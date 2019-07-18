package com.oocl.fs.parkinglot.repository;

import com.oocl.fs.parkinglot.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.yml")
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void should_return_specify_order_when_find_order_by_card_number() {
        Order order = new Order();
        order.setCardNumber("AAAAA");
        order.setParkingLotName("zha");
        orderRepository.save(order);

        Order fetched = orderRepository.findByCardNumber("AAAAA");

        assertEquals("zha", fetched.getParkingLotName());
    }

}