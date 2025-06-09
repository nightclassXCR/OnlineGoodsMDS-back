package com.dd.onlinegoodsms.Mapper;


import com.dd.onlinegoodsms.Entity.OrderDetailVO;
import com.dd.onlinegoodsms.Entity.Orders;
import org.apache.ibatis.annotations.*;


import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    @Select("select * from orders")
    List<Orders> findAll();

    @Select("select * from orders where id= #{id}")
    Orders findById(@Param("id") int id);

    @Select("select * from orders where user_id= #{user_id}")
    List<Orders> findByUserId(@Param("user_id") int user_id);

    @Insert("INSERT INTO orders(user_id, product_id, quantity, total_price) " +
            "VALUES(#{userId}, #{productId}, #{quantity}, #{totalPrice})")
    int insert(Orders order);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE orders SET user_id=#{userId}, product_id=#{productId}, quantity=#{quantity}, total_price=#{totalPrice} WHERE id=#{id}")
    int update(Orders order);

    /**
     * 联合查询订单，支持按关键字搜索用户名、商品名、状态，分页
     */
    @Select({
            "<script>",
            "SELECT o.id, o.user_id, u.username, o.product_id, p.name AS productName, o.quantity, o.total_price, o.order_time",
            "FROM orders o",
            "LEFT JOIN user u ON o.user_id = u.id",
            "LEFT JOIN product p ON o.product_id = p.id",
            "WHERE 1=1",
            "<if test='keyword != null and keyword != \"\"'>",
            "  AND (u.username LIKE CONCAT('%', #{keyword}, '%')",
            "    OR p.name LIKE CONCAT('%', #{keyword}, '%'))",
            "</if>",
            "ORDER BY o.order_time DESC",
            "</script>"
    }) @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "username", property = "username"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "productName", property = "productName"),
            @Result(column = "quantity", property = "quantity"),
            @Result(column = "total_price", property = "totalPrice"),
            @Result(column = "order_time", property = "orderTime")
    })
    List<OrderDetailVO> searchOrders(@Param("keyword") String keyword);


    /**
     * 查询总数（用于分页）
     */
    @Select({
            "<script>",
            "SELECT COUNT(1) FROM orders o",
            "LEFT JOIN user u ON o.user_id = u.id",
            "LEFT JOIN product p ON o.product_id = p.id",
            "WHERE 1=1",
            "<if test='keyword != null and keyword != \"\"'>",
            "  AND (u.username LIKE CONCAT('%', #{keyword}, '%')",
            "    OR p.product_name LIKE CONCAT('%', #{keyword}, '%')",
            "    OR o.status LIKE CONCAT('%', #{keyword}, '%'))",
            "</if>",
            "</script>"
    })
    int countSearchOrders(@Param("keyword") String keyword);

    // 下面SQL语句修正：加逗号和空格，保证语法正确
    @Select("SELECT o.id, o.user_id, u.username, o.product_id, p.name AS name, " +
            "o.quantity, o.total_price, o.order_time " +
            "FROM orders o " +
            "JOIN user u ON o.user_id = u.id " +
            "JOIN product p ON o.product_id = p.id " +
            "WHERE o.id = #{id}")
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
    OrderDetailVO findOrderDetailById(@Param("id") int id);

    @Select(" SELECT DATE_FORMAT(order_time, '%Y-%m-%d') AS date,"+
            "SUM(quantity) AS total_sales " +
            "FROM orders " +
            "WHERE product_id = #{productId} " +
            "AND order_time BETWEEN #{startDate} AND #{endDate} " +
            "GROUP BY DATE_FORMAT(order_time, '%Y-%m-%d') " +
            "ORDER BY date")
    List<Map<String, Object>> getDailySales(@Param("productId") Long productId,
                                            @Param("startDate") Date startDate,
                                            @Param("endDate") Date endDate);
}

