package com.example.demo.service.impl;

import com.example.demo.exception.CustomException;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order findById(Integer id) {
        return new Order(1, "RPC", 1);
    }

    @Override
    public Order createError() {
        throw new CustomException("Custom exception");
    }
}
