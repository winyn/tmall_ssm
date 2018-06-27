package com.winn.tmall.service.impl;

import com.winn.tmall.mapper.PropertyMapper;
import com.winn.tmall.pojo.Property;
import com.winn.tmall.pojo.PropertyExample;
import com.winn.tmall.service.PropertyService;
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
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper PropertyMapper;
    public List<Property> list(int cid) {
        PropertyExample propertyExample = new PropertyExample();
        propertyExample.createCriteria().andCidEqualTo(cid);
        propertyExample.setOrderByClause("id desc");
        return PropertyMapper.selectByExample(propertyExample);
    }

    @Override
    public void add(Property property) {
        PropertyMapper.insert(property);
    }

    @Override
    public void delete(int id) {
        PropertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Property get(int id) {
        return PropertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Property property) {
        PropertyMapper.updateByPrimaryKey(property);
    }
}
