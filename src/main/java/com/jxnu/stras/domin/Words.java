package com.jxnu.stras.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 文章留言信息
 */
@Data
@ToString
public class Words implements Serializable{
    //编号
    private int lwId;
    //留言人
    private String lwPhone;
    //留言时间
    private String lwDate;
    //留言内容
    private String lwContent;
    //在哪篇文章留言(id)
    private String lwForArticleId;

    // //留言人姓名
    private String lwName;
}
