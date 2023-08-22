package com.alaindroid.sportsbet.orders.controller;

import com.alaindroid.sportsbet.orders.service.OrderHandler;
import com.alaindroid.sportsbet.orders.model.OrderRequest;
import com.alaindroid.sportsbet.orders.model.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ordering {

    @Autowired
    private OrderHandler orderHandler;

    @PostMapping("/order")
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        return orderHandler.createOrder(request);
    }
}
