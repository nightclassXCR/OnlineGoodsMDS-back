package com.dd.onlinegoodsms.Service;

import com.dd.onlinegoodsms.Entity.OrderDetailVO;
import com.dd.onlinegoodsms.Entity.Orders;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface OrderService {

    public List<Orders> findAll();

    public Orders findById(int id);

    public  List<Orders> findByUserId(int userId);

    public int insert(Orders order);

    public int delete(int id);

    public int update(Orders order);

    public Page<OrderDetailVO> searchOrders(String keyword, int pageNum, int pageSize);

    public OrderDetailVO findOrderDetailById(int id);

    public List<Map<String, Object>> getDailySales(Long productId, Date startDate, Date endDate);

}
