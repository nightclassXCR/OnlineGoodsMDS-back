package com.dd.onlinegoodsms.Service;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    public List<Order> findAll();

    public Order findById(int id);


    public int insert(Order order);

    public int delete(int id);

    public int update(Order order);

    public List<Order> searchOrders(String keyword);

}
