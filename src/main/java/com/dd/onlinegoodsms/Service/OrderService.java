package com.dd.onlinegoodsms.Service;

import com.dd.onlinegoodsms.Entity.OrderDetailDTO;
import com.dd.onlinegoodsms.Entity.Orders;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    public List<Orders> findAll();

    public Orders findById(int id);

    public  List<Orders> findByUserId(int userId);

    public int insert(Orders order);

    public int delete(int id);

    public int update(Orders order);

    public PageInfo<Orders> searchOrders(String keyword,  int pageNum, int pageSize);

    public OrderDetailDTO findOrderDetailById(int id);

}
