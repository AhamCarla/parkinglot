package com.oocl.fs.parkinglot.service;

import com.oocl.fs.parkinglot.entity.Car;
import com.oocl.fs.parkinglot.entity.Order;
import com.oocl.fs.parkinglot.entity.ParkingLot;
import com.oocl.fs.parkinglot.repository.OrderRepository;
import com.oocl.fs.parkinglot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private OrderRepository orderRepository;

    public ParkingLot save(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    public Page<ParkingLot> findParkingLotPage(Integer page, Integer pageSize) {
        return parkingLotRepository.findAll(PageRequest.of(page, pageSize));
    }

    public void delete(String id) {
        parkingLotRepository.deleteById(id);
    }

    public ParkingLot findById(String id) {
        return parkingLotRepository.findById(id).orElse(null);
    }

    public ParkingLot update(String id, ParkingLot parkingLot) {
        parkingLot.setId(id);
        return parkingLotRepository.save(parkingLot);
    }

    public Order parkCar(String parkingLotId, Car car) {
        ParkingLot parkingLot = parkingLotRepository.getOne(parkingLotId);
        Order order = new Order();
        order.setCar(car);
        order.setParkingLotName(parkingLot.getName());
        parkingLot.getOrders().add(order);
        return parkingLotRepository.save(parkingLot).getOrders().stream().filter(item -> item.getCar().equals(car)).collect(Collectors.toList()).get(0);
    }

    public void fetchCar(String parkingLotId, Car car) {
        ParkingLot parkingLot = parkingLotRepository.getOne(parkingLotId);
        Order order = parkingLot.getOrders().stream().filter(item -> item.getCar().equals(car)).collect(Collectors.toList()).get(0);
        parkingLot.getOrders().remove(order);
        order.setStatus(false);
        order.setLeaveTime(new Date());
        parkingLot.getOrders().add(order);
        parkingLotRepository.save(parkingLot);
    }


}
