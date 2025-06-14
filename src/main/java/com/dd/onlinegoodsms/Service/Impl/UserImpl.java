package com.dd.onlinegoodsms.Service.Impl;

import com.dd.onlinegoodsms.Entity.User;
import com.dd.onlinegoodsms.Mapper.UserMapper;
import com.dd.onlinegoodsms.Service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
         return userMapper.login(username,password);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public boolean register(User user) {
        if (userMapper.findByUsername(user.getUsername()) != null) {
            return false; // 用户已存在
        }
        return userMapper.register(user) > 0;
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delete(int id) {
        return userMapper.delete(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public PageInfo<User> pageQuery(int pageNum, int pageSize, String username) {
        // 使用 PageHelper 分页插件
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.findByUsernameLike(username);
        return new PageInfo<>(users);
    }

}
