package com.dd.onlinegoodsms.Controller;

import com.dd.onlinegoodsms.Entity.Product;
import com.dd.onlinegoodsms.Entity.Result;
import com.dd.onlinegoodsms.Service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/findAll")
    public Result findAll() {
        List<Product> products = productService.findAll();
        return new Result(200, "查询成功", products);
    }

    @PostMapping("/findById")
    public Result findById(int id) {
        Product product = productService.findById(id);
        return new Result(200, "查询成功", product);
    }
    @PostMapping("/update")
    public Result update(Product product) {
        int i = productService.update(product);
        if (i > 0) {
            return new Result(200, "修改成功", i);
        } else {
            return new Result(500, "修改失败", i);
        }
    }

    @GetMapping("/search")
    public Result search(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageInfo<Product> result = productService.findByNameLike(name, pageNum, pageSize);
        return new Result(200, "查询成功", result);
    }

    @PostMapping("/delete")
    public Result delete(int id) {
        int i = productService.delete(id);
        if (i > 0) {
            return new Result(200, "删除成功", i);
        } else {
            return new Result(500, "删除失败", i);
        }
    }

    // 上传商品（包括图片）
    @PostMapping("/upload")
    public Result uploadProduct(@RequestParam("file") MultipartFile file,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("price") double price,
                                @RequestParam("category") String category,
                                @RequestParam("stock") int stock) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
            product.setStock(stock);
            product.setSalesVolume(0);
            product.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));

            if (!file.isEmpty()) {
                byte[] imageBytes = file.getBytes();
                product.setImage(imageBytes);
            }

            productService.saveProduct(product);
            return new Result(200,"上传成功", product);
        } catch (IOException e) {
            return new Result(400,"上传失败：",e.getMessage());
        }
    }

        @PostMapping("/findByCategory")
         public Result findByCategory(String category) {
            List<Product> products = productService.findByCategory(category);
        return new Result(200, "查询成功", products);
    }


}







