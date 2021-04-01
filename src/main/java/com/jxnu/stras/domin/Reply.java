package com.jxnu.stras.domin;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文章留言回复
 */
@Data
@ToString
public class Reply{

    //回复信息编号
    private int lrId;
    //回复人
    private String lrPhone;
    //回复时间
    private String lrDate;
    //回复内容
    private String lrContent;
    //给谁回复
    private String lrForPhone;
    //哪条留下的回复言
    private String lrForWords;
    //给哪条回复信息回复的
    private String lrForReply;
    //在哪篇文章下的回复
    private String lrForArticleId;

    //回复人名
    private String lrName;
    //给谁回复
    private String lrForName;
}
