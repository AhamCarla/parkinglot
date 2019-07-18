package com.oocl.fs.parkinglot.service;

import com.oocl.fs.parkinglot.entity.Order;
import com.oocl.fs.parkinglot.entity.ParkingLot;
import com.oocl.fs.parkinglot.repository.OrderRepository;
import com.oocl.fs.parkinglot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public Order parkCar(String parkingLotId, String cardNumber) {
        ParkingLot parkingLot = parkingLotRepository.getOne(parkingLotId);
        Order order = new Order();
        order.setCardNumber(cardNumber);
        order.setParkingLotName(parkingLot.getName());
        parkingLot.getOrders().add(order);
        parkingLotRepository.save(parkingLot);
        return orderRepository.findByCardNumber(cardNumber);
    }

}
