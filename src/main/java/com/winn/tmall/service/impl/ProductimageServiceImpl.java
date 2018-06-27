package com.winn.tmall.service.impl;


import com.winn.tmall.mapper.ProductimageMapper;
import com.winn.tmall.pojo.Productimage;
import com.winn.tmall.pojo.ProductimageExample;
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
public class ProductimageServiceImpl implements ProductimageService {
    @Autowired
    ProductimageMapper productimageMapper;
    public List<Productimage> list(int pid,String type) {
        ProductimageExample productimageExample = new ProductimageExample();
//        拼接两个条件：pid和type
        productimageExample.createCriteria().andPidEqualTo(pid).andTypeEqualTo(type);
        productimageExample.setOrderByClause("id desc");
        return productimageMapper.selectByExample(productimageExample);
    }

    @Override
    public void add(Productimage productimage) {
        productimageMapper.insert(productimage);
    }

    @Override
    public void delete(int id) {
        productimageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Productimage get(int id) {
        return productimageMapper.selectByPrimaryKey(id);
    }

}
