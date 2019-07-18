package com.oocl.fs.parkinglot.repository;

import com.oocl.fs.parkinglot.entity.ParkingLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {

    Page<ParkingLot> findAll(Pageable pageable);

}
