package com.dd.onlinegoodsms.Mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from order")
    public List<Order> findAll();

    @Select("select * from order where id= #{id}")
    public Order findById(@Param("id") int id);


    @Select("select * from order where user_id= #{user_id}")
    public List<Order> findByUserId(@Param("user_id") int user_id);

    @Insert("INSERT INTO orders(user_id, product_id, quantity, total_price) " +
            "VALUES(#{userId}, #{productId}, #{quantity}, #{totalPrice})")
    int insert(Order order);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE orders SET user_id=#{userId}, product_id=#{productId}, quantity=#{quantity}, total_price=#{totalPrice} WHERE id=#{id}")
    int update(Order order);

    @Select("<script>" +
            "SELECT o.id, u.name AS userName, p.name AS productName, " +
            "o.quantity, o.total_price, o.order_time " +
            "FROM orders o " +
            "JOIN user u ON o.user_id = u.id " +
            "JOIN product p ON o.product_id = p.id " +
            "WHERE 1=1 " +
            "<if test='keyword != null and keyword != \"\"'>" +
            "AND (u.name LIKE CONCAT('%', #{keyword}, '%') " +
            "OR p.name LIKE CONCAT('%', #{keyword}, '%')) " +
            "</if>" +
            "ORDER BY o.order_time DESC" +
            "</script>")
    List<Order> searchOrders(@Param("keyword") String keyword);
}
