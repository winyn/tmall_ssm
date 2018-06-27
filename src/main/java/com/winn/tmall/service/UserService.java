package com.winn.tmall.service;

import com.winn.tmall.pojo.User;

import java.util.List;

/**
 * @Author:Winny
 * @Description:
 * @Date: Create in 16:49 2018/6/13
 * @ModifiedBy:
 */
public interface UserService {
    List<User> list();
    User get(int uid);
}
