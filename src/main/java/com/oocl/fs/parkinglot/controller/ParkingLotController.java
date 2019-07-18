package com.oocl.fs.parkinglot.controller;

import com.oocl.fs.parkinglot.entity.ParkingLot;
import com.oocl.fs.parkinglot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping(value = "/parking-lots", params = {"page"})
    public ResponseEntity<Page<ParkingLot>> list(@RequestParam Integer page, @RequestParam(defaultValue = "15", required = false) Integer pageSize) {
        return ResponseEntity.ok(parkingLotService.findParkingLotPage(page, pageSize));
    }

    @GetMapping("/parking-lots/{id}")
    public ResponseEntity<ParkingLot> findById(@PathVariable String id) {
        return ResponseEntity.ok(parkingLotService.findById(id));
    }

    @PutMapping("/parking-lots/{id}")
    public ResponseEntity<ParkingLot> update(@PathVariable String id, @RequestBody ParkingLot parkingLot) {
        return ResponseEntity.ok(parkingLotService.update(id, parkingLot));
    }

}
