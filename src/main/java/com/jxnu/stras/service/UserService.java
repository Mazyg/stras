package com.jxnu.stras.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxnu.stras.domin.User;

import java.util.List;

public interface UserService extends IService<User> {

    public User getUserByPhone(String phone);
    public boolean updateUser(User user);
    public boolean updatePhoto(User user);
    public boolean restrictUser(String phone);
    public boolean disenthral(String phone);
}
