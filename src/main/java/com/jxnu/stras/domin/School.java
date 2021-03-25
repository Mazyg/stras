package com.jxnu.stras.domin;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 学校表
 */
@Data
@ToString
public class School implements Serializable {
    private Integer id;
    private String title;
    private String introduction;
    private String photo;
}
