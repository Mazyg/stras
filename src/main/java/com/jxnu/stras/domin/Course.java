package com.jxnu.stras.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
@Data
@ToString
public class Course implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String Tname;
    private String Pname;
    private String Dname;
    private String Tphoto;
    private String course;
    private String Cphoto;
    private String ppt;
    private String designer;
    @TableField("add_title")
    private String addTitle;
    private String add1;
    @TableField("add_title2")
    private String addTitle2;
    private String add2;
    private String Ctitle;
    private String date;
    private String introduce;
}
