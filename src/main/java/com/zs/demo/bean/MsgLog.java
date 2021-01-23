package com.zs.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 么么么哒
 * @create 2021/1/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgLog implements Serializable {
    private static final long serialVersionUID = 4985305866762231111L;
    private int id;
    private String type;
    private int type1;
    private String kind;
    private Date date;

}
