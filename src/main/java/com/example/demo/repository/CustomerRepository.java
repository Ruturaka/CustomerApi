package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Customer;

public interface CustomerRepository {

    int save(Customer record);

    int update(Customer record);

    Customer findById(Long id);

    List<Customer> findAll();

    List<Customer> findBYName(String name);

    int deleteById(Long id);

    int deleteAll();
}
