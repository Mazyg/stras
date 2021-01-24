package com.jxnu.stras.domin;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;


/**
 * 用户表（user，admin）
 * phone 为用户账号，唯一标识（主键）
 * u_status(封号，正常两种状态)
 */
@Data
@ToString
public class User implements Serializable{

    private String uname;
    private String password;
    private String sex;
    private String phone;
    private String photo;
    private String Utype;
    private String Ustatus;


}
