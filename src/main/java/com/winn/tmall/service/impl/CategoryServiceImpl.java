package com.winn.tmall.service.impl;

import com.winn.tmall.mapper.CategoryMapper;
import com.winn.tmall.pojo.Category;
import com.winn.tmall.service.CategoryService;
import com.winn.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 16:51 2018/6/13
 * @ModifiedBy:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    public List<Category> list() {
        return categoryMapper.selectByExample(null);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category get(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }
}
