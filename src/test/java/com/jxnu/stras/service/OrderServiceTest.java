package com.jxnu.stras.service;

import com.jxnu.stras.domin.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {
@Autowired
private OrderService orderService;

    @Test
    void save() {
        Orders orders=new Orders();
        orders.setUserPhone("18679657892");
        orders.setLoginName("grandir");
        orderService.save(orders);

    }
}