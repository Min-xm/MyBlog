package com.xm.blog.spro.services;

import com.xm.blog.spro.entity.User;
import com.xm.blog.spro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService {

    //博客登录
    public Map<String,Boolean> login(String phone, String password);

    //获取用户信息
    public User getUser(String phone, String password);

    //博客注册
    public boolean register(User user);

    //修改个人信息
    public void updatePerson(User user);

}
