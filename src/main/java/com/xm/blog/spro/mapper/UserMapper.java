package com.xm.blog.spro.mapper;

import com.xm.blog.spro.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    //通过id获取角色
    public User selectIdByPrimary(Integer id);
    //通过手机号获取角色
    public User selectPhoneAndPassword(String phone, String password);

    //万能选择
    public List<User> selectAll(User user);

    //修改个人信息
    public void updatePerson(User user);

    //注册新用户
    public void register(User user);
}