package com.winn.tmall.service.impl;

import com.winn.tmall.mapper.ProductMapper;
import com.winn.tmall.mapper.ProductimageMapper;
import com.winn.tmall.pojo.Product;
import com.winn.tmall.pojo.ProductExample;
import com.winn.tmall.pojo.Productimage;
import com.winn.tmall.pojo.ProductimageExample;
import com.winn.tmall.service.ProductService;
import com.winn.tmall.service.ProductimageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 16:51 2018/6/13
 * @ModifiedBy:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductimageMapper productimageMapper;
    @Autowired
    ProductimageService productimageService;

    public List<Product> list(int cid) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andCidEqualTo(cid);
        productExample.setOrderByClause("id desc");
        List<Product> result = productMapper.selectByExample(productExample);
        /*绑定缩略图*/
        bindImage(result);

        return result;
    }

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        bindImage(product);
        return product;
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    /**
     * 给每个产品绑定一张缩略图
     * 根据pid和type=single查询productImage第一条赋值给firstProductImage，在批量查询和单条查询产品时都需要此方法
     * @param p
     */
    public void bindImage(Product p){
        List<Productimage> pimglist = productimageService.list(p.getId(),"type_single");
//        todo 集合get(0)是否需要 try catch?
        try {
            p.setFirstProductImage(pimglist.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bindImage(List<Product> productList){
        for(Product p : productList){
            bindImage(p);
        }
    }

}
