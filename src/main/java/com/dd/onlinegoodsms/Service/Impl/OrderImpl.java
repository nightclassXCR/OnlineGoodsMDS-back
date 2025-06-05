package com.dd.onlinegoodsms.Service.Impl;

import com.dd.onlinegoodsms.Entity.OrderDetailDTO;
import com.dd.onlinegoodsms.Entity.Orders;
import com.dd.onlinegoodsms.Mapper.OrderMapper;
import com.dd.onlinegoodsms.Service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Orders> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public Orders findById(int id) {
        return orderMapper.findById(id);
    }

    @Override
    public PageInfo<Orders> searchOrders(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = orderMapper.searchOrders(keyword);
        return new PageInfo<>(list);
    }


    @Override
    public int insert(Orders order) {
        return orderMapper.insert(order);
    }

    @Override
    public int delete(int id) {
        return orderMapper.delete(id);
    }

    @Override
    public int update(Orders order) {
        return orderMapper.update(order);
    }

    @Override
    public OrderDetailDTO findOrderDetailById(int id) {
        return orderMapper.findOrderDetailById(id);
    }

    @Override
    public List<Orders> findByUserId(int userId) {
        return orderMapper.findByUserId(userId);
    }





}
