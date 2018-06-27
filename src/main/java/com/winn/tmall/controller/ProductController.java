package com.winn.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winn.tmall.pojo.Category;
import com.winn.tmall.pojo.Product;
import com.winn.tmall.service.CategoryService;
import com.winn.tmall.service.ProductService;
import com.winn.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * @Author:Winny
 * @Description:产品控制器，用于产品的CRUD
 * @Date: Create in 17:04 2018/6/13
 * @ModifiedBy:
 */
@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    /**
     * 查询属性列表
     * @param model
     * @param page
     * @param cid 所属产品分类id
     * @return
     */
    @RequestMapping("admin_product_list")
    public String list(Model model,Page page,int cid){
//        根据产品分类页面传进来的id获取所属产品类
        Category c = categoryService.get(cid);
//        根据分类ID获取属性list并分页
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> ps = productService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+cid);
//        查出的属性集合、分页、所属产品类都set到页面
        model.addAttribute("ps",ps);
        model.addAttribute("page",page);
        model.addAttribute("c",c);
//        通过listProduct.jsp展示结果
        return "admin/listProduct";
    }

    @RequestMapping("admin_product_edit")
    public String edit(int id,Model model){
        Product p = productService.get(id);
        Category c = categoryService.get(p.getCid());
        model.addAttribute("p",p);
        model.addAttribute("c",c);
        return "admin/editProduct";
    }

    @RequestMapping("admin_product_add")
    public String add(Product p){
        productService.add(p);
        return "redirect:/admin_product_list?cid="+p.getCid();
    }

    @RequestMapping("admin_product_delete")
    public String delete(int pid,int cid){
        System.out.println("==========cid is "+ cid +"===============");
        productService.delete(pid);
        return "redirect:/admin_product_list?cid="+cid;
    }

    @RequestMapping("admin_product_update")
    public String update(Product p,int cid) {
        productService.update(p);
        return "redirect:/admin_product_list?cid="+cid;
    }

}
