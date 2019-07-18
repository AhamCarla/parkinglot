package com.oocl.fs.parkinglot.controller;

import com.oocl.fs.parkinglot.entity.ParkingLot;
import com.oocl.fs.parkinglot.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void should_return_parkinglot_after_saved_parkinglot() throws Exception {
        ParkingLot parkingLot = generateParkingLot();
        when(parkingLotService.save(any())).thenReturn(parkingLot);

        ResultActions result = mvc.perform(post("/parking-lots").content("{}").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.id", is("AAAA")));
    }


}