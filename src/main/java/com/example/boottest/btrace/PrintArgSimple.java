package com.example.boottest.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * https://github.com/btraceio/btrace
 * 其实是可以另启一个工程，写在一个工程只是为了自动提示
 * 安装本地jar包命令，boot client agent三个jar包
 * 版本号和btrace一致。
 * mvn install:install-file -Dfile=/Users/yibo/btrace-bin-1.3.11.1/build/btrace-client.jar -DgroupId=com.sun.tools.btrace -DartifactId=btrace-client -Dversion=1.3.11.1 -Dpackaging=jar
 */
@BTrace
public class PrintArgSimple {
    @OnMethod(
            clazz = "com.example.boottest.controller.DeptController",
            method = "getDeptById",
            location = @Location(Kind.ENTRY)
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
        BTraceUtils.printArray(args);
        BTraceUtils.println(pcn + "," + pmn);
        BTraceUtils.println();
    }
}
