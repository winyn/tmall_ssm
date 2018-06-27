package com.winn.tmall.service;

import com.winn.tmall.pojo.Product;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 16:49 2018/6/13
 * @ModifiedBy:
 */
public interface ProductService {
    List<Product> list(int cid);
    void add(Product product);
    void delete(int id);
    Product get(int id);
    void update(Product product);
}
