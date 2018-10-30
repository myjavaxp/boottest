package com.example.boottest.config;

import com.example.boottest.common.ResponseEntity;
import com.example.boottest.common.Status;
import com.example.boottest.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler implements ErrorController {
    private static final String ERROR_PATH = "/error";
    private final ErrorAttributes errorAttributes;

    @Autowired
    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @GetMapping(ERROR_PATH)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> errorApiHandler(HttpServletRequest request, Exception e) {
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
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            BindingResult result = exception.getBindingResult();
            List<FieldError> errors = result.getFieldErrors();
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError error : errors) {
                stringBuilder.append(error.getDefaultMessage()).append(" ");
            }
            return new ResponseEntity<>(99999, stringBuilder.toString());
        }
        WebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(webRequest, false);
        int statusCode = getStatus(request);
        Object error = errorAttributes.get("error");
        if (error != null && !"None".equals(error.toString())) {
            return new ResponseEntity<>(statusCode, error.toString());
        }
        return new ResponseEntity<>(statusCode, String.valueOf(errorAttributes.getOrDefault("message", "error")));
    }

    private Integer getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return INTERNAL_SERVER_ERROR.value();
        }
        return statusCode;
    }
}