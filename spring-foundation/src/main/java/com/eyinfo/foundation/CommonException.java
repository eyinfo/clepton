package com.eyinfo.foundation;

public class CommonException extends RuntimeException {
    private int code = 400;

    public int getCode() {
        return code;
    }

    public CommonException(Throwable throwable) {
        super(throwable);
    }
}
