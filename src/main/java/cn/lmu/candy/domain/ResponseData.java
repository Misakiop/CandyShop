package cn.lmu.candy.domain;

import lombok.Data;

@Data
public class ResponseData<T> {
    private T data;        //服务器响应数据
    private boolean success; //请求是否成功
    private String msg;       // 错误信息
    private int code = 200;   // 状态码
    private long timestamp; //服务器响应时间

    public ResponseData() {
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public ResponseData(boolean success) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    public ResponseData(boolean success, T data) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.data = data;
    }

    public ResponseData(boolean success, T data, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.data = data;
        this.code = code;
    }

    public ResponseData(boolean success, String msg) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
    }

    public ResponseData(boolean success, String msg, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    public static ResponseData ok() {
        return new ResponseData(true);
    }

    public static <T> ResponseData ok(T data) {
        return new ResponseData(true, data);
    }

    public static <T> ResponseData ok(int code) {
        return new ResponseData(true, null, code);
    }

    public static <T> ResponseData ok(T data, int code) {
        return new ResponseData(true, data, code);
    }

    public static ResponseData fail() {
        return new ResponseData(false);
    }


    public static ResponseData fail(String msg) {
        return new ResponseData(false, msg);
    }

    public static ResponseData fail(int code) {
        return new ResponseData(false, null, code);
    }

    public static ResponseData fail(int code, String msg) {
        return new ResponseData(false, msg, code);
    }
}
