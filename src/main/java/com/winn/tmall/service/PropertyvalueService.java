package com.winn.tmall.service;

import com.winn.tmall.pojo.Propertyvalue;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 16:49 2018/6/13
 * @ModifiedBy:
 */
public interface PropertyvalueService {
    List<Propertyvalue> list(int pid,int cid);
    void update(Propertyvalue propertyvalue);
    void add(Propertyvalue propertyvalue);
}
