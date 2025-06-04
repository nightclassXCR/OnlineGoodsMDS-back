package com.dd.onlinegoodsms.Service.Impl;

import com.dd.onlinegoodsms.Mapper.OrderMapper;
import com.dd.onlinegoodsms.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderMapper.findById(id);
    }

    @Override
    public List<Order> searchOrders(String keyword) {
        return orderMapper.searchOrders(keyword);
    }


    @Override
    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int delete(int id) {
        return orderMapper.delete(id);
    }

    @Override
    public int update(Order order) {
        return orderMapper.update(order);
    }





}
