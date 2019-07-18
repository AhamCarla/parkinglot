package com.oocl.fs.parkinglot.repository;

import com.oocl.fs.parkinglot.entity.ParkingLot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ParkingLotRepositoryTest {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Test
    void should_find_all_return_park_pages() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            ParkingLot parkingLot = new ParkingLot();
            parkingLot.setAddress("ZHA");
            parkingLot.setName("ZHA-PARKING-LOT" + i);
            parkingLot.setCapacity(100);
            parkingLots.add(parkingLot);
        }
        parkingLotRepository.saveAll(parkingLots);

        Page<ParkingLot> parkingLotPages = parkingLotRepository.findAll(PageRequest.of(0, 15));

        assertEquals(2, parkingLotPages.getTotalPages());

    }

}