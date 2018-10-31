package com.example.boottest.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

@BTrace
public class PrintReturn {

    @OnMethod(
            clazz = "com.example.boottest.controller.DeptController",
            method = "getDeptById",
            location = @Location(Kind.RETURN)
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, @Return AnyType result) {
        BTraceUtils.println(pcn + "," + pmn + "," + result);
        BTraceUtils.println();
    }
}