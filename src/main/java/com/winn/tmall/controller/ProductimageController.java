package com.winn.tmall.controller;

import com.winn.tmall.pojo.Category;
import com.winn.tmall.pojo.Product;
import com.winn.tmall.pojo.Productimage;
import com.winn.tmall.service.CategoryService;
import com.winn.tmall.service.ProductService;
import com.winn.tmall.service.ProductimageService;
import com.winn.tmall.util.ImageUtil;
import com.winn.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class ProductimageController{
    @Autowired
    CategoryService categoryService;
    @Autowired
   ProductService productService;
    @Autowired
    ProductimageService productimageService;

    String imgpath;
    /**
     * 查询属性列表
     * @param model
     * @param pid 所属产品分类id
     * @return
     */
    @RequestMapping("admin_productImage_list")
    public String list(Model model,int pid){
//        根据pid获取当前产品类
        Product p = productService.get(pid);
        Category c = categoryService.get(p.getCid());
//        根据pid和图片分类type分别查询单个和详情图片集合
        List<Productimage> pgle = productimageService.list(pid,"type_single");
        List<Productimage> pdil = productimageService.list(pid,"type_detail");

        model.addAttribute("pgle",pgle);
        model.addAttribute("pdil",pdil);
        model.addAttribute("p",p);
        model.addAttribute("c",c);
        return "admin/listProductimage";
    }

    @RequestMapping("admin_productImage_add")
    public String add(Productimage p, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        productimageService.add(p);

        File imgFolder = new File(session.getServletContext().getRealPath(getImgpath(p.getType())));
        File file = new File(imgFolder,p.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img,"jpg",file);

        return "redirect:/admin_productImage_list?pid="+p.getPid();
    }

    @RequestMapping("admin_productImage_delete")
    public String delete(int id,int pid,HttpSession session){
        System.out.println("==========pid is "+ pid +"===============");
        String delType = productimageService.get(id).getType();
        productimageService.delete(id);
        File  imageFolder= new File(session.getServletContext().getRealPath(delType));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return "redirect:/admin_productImage_list?pid="+pid;
    }

    public String getImgpath(String type){
        if(type.equals("type_single"))
            imgpath = "img/productSingle";
        else
            imgpath = "img/productDetail";
        return imgpath;
    }

}
