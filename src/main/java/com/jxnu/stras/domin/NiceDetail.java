package com.jxnu.stras.domin;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/****
 * 收藏表
 */
@Data
@ToString
public class NiceDetail {
    private String id;
    private String uphone;
    private String contentId;
    private Date createTime;
}
