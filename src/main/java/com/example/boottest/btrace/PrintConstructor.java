package com.example.boottest.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

@BTrace
public class PrintConstructor {
    /**
     * 拦截方法
     *
     * @param pcn  类名
     * @param pmn  方法名
     * @param args 参数列表，实际上这里可以把实际的方法参数按照原样给拷贝过来
     */
    @OnMethod(
            clazz = "com.example.boottest.entity.Dept",
            method = "<init>"
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
        BTraceUtils.println(pcn + "," + pmn);
        BTraceUtils.printArray(args);
        BTraceUtils.println();
    }
}
