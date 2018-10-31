package com.example.boottest.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;

@BTrace
public class PrintJinfo {
    static {
        BTraceUtils.println("System Properties:");
        BTraceUtils.printProperties();
        BTraceUtils.println("VM Flags:");
        BTraceUtils.printVmArguments();
        BTraceUtils.println("OS Environment:");
        BTraceUtils.printEnv();
        BTraceUtils.exit(0);
    }
}