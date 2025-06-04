package com.dd.onlinegoodsms.Service;


import com.dd.onlinegoodsms.Entity.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(int id);
    public PageInfo<Product> findByNameLike(String name,  int pageNum, int pageSize);
    public int update(Product product);
    public int delete(int id);
    public void saveProduct(Product product);
    public List<Product> findByCategory(String category);

}
