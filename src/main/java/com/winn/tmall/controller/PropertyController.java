package com.winn.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winn.tmall.pojo.Category;
import com.winn.tmall.pojo.Property;
import com.winn.tmall.service.CategoryService;
import com.winn.tmall.service.PropertyService;
import com.winn.tmall.util.ImageUtil;
import com.winn.tmall.util.Page;
import com.winn.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author:Winny
 * @Description:属性控制器，用于属性的CRUD
 * @Date: Create in 17:04 2018/6/13
 * @ModifiedBy:
 */
@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;

    /**
     * 查询属性列表
     * @param model
     * @param page
     * @param cid 所属产品分类id
     * @return
     */
    @RequestMapping("admin_property_list")
    public String list(Model model,Page page,int cid){
//        根据产品分类页面传进来的id获取所属产品类
        Category c = categoryService.get(cid);
//        根据分类ID获取属性list并分页
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps = propertyService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+cid);
//        查出的属性集合、分页、所属产品类都set到页面
        model.addAttribute("ps",ps);
        model.addAttribute("page",page);
        model.addAttribute("c",c);
//        通过listProperty.jsp展示结果
        return "admin/listProperty";
    }

    @RequestMapping("admin_property_edit")
    public String edit(int id,Model model){
        Property p = propertyService.get(id);
        Category c = categoryService.get(p.getCid());
        model.addAttribute("p",p);
        model.addAttribute("c",c);
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_add")
    public String add(Property p){
        propertyService.add(p);
        return "redirect:/admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id,int cid){
        System.out.println("==========cid is "+ cid +"===============");
        propertyService.delete(id);
        return "redirect:/admin_property_list?cid="+cid;
    }

    @RequestMapping("admin_property_update")
    public String update(Property p) {
        propertyService.update(p);
        return "redirect:/admin_property_list?cid="+p.getCid();
    }
}
