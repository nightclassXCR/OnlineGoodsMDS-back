package com.dd.onlinegoodsms.Service.Impl;

import com.dd.onlinegoodsms.Entity.OrderDetailVO;
import com.dd.onlinegoodsms.Entity.Orders;
import com.dd.onlinegoodsms.Mapper.OrderMapper;
import com.dd.onlinegoodsms.Service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public PageInfo<OrderDetailVO> searchOrders(String keyword, int pageNum, int pageSize) {
        // 启动分页，PageHelper会自动对后面紧跟的查询做分页处理
        PageHelper.startPage(pageNum, pageSize);

        // 这里执行分页查询，结果会自动被PageHelper拦截，返回Page对象
        List<OrderDetailVO> list = orderMapper.searchOrders(keyword);

        // list本身就是Page对象，可以直接强转
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
    public OrderDetailVO findOrderDetailById(int id) {
        return orderMapper.findOrderDetailById(id);
    }

    @Override
    public List<Orders> findByUserId(int userId) {
        return orderMapper.findByUserId(userId);
    }

    @Override
    public List<Map<String, Object>> getDailySales(Long productId, Date startDate, Date endDate) {
        return orderMapper.getDailySales(productId, startDate, endDate);
    }

    @Override
    public int countSearchOrders() {
        return orderMapper.countSearchOrders();
    }





}
