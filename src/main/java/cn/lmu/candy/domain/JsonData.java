package cn.lmu.candy.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonData implements Serializable {
    private static final long serialVersionUID = 1L;
    //状态码,0表示成功，-1表示失败
    private int code;
    //结果
    private Object data;
    //信息描述
    private String msg;

    public JsonData(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public JsonData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
