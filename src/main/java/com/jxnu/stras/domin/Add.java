package com.jxnu.stras.domin;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 附件表
 */
@Data
@ToString
public class Add implements Serializable {
    private Integer id;
    private String title;
    private String link;
}
