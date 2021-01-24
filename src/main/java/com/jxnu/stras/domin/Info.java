package com.jxnu.stras.domin;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * introduce : 文章简介
 * content : 文章内容
 *  date：发布时间
 *  photo：文章封面
 *  nice：点赞数
 *  view：浏览量
 *  infoDels:文章状态（1文章已删除，0文章未删除）
 *  Uphone ：用户手机号
 *  infoStatus：文章是否通过审核
 *  Utype:文章发布者类型
 */
@Data
@ToString
public class Info implements Serializable {

    private Integer infoId;
    private String title;
    private String infoType;
    private String introduce;
    private String content;
    private String date;
    private String photo;
    private Integer nice;
    private Integer view;
    private String infoStatus;
    private String Uphone;
    private Integer infoDel;
    private String Utype;
}
