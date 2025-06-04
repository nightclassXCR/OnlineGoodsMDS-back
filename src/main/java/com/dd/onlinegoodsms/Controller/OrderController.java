package com.dd.onlinegoodsms.Controller;


import com.dd.onlinegoodsms.Entity.Result;
import com.dd.onlinegoodsms.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/findAll")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @PostMapping("/findById")
    public Order findById(int id) {
        return orderService.findById(id);
    }

    @GetMapping("/search")
    public Result<List<Order>> search(@RequestParam(required = false) String keyword) {
        return new Result(200,  "查询成功",orderService.searchOrders(keyword));
    }

    @PostMapping
    public Result<?> create(@RequestBody Order order) {
        orderService.insert(order);
        return new Result(200, "添加成功", null);
    }

    @PutMapping
    public Result<?> update(@RequestBody Order order) {
        orderService.update(order);
        return new Result(200, "添加成功", null);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        orderService.delete(id);
        return new Result(200, "添加成功", null);
    }

    @GetMapping
    public Result<List<Order>> getAll() {
        return new Result(200, "添加成功", null);
    }

}
