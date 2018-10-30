package com.example.boottest.controller;

import com.example.boottest.common.ResponseEntity;
import com.example.boottest.entity.Dept;
import com.example.boottest.util.Metaspace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 需要设置运行参数(自动)
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=./
 * 或者使用jmap工具导出
 * jmap -dump:format=b,file=heap.hprof 16057
 * 另外
 * jstack pid > pid.txt把线程信息输出到txt文件中
 *
 * @author yibo
 */
@RestController
@RequestMapping("/memory")
public class MemoryController {
    /**
     * -Xmx64m -Xms64m
     * 测试堆内存溢出
     *
     * @return 部门列表
     */
    @GetMapping("/heap")
    public ResponseEntity<List<Dept>> heap() {
        List<Dept> list = new LinkedList<>();
        for (int i = 0; i < 1000000000; i++) {
            list.add(new Dept(new Random().nextInt(100000), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        }
        return new ResponseEntity<>(list);
    }

    /**
     * -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=64m
     * 测试非堆内存溢出
     *
     * @return 类列表
     */
    @GetMapping("/nonHeap")
    public ResponseEntity<List<Class<?>>> nonHeap() {
        List<Class<?>> classes = new LinkedList<>();
        for (int i = 0; i < 1000000; i++) {
            classes.addAll(Metaspace.createClasses());
        }
        return new ResponseEntity<>(classes);
    }
}
