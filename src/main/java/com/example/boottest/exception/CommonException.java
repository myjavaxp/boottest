package com.example.boottest.exception;

import com.example.boottest.common.Status;
import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException {
    private static final long serialVersionUID = 3965781030087386799L;
    private int code;

    public CommonException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        this.code = httpStatus.value();
    }

    public CommonException(Status status) {
        super(status.getReasonPhrase());
        this.code = status.value();
    }

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}