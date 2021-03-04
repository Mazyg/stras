package com.jxnu.stras.domin;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Component
public class Topic  implements Serializable {
    private Integer tid;
    private String date;
    private String content;
    private String phone;
    private String Tstatus;
    private String Ttitle;
    private String Ttype;
    private Integer Tview;
    private String Tresult;
    private Integer del;
    private User user;
}
