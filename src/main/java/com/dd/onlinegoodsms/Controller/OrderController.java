package com.dd.onlinegoodsms.Controller;


import com.dd.onlinegoodsms.Entity.OrderDetailVO;
import com.dd.onlinegoodsms.Entity.Orders;
import com.dd.onlinegoodsms.Entity.Product;
import com.dd.onlinegoodsms.Entity.Result;
import com.dd.onlinegoodsms.Service.OrderService;
import com.dd.onlinegoodsms.Service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @PostMapping("/findAll")
    public List<Orders> findAll() {
        return orderService.findAll();
    }

    @PostMapping("/findById")
    public Orders findById(int id) {
        return orderService.findById(id);
    }
    @GetMapping("/search")
    public Result<Page<OrderDetailVO>> searchOrders(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {

        Page<OrderDetailVO> page = orderService.searchOrders(keyword, pageNum, pageSize);
        return new Result(200,  "查询成功",page);
    }


    @PostMapping
    public Result<?> create(@RequestBody OrderDetailVO vo) {
        Product product = productService.findById(vo.getProductId());
        if(product == null){
            return new Result(400, "商品不存在", null);
        }
        int quantity = vo.getQuantity();
        if(product.getStock() < quantity){
            return new Result(400, "库存不足", null);
        }


        double totalPrice = product.getPrice() * quantity;
        Orders order =new Orders();
        order.setUserId(vo.getUserId());
        order.setProductId(vo.getProductId());
        order.setQuantity(vo.getQuantity());
        order.setTotalPrice(totalPrice);
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));

        product.setStock(product.getStock()-quantity);
        int newSales = product.getSalesVolume() + vo.getQuantity();
        product.setSalesVolume(newSales);
        productService.update(product);

        orderService.insert(order);
        return new Result(200, "创建成功", null);
    }

    @PutMapping
    public Result<?> update(@RequestBody OrderDetailVO vo) {
        // 根据订单ID查找已有订单
        Orders order = orderService.findById(vo.getId());
        Product product = productService.findById(vo.getProductId());
        if (order == null) {
            return new Result<>(404, "订单不存在", null);
        }
        int oldQuantity = order.getQuantity();
        int newQuantity = vo.getQuantity();
        int diff = newQuantity - oldQuantity;
        if (product == null) {
            return new Result<>(404, "商品不存在", null);
        }

        if(diff > 0 && product.getStock() < diff){
            return new Result<>(400, "库存不足", null);
        }

        // 计算总价
        double totalPrice = product.getPrice() * vo.getQuantity();

        // 更新订单信息
        order.setUserId(vo.getUserId());
        order.setProductId(vo.getProductId());
        order.setQuantity(newQuantity);
        order.setTotalPrice(totalPrice);
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));

        product.setStock(product.getStock()-diff);

        // 保存更新
        product.setSalesVolume(product.getSalesVolume() + diff);
        productService.update(product);
        orderService.update(order);

        return new Result<>(200, "更新成功", null);
    }


    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        Orders order = orderService.findById(id);
        if (order == null) {
            return new Result<>(404, "订单不存在", null);
        }

        Product product = productService.findById(order.getProductId());
        if (product != null) {
            product.setSalesVolume(product.getSalesVolume() - order.getQuantity());
            productService.update(product);
        }

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

    @GetMapping("/getDailySales")
    public Result<List<Map<String, Object>>> getDailySales(@RequestParam Long productId,
                                                           @RequestParam(required = false) String startDate,
                                                           @RequestParam(required = false) String endDate)throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date start = null, end = null;
        if (startDate != null && !startDate.isEmpty()) {
            start = sdf.parse(startDate);
        }
        if (endDate != null && !endDate.isEmpty()) {
            end = sdf.parse(endDate);
        }

        List<Map<String, Object>> dailySales = orderService.getDailySales(productId, start, end);
        return new Result<>(200, "查询成功", dailySales);

    }

}
