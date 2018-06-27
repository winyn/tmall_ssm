package com.winn.tmall.service.impl;

import com.winn.tmall.mapper.OrderMapper;
import com.winn.tmall.pojo.Order;
import com.winn.tmall.pojo.OrderExample;
import com.winn.tmall.pojo.User;
import com.winn.tmall.service.OrderService;
import com.winn.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 13:48 2018/6/25
 * @ModifiedBy:
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;

    @Override
    public List<Order> list() {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause("id desc");
        List<Order> os = orderMapper.selectByExample(orderExample);

        /*给订单的User属性赋值，以便页面获取当前订单的买家名称*/
        setUser(os);
        return os;
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    private void setUser(Order order){
        User user = userService.get(order.getUid());
        order.setUser(user);
    }

    public void setUser(List<Order> os){
        for(Order o : os){
           setUser(o);
        }
    }
}
