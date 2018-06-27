package com.winn.tmall.service;

import com.winn.tmall.pojo.Productimage;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 16:49 2018/6/13
 * @ModifiedBy:
 */
public interface ProductimageService {
    List<Productimage> list(int pid,String type);
    void add(Productimage productimage);
    void delete(int id);
    Productimage get(int id);
}
