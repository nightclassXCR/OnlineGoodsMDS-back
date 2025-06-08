package com.dd.onlinegoodsms.Service.Impl;

import com.dd.onlinegoodsms.Entity.Product;
import com.dd.onlinegoodsms.Mapper.ProductMapper;
import com.dd.onlinegoodsms.Service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    private ProductMapper  ProductMapper;

    @Override
    public List<Product> findAll(){
        return ProductMapper.findAll();
    }

    @Override
    public Product findById(int id){
        return ProductMapper.findById(id);
    }

    // 模糊查询
    @Override
    public PageInfo<Product> findByNameLike(String name,  int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = ProductMapper.findByNameLike(name);
        return new PageInfo<>(list);
    }


    @Override
    public int update(Product product){
        return ProductMapper.update(product);
    }

    @Override
    public int delete(int id){
        return ProductMapper.deleteById(id);
    }


    @Override
    public void saveProduct(Product product){
        ProductMapper.insertProduct(product);
    }

    @Override
    public List<Product> findByCategory(String category){
        return ProductMapper.findByCategory(category);
    }

    @Override
    public List<Map<String, Object>> getTopSellingProducts(int limit){
        return ProductMapper.getTopSellingProducts(limit);
    }

    @Override
    public PageInfo<Product> selectByPage(int offset, int size){
        PageHelper.startPage(offset, size);
        List<Product> list = ProductMapper.selectByPage(offset, size);
        return new PageInfo<>(list);
    }

    @Override
    public long countAll(){
        return ProductMapper.countAll();
    }
}
