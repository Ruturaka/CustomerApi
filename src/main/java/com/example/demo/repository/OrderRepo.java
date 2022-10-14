package com.example.demo.repository;

import java.util.List;
import com.example.demo.model.Order;

public interface OrderRepo {
    int save(Order record);

    int update(Order record);

    Order findById(Long id);

    List<Order> findAll();

    List<Order> findByDish(String dish);

    int deleteById(Long id);

    int deleteAll();
}
