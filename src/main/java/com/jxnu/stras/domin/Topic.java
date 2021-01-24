package com.jxnu.stras.domin;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Topic  implements Serializable {
    private Integer tid;
    private String date;
    private String content;
    private String Uphone;
    private String Tstatus;
    private String Ttitle;
    private String Ttype;
    private Integer Tview;
    private String Tresult;
    private Integer del;
}
