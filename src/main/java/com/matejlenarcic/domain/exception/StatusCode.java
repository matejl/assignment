package com.matejlenarcic.domain.exception;

public enum StatusCode {
    BAD_REQUEST(400);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
