package com.example.demo.service;

import com.example.demo.model.Order;

public interface OrderService {

    /**
     * find by id
     */
    Order findById(Integer id);

    /**
     * return exception
     */
    Order createError();
}
