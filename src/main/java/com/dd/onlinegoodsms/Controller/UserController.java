package com.dd.onlinegoodsms.Controller;

import com.dd.onlinegoodsms.Entity.Result;
import com.dd.onlinegoodsms.Entity.User;
import com.dd.onlinegoodsms.Service.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user)
    {
        User dbUser = userService.findByUsername(user.getUsername());
        User LoginUser = userService.login(user.getUsername(), user.getPassword());
        if (LoginUser != null)
        {
            Map<String, Object> data = new HashMap<>();
            data.put("id", LoginUser.getId());
            data.put("username",LoginUser.getUsername());
            data.put("role", dbUser.getRole());
            return new Result(200, "登录成功", data);
        }
        else
        {
            return new Result(401, "用户名或密码错误",LoginUser);
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user)
    {
        if (userService.register(user))
        {
            return new Result(200, "注册成功", user);
        }
        else
        {
            return new Result(400, "注册失败", null);
        }
    }

    @PostMapping("/findById")
    public Result findById(int id)
    {
        User user = userService.findById(id);
        if (user != null)
        {
            return new Result(200, "查询成功", user);
        }
        else
        {
            return new Result(400, "查询失败", null);
        }
    }

    @PostMapping("/findByUsername")
    public Result findByUsername(String username)
    {
        User user = userService.findByUsername(username);
        if (user != null)
        {
            return new Result(200, "查询成功", user);
        }
        else
        {
            return new Result(400, "查询失败", null);
        }
    }

    @PostMapping("/findAll")
    public Result findAll()
    {
        return new Result(200, "查询成功", userService.findAll());
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user)
    {
        if (userService.update(user) > 0)
        {
            return new Result(200, "修改成功", user);
        }
        else
        {
            return new Result(400, "修改失败", null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id)
    {
        if (userService.delete(id) > 0)
        {
            return new Result(200, "删除成功", null);
        }
        else
        {
            return new Result(400, "删除失败", null);
        }
    }

    @PostMapping("/list")
    public Result page(@RequestBody Map<String, Object> paramMap) {
        Map<String, Object> params = (Map<String, Object>) paramMap.get("params");
        String username = params.get("username") == null ? "" : params.get("username").toString();
        int pageNum = params.get("pageNum") == null ? 1 : Integer.parseInt(params.get("pageNum").toString());
        int pageSize = params.get("pageSize") == null ? 10 : Integer.parseInt(params.get("pageSize").toString());

        PageInfo<User> pageInfo = userService.pageQuery(pageNum, pageSize,username);

        Map<String, Object> data = new HashMap<>();
        data.put("list", pageInfo.getList());
        data.put("total", pageInfo.getTotal());

        return new Result(200, "查询成功", data);
    }

    @PostMapping("add")
    public Result add(@RequestBody User user)
    {
        if (userService.register(user))
        {
            return new Result(200, "注册成功", user);
        }
        else
        {
            return new Result(400, "注册失败", null);
        }
    }


}
