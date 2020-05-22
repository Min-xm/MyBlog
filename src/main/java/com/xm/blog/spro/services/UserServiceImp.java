package com.xm.blog.spro.services;

import com.xm.blog.spro.entity.Article;
import com.xm.blog.spro.entity.User;
import com.xm.blog.spro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService{
    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Boolean> login(String phone, String password) {
        //用于存储登录信息: isPassword：密码是否正确、isPhone：手机号是否存在、isAdmin：是否是管理员账号
        Map<String, Boolean> map = new HashMap();
        //user1先设置手机号，用于手机号是否存在的检测
        User user1 = new User();
        user1.setPhone(phone);
        List<User> user2 = userMapper.selectAll(user1);
        //先判断手机号是否存在
        if(user2.size() != 0){
            map.put("isPhone", true);
            //如果手机号存在，则继续设置密码，用于判断密码是否正确
            user1.setPassword(password);
            List<User> user3 = userMapper.selectAll(user1);
            //判断是否是管理员
            if(user2.get(0).getId() == 1){
                map.put("isAdmin", true);
                //判断密码是否正确
                if(user3.size() != 0){
                    map.put("isPassword", true);
                }else{
                    map.put("isPassword", false);
                }
            }else{
                map.put("isAdmin", false);
                //判断密码是否正确
                if(user3.size() != 0){
                    map.put("isPassword", true);
                }else{
                    map.put("isPassword", false);
                }
            }
            return map;
        }else {
            map.put("isPhone", false);
            return map;
        }
    }

    @Override
    public User getUser(String phone, String password) {
        User user = userMapper.selectPhoneAndPassword(phone, password);
        return user;
    }

    //用户注册
    @Override
    public boolean register(User user) {
        User u1 = new User();
        u1.setPhone(user.getPhone());
        List<User> u = userMapper.selectAll(u1);
        //先判断手机号是否存在
        if(u.size() == 0){
            //注册用户
            userMapper.register(user);
            return true;
        }
        return false;
    }

    //修改个人信息
    @Override
    public void updatePerson(User user) {
        userMapper.updatePerson(user);
    }

}
