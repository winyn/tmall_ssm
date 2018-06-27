package com.winn.tmall.service;

import com.winn.tmall.pojo.Property;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 16:49 2018/6/13
 * @ModifiedBy:
 */
public interface PropertyService {
    List<Property> list(int cid);
    void add(Property property);
    void delete(int id);
    Property get(int id);
    void update(Property property);
}
