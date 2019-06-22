package com.example.boottest.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

import static org.springframework.http.HttpStatus.OK;

/**
 * @param <T> 返回的类型
 * @author Yibo
 */
@Data
@AllArgsConstructor
public class ResponseEntity<T> implements Serializable {
    private static final long serialVersionUID = -5950487642515880219L;
    private int status;
    private String message;
    private T data;

    public ResponseEntity() {
        this(OK.value(), OK.getReasonPhrase());
    }

    public ResponseEntity(int status, String message) {
        this(status, message, null);
    }

    public ResponseEntity(String message) {
        this(OK.value(), message);
    }

    public ResponseEntity(T data) {
        this(OK.value(), OK.getReasonPhrase(), data);
    }

    public ResponseEntity(Status status) {
        this(status.value(), status.getReasonPhrase());
    }

    public ResponseEntity(HttpStatus httpStatus) {
        this(httpStatus.value(), httpStatus.getReasonPhrase());
    }
}