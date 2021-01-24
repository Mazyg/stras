package com.jxnu.stras.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxnu.stras.domin.User;
import com.jxnu.stras.mapper.UserMapper;
import com.jxnu.stras.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Resource
    private UserMapper userMapper;


    /**
     * 通过手机号查询用户
     * @param phone 用户手机号也是登录账号
     * @return
     */
    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 更换头像
     * @param user
     * @return
     */
    @Override
    public boolean updatePhoto(User user) {
        return userMapper.updatePhoto(user);
    }

    /**
     * 限制用户
     * @param phone
     * @return
     */
    @Override
    public boolean restrictUser(String phone) {
        return userMapper.restrictUser(phone);
    }

    /**
     * 解封
     * @param phone
     * @return
     */
    @Override
    public boolean disenthral(String phone) {
        return userMapper.disenthral(phone);
    }


}
