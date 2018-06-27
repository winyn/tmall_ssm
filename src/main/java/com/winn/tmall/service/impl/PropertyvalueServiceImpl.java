package com.winn.tmall.service.impl;

import com.winn.tmall.mapper.PropertyvalueMapper;
import com.winn.tmall.pojo.Property;
import com.winn.tmall.pojo.Propertyvalue;
import com.winn.tmall.pojo.PropertyvalueExample;
import com.winn.tmall.service.PropertyService;
import com.winn.tmall.service.PropertyvalueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 16:51 2018/6/13
 * @ModifiedBy:
 */
@Service
public class PropertyvalueServiceImpl implements PropertyvalueService {

    @Autowired
    PropertyvalueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;

    public List<Propertyvalue> list(int pid,int cid) {
        PropertyvalueExample propertyValueExample = new PropertyvalueExample();
//        根据cid查询当前分类下的属性
        List<Property> properties = propertyService.list(cid);
        List<Propertyvalue> propertyValues = new ArrayList<Propertyvalue>();
        for(Property property : properties){
//            todo or 是什么用法？
            propertyValueExample.or().andPidEqualTo(pid).andPtidEqualTo(property.getId());
            propertyValueExample.setOrderByClause("ptid desc");
        }
        propertyValues.addAll(propertyValueMapper.selectByExample(propertyValueExample));
        return propertyValues;
    }
    
    @Override
    public void update(Propertyvalue propertyvalue) {
        propertyValueMapper.updateByPrimaryKey(propertyvalue);
    }

    @Override
    public void add(Propertyvalue propertyvalue) {
        propertyValueMapper.insert(propertyvalue);
    }

}
