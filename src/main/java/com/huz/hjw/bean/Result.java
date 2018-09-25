package com.huz.hjw.bean;

import java.util.List;

/**
 * Created by hasee on 2018/4/6.
 * 返回的http请求的最外层
 */
public class Result<T> {
    /**编码.*/
    private Integer code;
    /**提示.*/
    private Object msg;
    /**提示.*/
    private Boolean success;
    /**内容.*/
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg=" + msg +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
