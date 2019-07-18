package com.oocl.fs.parkinglot.controller;

import com.oocl.fs.parkinglot.entity.Order;
import com.oocl.fs.parkinglot.entity.ParkingLot;
import com.oocl.fs.parkinglot.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class ParkingLotControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ParkingLotService parkingLotService;

    private ParkingLot generateParkingLot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId("AAAA");
        parkingLot.setCapacity(11);
        parkingLot.setName("aaa");
        parkingLot.setAddress("zha");
        return parkingLot;
    }

    @Test
    void should_return_parking_lot_after_saved_parking_lot() throws Exception {
        ParkingLot parkingLot = generateParkingLot();
        when(parkingLotService.save(any())).thenReturn(parkingLot);

        ResultActions result = mvc.perform(post("/parking-lots").content("{}").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.id", is("AAAA")));
    }

    @Test
    void should_return_status_ok_after_deleted_parking_lot() throws Exception {

        ResultActions result = mvc.perform(delete("/parking-lots/{id}", "AAAA"));

        result.andExpect(status().isOk());

    }

    @Test
    void should_return_page_when_find_parking_lot_by_page() throws Exception {
        Page<ParkingLot> page = new PageImpl<>(Collections.singletonList(generateParkingLot()));
        when(parkingLotService.findParkingLotPage(anyInt(), anyInt())).thenReturn(page);

        ResultActions result = mvc.perform(get("/parking-lots?page={page}", 1));

        result.andExpect(status().isOk());
    }

    @Test
    void should_return_parking_lot_when_find_by_id() throws Exception {
        ParkingLot parkingLot = generateParkingLot();
        when(parkingLotService.findById(any())).thenReturn(parkingLot);

        ResultActions result = mvc.perform(get("/parking-lots/{id}", "AAAA"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.id", is("AAAA")));
    }

    @Test
    void should_return_parking_lot_when_update() throws Exception {
        ParkingLot parkingLot = generateParkingLot();
        when(parkingLotService.update(any(), any())).thenReturn(parkingLot);

        ResultActions result = mvc.perform(put("/parking-lots/{id}", "AAAA").content("{}").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.id", is("AAAA")));
    }

    @Test
    void should_return_order_when_park_car() throws Exception {
        Order order = new Order();
        order.setCarNumber("AAAA");

        when(parkingLotService.parkCar(any(), any())).thenReturn(order);

        ResultActions result = mvc.perform(post("/parking-lots/{id}/order", "AAAA").content("{}").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.carNumber", is("AAAA")));

    }

    @Test
    void should_return_order_when_fetch_car() throws Exception {
        Order order = new Order();
        order.setCarNumber("AAAA");

        when(parkingLotService.fetchCar(any(), any())).thenReturn(order);

        ResultActions result = mvc.perform(put("/parking-lots/{id}/order", "AAAA").content("{}").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.carNumber", is("AAAA")));
    }


}