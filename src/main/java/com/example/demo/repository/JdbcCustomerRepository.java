package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

@Repository
public class JdbcCustomerRepository implements CustomerRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Customer customer) {
        return jdbcTemplate.update("INSERT INTO customers (name, email, number) VALUES(?,?,?)",
                new Object[] { customer.getName(), customer.getEmail(), customer.getNumber() });
    }

    @Override
    public int update(Customer customer) {
        return jdbcTemplate.update("UPDATE customers SET name=?, email=?, number=? WHERE id=?",
                new Object[] {customer.getName(), customer.getEmail(), customer.getNumber(), customer.getId(), customer.getId() });
    }

    @Override
    public Customer findById(Long id) {
        try {
            Customer customer = jdbcTemplate.queryForObject("SELECT * FROM customers WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Customer.class), id);

            return customer;
        }
        catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public Customer showByName(String name){
        try{
            Customer customer = jdbcTemplate.queryForObject("SELECT * FROM customers WHERE name=?",
                    BeanPropertyRowMapper.newInstance(Customer.class), name);
            return customer;
        }
        catch (IncorrectResultSizeDataAccessException e)
        {
            return null;
        }
    }



    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM customers WHERE id=?", id);
    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * from customers", BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public List<Customer> findBYName(String name) {
        String q = "SELECT * from customers WHERE name ILIKE '%" + name + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from customers");
    }
}