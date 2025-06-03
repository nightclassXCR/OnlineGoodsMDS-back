package com.dd.onlinegoodsms.Controller;

import com.dd.onlinegoodsms.Entity.Result;
import com.dd.onlinegoodsms.Entity.User;
import com.dd.onlinegoodsms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(User user)
    {
        User LoginUser = userService.login(user.getUsername(), user.getPassword());
        if (LoginUser != null)
        {
            return new Result(200, "登录成功", LoginUser);
        }
        else
        {
            return new Result(401, "用户名或密码错误", null);
        }
    }

    @PostMapping("/register")
    public Result register(User user)
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

    @PostMapping("/update")
    public Result update(User user)
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

    @DeleteMapping("/delete")
    public Result delete(@RequestParam int id)
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


}
