package com.winn.tmall.controller;


import com.winn.tmall.pojo.Category;
import com.winn.tmall.pojo.Product;
import com.winn.tmall.pojo.Property;
import com.winn.tmall.pojo.Propertyvalue;
import com.winn.tmall.service.CategoryService;
import com.winn.tmall.service.ProductService;
import com.winn.tmall.service.PropertyService;
import com.winn.tmall.service.PropertyvalueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author:Winny
 * @Description:属性值控制器，用于属性的CRUD
 * @Date: Create in 17:04 2018/6/13
 * @ModifiedBy:
 */
@Controller
@RequestMapping("")
public class PropertyvalueController {

    @Autowired
    PropertyvalueService propertyvalueService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    /**
     * 查询属性列表
     * @param model
     * @param pid 所属产品分类id
     * @return
     */
    @RequestMapping("admin_product_editPropertyValue")
    public String list(Model model,int pid,int cid){
//        根据分类ID获取属性list并分页
        List<Propertyvalue> pv = propertyvalueService.list(pid,cid);
        List<Property> pt = propertyService.list(cid);
        Product p = productService.get(pid);
        Category c = categoryService.get(cid);
//        查出的属性集合、分页、所属产品类都set到页面
        model.addAttribute("pv",pv);
        model.addAttribute("pt",pt);
        model.addAttribute("p",p);
        model.addAttribute("c",c);
        return "admin/editpropertyValue";
    }

    @RequestMapping("admin_product_updatePropertyValue")
    private String update(Propertyvalue pv){
        propertyvalueService.update(pv);
        return "success";
    }

}
