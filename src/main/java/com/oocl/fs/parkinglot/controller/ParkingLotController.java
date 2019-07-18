package com.oocl.fs.parkinglot.controller;

import com.oocl.fs.parkinglot.entity.ParkingLot;
import com.oocl.fs.parkinglot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/parking-lots")
    public ResponseEntity<ParkingLot> create(@RequestBody ParkingLot parkingLot) {
        return ResponseEntity.ok(parkingLotService.save(parkingLot));
    }

    @DeleteMapping("/parking-lots/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        parkingLotService.delete(id);
        return ResponseEntity.ok().build();
    }


}
