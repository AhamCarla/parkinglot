package com.oocl.fs.parkinglot.repository;

import com.oocl.fs.parkinglot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
