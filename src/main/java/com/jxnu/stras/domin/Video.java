package com.jxnu.stras.domin;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 视频表
 * vid 视频id
 * v_title 标题
 * v_introduce 简介
 * v_content 内容
 * v_date 发布时间
 * v_photo 封面地址
 * video 视频地址
 * v_view; 浏览量
 * v_nice; 点赞数
 * v_status 文章状态（1文章已删除，0文章已发布）
 */
@Data
@ToString
public class Video implements Serializable {
    private Integer vid;
    private String Vtitle;
    private String Vintroduce;
    private String Vdate;
    private String Vphoto;
    private String video;
    private Integer Vview;
    private Integer Vnice;
    private Integer Vstatus;
}
