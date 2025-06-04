package com.dd.onlinegoodsms.Mapper;

import com.dd.onlinegoodsms.Entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from product")
    public List<Product> findAll();

    @Select("select * from product where id= #{id}")
    public Product findById(@Param("id") int id);

    // 模糊查询
    @Select("<script>" +
            "SELECT * FROM product " +
            "<where>" +
            " <if test='name != null and name != \"\"'>" +
            "   AND name LIKE CONCAT('%', #{name}, '%') " +
            " </if>" +
            "</where>" +
            "ORDER BY id DESC" +
            "</script>")
    List<Product> findByNameLike(@Param("name") String name);

    @Insert("INSERT INTO product(name, description, price, category, image, stock, sales_volume, create_time) " +
            "VALUES(#{name}, #{description}, #{price}, #{category}, #{image}, #{stock}, #{salesVolume}, #{createTime})")
    void insertProduct(Product product);

    @Update("UPDATE product SET name = #{name}, description = #{description}, " +
            "price = #{price}, stock = #{stock} WHERE id = #{id}")
    int update(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("select * from product where category = #{category}")
    public List<Product> findByCategory(@Param("category") String category);

}
