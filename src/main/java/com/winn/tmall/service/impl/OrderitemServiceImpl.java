package com.winn.tmall.service.impl;

import com.winn.tmall.mapper.OrderitemMapper;
import com.winn.tmall.pojo.*;
import com.winn.tmall.service.OrderitemService;
import com.winn.tmall.service.ProductService;
import com.winn.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Winny
 * @Description:根据Order类获取订单详情Orderitem和产品Product 各属性值
 * @Date: Create in 13:48 2018/6/25
 * @ModifiedBy:
 */
@Service
public class OrderitemServiceImpl implements OrderitemService {

    @Autowired
    OrderitemMapper orderitemMapper;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    List<Orderitem> oilist = new ArrayList<>();

    /**
     * 根据传入的Order获取详情Orderitem 中的数量和产品id，再根据pid获取单价
     * @param order
     */
    @Override
    public void fill(Order order) {
        int num = 0;
        float tmoney = 0.00f;

        OrderitemExample orderitemExample = new OrderitemExample();
        orderitemExample.createCriteria().andOidEqualTo(order.getId());
        orderitemExample.setOrderByClause("id desc");
        oilist = orderitemMapper.selectByExample(orderitemExample);

        /*遍历订单详情*/
        for(Orderitem or : oilist){
            /*获取当前购买产品实体并赋值给orderitem*/
            Product p = productService.get(or.getPid());
            or.setProduct(p);

            /*累加计算每笔订单商品总量*/
            num += or.getNumber();

            /*订单总金额+=每笔商品单价*购买数量*/
            Float op = p.getPromotePrice();
            tmoney += or.getNumber()*op;
        }

        order.setOrder_num(num);
        order.setOrder_money(tmoney);
        order.setOrderitems(oilist);
    }
}
