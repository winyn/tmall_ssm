package com.winn.tmall.mapper;

import com.winn.tmall.pojo.Productimage;
import com.winn.tmall.pojo.ProductimageExample;
import java.util.List;

public interface ProductimageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Productimage record);

    int insertSelective(Productimage record);

    List<Productimage> selectByExample(ProductimageExample example);

    Productimage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Productimage record);

    int updateByPrimaryKey(Productimage record);
}