package com.example.boottest.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

@BTrace
public class PrintLine {
    /**
     * 拦截行号
     *
     * @param pcn  类名
     * @param pmn  方法名
     * @param line =-1的话，表示打印该方法所有行
     */
    @OnMethod(
            clazz = "com.example.boottest.controller.DownloadController",
            method = "excel",
            location = @Location(value = Kind.LINE, line = -1)
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, int line) {
        BTraceUtils.println(pcn + "," + pmn + "," + line);
        BTraceUtils.println();
    }
}
