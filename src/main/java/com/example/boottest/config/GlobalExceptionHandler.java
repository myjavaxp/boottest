package com.example.boottest.config;

import com.example.boottest.common.ResponseEntity;
import com.example.boottest.common.Status;
import com.example.boottest.exception.CommonException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Void> errorApiHandler(Exception e) {
        e.printStackTrace();
        if (e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            return new ResponseEntity<>(commonException.getCode(), commonException.getMessage());
        }
        if (e instanceof NullPointerException) {
            return new ResponseEntity<>(Status.NULL_POINTER_EXCEPTION);
        }
        if (e instanceof BadSqlGrammarException) {
            return new ResponseEntity<>(Status.NOT_VALID_SQL);
        }
        if (e instanceof BindException) {
            return getResponse(((BindException) e).getBindingResult());
        }
        if (e instanceof MethodArgumentNotValidException) {
            return getResponse(((MethodArgumentNotValidException) e).getBindingResult());
        }
        return new ResponseEntity<>(BAD_REQUEST.value(), e.getMessage());
    }

    private ResponseEntity<Void> getResponse(BindingResult result) {
        List<FieldError> errors = result.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < errors.size() - 1; i++) {
            stringBuilder.append(errors.get(i).getDefaultMessage()).append(" ");
        }
        stringBuilder.append(errors.get(errors.size() - 1).getDefaultMessage());
        return new ResponseEntity<>(BAD_REQUEST.value(), stringBuilder.toString());
    }
}