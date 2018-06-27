package com.winn.tmall.mapper;

import com.winn.tmall.pojo.Propertyvalue;
import com.winn.tmall.pojo.PropertyvalueExample;
import java.util.List;

public interface PropertyvalueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Propertyvalue record);

    int insertSelective(Propertyvalue record);

    List<Propertyvalue> selectByExample(PropertyvalueExample example);

    Propertyvalue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Propertyvalue record);

    int updateByPrimaryKey(Propertyvalue record);
}