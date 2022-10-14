package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcOrderRepo implements OrderRepo{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Order order) {
        return jdbcTemplate.update("INSERT INTO orders (dish, price, qunt) VALUES(?,?,?)",
                new Object[] {order.getDish(), order.getPrice(), order.getQunt()});
    }

    @Override
    public int update(Order order) {
        return jdbcTemplate.update("UPDATE orders SET dish=?, price=?, qunt=? WHERE m_id=?",
                new Object[] {order.getDish(), order.getPrice(), order.getQunt(), order.getM_id() });
    }

    @Override
    public Order findById(Long id) {
        try {
            Order order = jdbcTemplate.queryForObject("SELECT * FROM orders WHERE m_id=?",
                    BeanPropertyRowMapper.newInstance(Order.class), id);

            return order;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query("SELECT * from orders", BeanPropertyRowMapper.newInstance(Order.class));
    }

    @Override
    public List<Order> findByDish(String dish) {
        String q = "SELECT * from orders WHERE dish ILIKE '%" + dish + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Order.class));
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM orders WHERE m_id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from orders");
    }
}