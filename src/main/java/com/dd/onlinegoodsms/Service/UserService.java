package com.dd.onlinegoodsms.Service;

import com.dd.onlinegoodsms.Entity.User;
import java.util.List;
import com.github.pagehelper.PageInfo;
import java.util.Map;

public interface UserService {
    public User login(String username, String password);
    public User findByUsername(String username);
    public User findById(int id);
    public boolean register(User user);
    public int update(User user);
    public int delete(int id);
    public List<User> findAll();
    PageInfo<User> pageQuery(int pageNum, int pageSize, String username);
}
