package com.example.boottest.btrace;

import com.example.boottest.entity.Dept;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import java.lang.reflect.Field;

@BTrace
public class PrintArgComplex {

    /**
     * 打印复杂参数
     * 这种情况需要把classpath加进去，具体为btrace -cp "[类路径]" [pid] [java脚本]
     *
     * @param pcn  类名
     * @param pmn  方法名
     * @param dept 复杂参数
     */
    @OnMethod(
            clazz = "com.example.boottest.controller.DeptController",
            method = "getUser",
            location = @Location(Kind.ENTRY)
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, Dept dept) {
        //print all fields
        BTraceUtils.printFields(dept);
        //print one field
        Field field = BTraceUtils.field("com.example.boottest.entity.Dept", "name");
        BTraceUtils.println(BTraceUtils.get(field, dept));
        BTraceUtils.println(pcn + "," + pmn);
        BTraceUtils.println();
    }
}
