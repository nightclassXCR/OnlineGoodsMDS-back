package com.dd.onlinegoodsms.Service;

import com.dd.onlinegoodsms.Entity.OrderDetailVO;
import com.dd.onlinegoodsms.Entity.Orders;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface OrderService {

    List<Orders> findAll();

    Orders findById(int id);

    List<Orders> findByUserId(int userId);

    int insert(Orders order);

    int delete(int id);

    int update(Orders order);

    PageInfo<OrderDetailVO> searchOrders(String keyword, int pageNum, int pageSize);

    OrderDetailVO findOrderDetailById(int id);

    List<Map<String, Object>> getDailySales(Long productId, Date startDate, Date endDate);

    int countSearchOrders();

}
