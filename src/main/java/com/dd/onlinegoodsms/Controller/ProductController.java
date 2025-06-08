package com.dd.onlinegoodsms.Controller;

import com.dd.onlinegoodsms.Entity.Product;
import com.dd.onlinegoodsms.Entity.Result;
import com.dd.onlinegoodsms.Service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    // 上传文件保存根路径
    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping
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
    public Result update(@RequestBody Product product) {
        productService.update(product);
        return new Result(200, "修改成功", product);
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
    public Result uploadProduct(
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("price") double price,
                                @RequestParam("category") String category,
                                @RequestParam("stock") int stock,
                                @RequestPart(value="file", required = false) MultipartFile file) {
        try {
            String imageUrl = null;

            if (file != null && !file.isEmpty()) {
                // 生成唯一文件名
                String originalFilename = file.getOriginalFilename();
                String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                String filename = UUID.randomUUID().toString() + suffix;
                String uploadDir = "D:/upload-dir/";

                // 创建目录（如果不存在）
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();  // 自动创建多级目录
                }

                // 保存文件
                File dest = new File(uploadDir + filename);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                file.transferTo(dest);

                // 拼接访问URL，比如http://localhost:8080/uploads/xxx.jpg
                imageUrl = "/uploads/" + filename;
            }
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
            product.setStock(stock);
            product.setSalesVolume(0);
            product.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            product.setImage(imageUrl);
            System.out.println("name = " + product.getName());
            System.out.println("category = " + product.getCategory());
            System.out.println("price = " + product.getPrice());
            System.out.println("stock = " + product.getStock());
            System.out.println("description = " + product.getDescription());
            System.out.println("file = " + (file != null ? file.getOriginalFilename() : "null"));

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

    @GetMapping("/getTopSellingProducts")
    public Result getTopSellingProducts(@RequestParam(defaultValue = "5") int limit) {
        List<Map<String, Object>> products = productService.getTopSellingProducts(limit);
        return new Result(200, "查询成功", products);
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Product> pageInfo = productService.selectByPage(pageNum, pageSize);
        return new Result(200, "查询成功", pageInfo);
    }

    @GetMapping("/countAll")
    public Result countAll() {
        long count = productService.countAll();
        return new Result(200, "查询成功", count);
    }


}







