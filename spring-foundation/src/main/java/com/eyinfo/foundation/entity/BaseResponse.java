package com.eyinfo.foundation.entity;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    //返回状态
    private int code = 0;
    //提示消息
    private String msg = "success";

    public BaseResponse() {
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
