package com.it.controller;

import com.it.entity.Order;
import com.it.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping(value = "/order/{orderId}")
    public Order queryOrderInfoById(@PathVariable(name="orderId") String orderId){
        return orderService.queryOrderByid(orderId);
    }
}
