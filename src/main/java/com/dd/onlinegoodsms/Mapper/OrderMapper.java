package com.dd.onlinegoodsms.Mapper;


import com.dd.onlinegoodsms.Entity.OrderDetailDTO;
import com.dd.onlinegoodsms.Entity.Orders;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from orders")
    public List<Orders> findAll();

    @Select("select * from orders where id= #{id}")
    public Orders findById(@Param("id") int id);


    @Select("select * from orders where user_id= #{user_id}")
    public List<Orders> findByUserId(@Param("user_id") int user_id);

    @Insert("INSERT INTO orders(user_id, product_id, quantity, total_price) " +
            "VALUES(#{userId}, #{productId}, #{quantity}, #{totalPrice})")
    int insert(Orders order);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE orders SET user_id=#{userId}, product_id=#{productId}, quantity=#{quantity}, total_price=#{totalPrice} WHERE id=#{id}")
    int update(Orders order);

    @Select("<script>" +
            "SELECT o.id, u.username AS userName, p.name AS productName, " +
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
    List<Orders> searchOrders(@Param("keyword") String keyword);

    @Select("""
    SELECT o.id, o.user_id, u.username, o.product_id, p.name as product_name,
           o.quantity, o.total_price, o.order_time
    FROM orders o
    JOIN user u ON o.user_id = u.id
    JOIN product p ON o.product_id = p.id
    WHERE o.id = #{id}
""")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="user_id", property="userId"),
            @Result(column="username", property="username"),
            @Result(column="product_id", property="productId"),
            @Result(column="product_name", property="productName"),
            @Result(column="quantity", property="quantity"),
            @Result(column="total_price", property="totalPrice"),
            @Result(column="order_time", property="orderTime")
    })
    public OrderDetailDTO findOrderDetailById(@Param("id") int id);
}
