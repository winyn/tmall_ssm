package com.winn.tmall.service.impl;

import com.winn.tmall.mapper.UserMapper;
import com.winn.tmall.pojo.User;
import com.winn.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 11:38 2018/6/25
 * @ModifiedBy:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> list() {
        return userMapper.selectByExample(null);
    }

    @Override
    public User get(int uid) {
        return userMapper.selectByPrimaryKey(uid);
    }
}
