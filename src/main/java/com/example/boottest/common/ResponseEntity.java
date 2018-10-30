package com.example.boottest.common;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.StringJoiner;

import static org.springframework.http.HttpStatus.OK;

/**
 * @param <T> 返回的类型
 * @author Yibo
 */
public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = -5950487642515880219L;
    private int status;
    private String message;
    private T data;

    public ResponseEntity() {
        this.status = OK.value();
        this.message = OK.getReasonPhrase();
    }

    public ResponseEntity(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseEntity(String message) {
        this.status = OK.value();
        this.message = message;
    }

    public ResponseEntity(T data) {
        this.status = OK.value();
        this.message = OK.getReasonPhrase();
        this.data = data;
    }

    public ResponseEntity(Status status) {
        this.status = status.value();
        this.message = status.getReasonPhrase();
    }

    public ResponseEntity(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ResponseEntity.class.getSimpleName() + "[", "]")
                .add("status=" + status)
                .add("message='" + message + "'")
                .add("data=" + data)
                .toString();
    }
}