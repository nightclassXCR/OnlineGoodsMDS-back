package com.dd.onlinegoodsms.Mapper;

import com.dd.onlinegoodsms.Entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {


    @Select("select * from product")
    @Results({
            @Result(property = "image", column = "image_url")
    })
    List<Product> findAll();


    @Select("select * from product where id= #{id}")
    @Results({
            @Result(property = "image", column = "image_url")
    })
    Product findById(@Param("id") int id);


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
    @Results({
            @Result(property = "image", column = "image_url")
    })
    List<Product> findByNameLike(@Param("name") String name);

    @Insert("INSERT INTO product(name, description, price, category, image_url, stock, sales_volume, create_time) " +
            "VALUES(#{name}, #{description}, #{price}, #{category}, #{image}, #{stock}, #{salesVolume}, #{createTime})")
    void insertProduct(Product product);

    @Update("UPDATE product SET name = #{name}, description = #{description}, " +
            "price = #{price}, stock = #{stock},image_url = #{image} WHERE id = #{id}")
    int update(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("select * from product where category = #{category}")
    @Results({
            @Result(property = "image", column = "image_url")
    })
    List<Product> findByCategory(@Param("category") String category);

    @Select("SELECT name, sales_volume FROM product ORDER BY sales_volume DESC LIMIT #{limit}")
    @Results({
            @Result(property = "image", column = "image_url")
    })
    List<Map<String, Object>> getTopSellingProducts(@Param("limit") int limit);

    @Select("SELECT * FROM product ORDER BY id DESC LIMIT #{offset}, #{size}")
    @Results({
            @Result(property = "image", column = "image_url")
    })
    List<Product> selectByPage(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM product")
    @Results({
            @Result(property = "image", column = "image_url")
    })
    long countAll();


}
