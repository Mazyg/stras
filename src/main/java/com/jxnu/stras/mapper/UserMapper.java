package com.jxnu.stras.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxnu.stras.domin.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过手机号查询用户
     * @param phone
     * @return
     */
    @Select("select * from user where phone = #{phone}")
    public User getUserByPhone(String phone);

    @Select("SELECT  uname,password,sex,phone,photo,Utype,Ustatus  FROM user")
    public List<User> findAll();

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return true or false
     */
    @Update("update user set uname= '${uname}', password = '${password}', sex = '${sex}'  where phone = '${phone}'")
    public boolean updateUser(User user);

    /**
     * 更换头像
     * @param user
     * @return
     */
    @Update("update user set photo = '${photo}' where phone = '${phone}'")
    public boolean updatePhoto(User user);

    /**
     * 封号操作
     * @param phone
     * @return true or false
     */
    @Update("update user set Ustatus = '受限' where phone = #{phone}")
    public boolean restrictUser(String phone);


    /**
     * 解封
     * @param phone
     * @return true or false
     */
    @Update("update user set Ustatus = '正常' where phone = #{phone}")
    public boolean disenthral(String phone);

}
