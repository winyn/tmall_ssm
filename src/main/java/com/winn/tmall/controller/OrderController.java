package com.winn.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winn.tmall.pojo.Order;
import com.winn.tmall.service.OrderService;
import com.winn.tmall.service.OrderitemService;
import com.winn.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 13:50 2018/6/25
 * @ModifiedBy:
 */
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderitemService orderitemService;

    @RequestMapping("admin_order_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Order> os = orderService.list();
        int total = (int) new PageInfo<>(os).getTotal();
        page.setTotal(total);

        for(Order o : os){
            orderitemService.fill(o);
        }

        model.addAttribute("os",os);
        return "admin/listOrder";
    }

}
