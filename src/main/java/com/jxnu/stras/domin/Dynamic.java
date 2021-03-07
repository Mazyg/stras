package com.jxnu.stras.domin;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class Dynamic implements Serializable {
    private Integer wid;
    private String date;
    private String content;
    private String phone;
    private Integer tid;
    private User user;
    List<Comment> comments;
}
