package com.example.boottest.aop;

import com.alibaba.fastjson.JSON;
import com.example.boottest.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAdvice {
    private static final String EMPTY = "";

    @Pointcut("within(com.example.boottest..*)")
    public void logPointcut() {

    }

    @Before("logPointcut()&&@annotation(loggerManager)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManager loggerManager) {
        log.info("执行[{}]开始", loggerManager.description());
        log.info("方法名为:[{}]", joinPoint.getSignature().toShortString());
        log.info("传入参数为:\n{}", parseParams(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "logPointcut()&&@annotation(loggerManager)", returning = "result")
    public void addAfterReturningLogger(LoggerManager loggerManager, Object result) {
        log.info("执行[{}]结束", loggerManager.description());
        log.info("执行结果为:\n{}", JSON.toJSONString(result, CommonConstants.FEATURES));
    }

    @AfterThrowing(pointcut = "logPointcut()&&@annotation(loggerManager)", throwing = "e")
    public void addAfterThrowingLogger(LoggerManager loggerManager, Exception e) {
        log.error("执行[{}]发生异常:{}", loggerManager.description(), e.getMessage());
    }

    private String parseParams(Object[] params) {
        if (null == params) {
            return EMPTY;
        }
        int size = params.length;
        if (size < 1) {
            return EMPTY;
        }
        StringBuilder param = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            param.append(JSON.toJSONString(params[i], CommonConstants.FEATURES)).append(",\n");
        }
        param.append(JSON.toJSONString(params[size - 1], CommonConstants.FEATURES));
        return param.toString();
    }
}