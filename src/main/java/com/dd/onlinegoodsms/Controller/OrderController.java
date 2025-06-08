package com.dd.onlinegoodsms.Controller;


import com.dd.onlinegoodsms.Entity.OrderDetailVO;
import com.dd.onlinegoodsms.Entity.Orders;
import com.dd.onlinegoodsms.Entity.Result;
import com.dd.onlinegoodsms.Service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/findAll")
    public List<Orders> findAll() {
        return orderService.findAll();
    }

    @PostMapping("/findById")
    public Orders findById(int id) {
        return orderService.findById(id);
    }

//    @GetMapping("/search")
//    public Result<PageInfo<Orders>> search(
//            @RequestParam(required = false) String keyword,
//            @RequestParam(defaultValue = "1") int pageNum,
//            @RequestParam(defaultValue = "10") int pageSize) {
//        return new Result(200, "查询成功", orderService.searchOrders(keyword, pageNum, pageSize));
//    }

    @GetMapping("/search")
    public Result<Page<OrderDetailVO>> searchOrders(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {

        Page<OrderDetailVO> page = orderService.searchOrders(keyword, pageNum, pageSize);
        return new Result(200,  "查询成功",page);
    }


    @PostMapping
    public Result<?> create(@RequestBody Orders order) {
        orderService.insert(order);
        return new Result(200, "添加成功", null);
    }

    @PutMapping
    public Result<?> update(@RequestBody Orders order) {
        orderService.update(order);
        return new Result(200, "更新成功", null);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        orderService.delete(id);
        return new Result(200, "删除成功", null);
    }

    @GetMapping
    public Result<List<Orders>> getAll() {
        return new Result(200, "获得成功", null);
    }

    @GetMapping("/findByUserId")
    public Result<List<Orders>> findByUserId(int userId) {
        return new Result(200, "查询成功", orderService.findByUserId(userId));
    }

    @GetMapping("/findOrderDetailById")
    public Result<OrderDetailVO> findOrderDetailById(int id) {
        return new Result(200, "查询成功", orderService.findOrderDetailById(id));
    }

}
