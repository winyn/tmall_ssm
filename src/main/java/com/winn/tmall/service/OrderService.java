package com.winn.tmall.service;

import com.winn.tmall.pojo.Order;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 16:49 2018/6/13
 * @ModifiedBy:
 */
public interface OrderService {
    List<Order> list();
    Order get(int id);
}
