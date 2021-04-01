package com.jxnu.stras.domin;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/****
 * 收藏表
 */
@Data
@ToString
public class NiceInfo {
    private Integer id;
    private String uPhone;
    private String contentId;
    private Date createTime;
}
