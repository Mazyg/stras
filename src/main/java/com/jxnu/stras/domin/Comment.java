package com.jxnu.stras.domin;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Comment implements Serializable {

    private Integer cid;
    private String date;
    private String content;
    private String phone;
    private String wid;
    private String rphone;
    private User user;
    private User ruser;
    private Dynamic dynamic;
}
