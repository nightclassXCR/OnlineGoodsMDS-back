package com.dd.onlinegoodsms.Service;


import com.dd.onlinegoodsms.Entity.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(int id);
    public PageInfo<Product> findByNameLike(String name,  int pageNum, int pageSize);
    public int update(Product product);
    public int delete(int id);
    public void saveProduct(Product product);
    public List<Product> findByCategory(String category);
    public List<Map<String, Object>>getTopSellingProducts(int limit);
    public PageInfo<Product> selectByPage(int pageNum, int pageSize);
    public long countAll();


}
