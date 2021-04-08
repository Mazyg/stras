package com.jxnu.stras.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Cvideo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String vname;
    private String video;
    private Integer cid;

}
