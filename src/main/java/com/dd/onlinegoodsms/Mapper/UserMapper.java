package com.dd.onlinegoodsms.Mapper;

import com.dd.onlinegoodsms.Entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {

    // 登录
    @Select("select * from user where username=#{username} and password=#{password}")
    User login(@Param("username")String username, @Param("password")String password);

    // 注册
    @Insert("insert into user(username,password,role) values(#{username},#{password},#{role})")
    int register(User user);

    // 查询用户(根据用户名)
    @Select("select * from user where username=#{username}")
    User findByUsername(@Param("username")String username);

    // 查询用户（根据id）
    @Select("select * from user where id= #{id}")
    User findById(@Param("id") int id);

    // 展示全部用户
    @Select("select * from user")
    List<User> findAll();

    // 修改用户信息
    @Update("update user set username=#{username},password=#{password},role=#{role} where id=#{id}")
    int update(User user);

    // 删除用户
    @Delete("delete from user where id= #{id}")
    int delete(@Param("id") int id);

    // 模糊查询用户名
    @Select("SELECT * FROM user WHERE username LIKE CONCAT('%', #{username}, '%')")
    List<User> findByUsernameLike(@Param("username") String username);

}
