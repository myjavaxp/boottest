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
 * linux命令 top -p [pid] -H查看进程内的所有线程
 * 这里的线程id为十进制，对应stack文件里的nid为十六进制，需要转化后观察
 *
 * @author yibo
 */
@RestController
@RequestMapping("/memory")
public class MemoryController {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

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

    /**
     * 死锁演示
     * 可以通过查询jstack导出的文件看死锁
     *
     * @return 空返回体
     */
    @GetMapping("/deadlock")
    public ResponseEntity<Void> deadlock() {
        new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Thread 1 over");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock2) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("Thread 2 over");
                }
            }
        }).start();
        return new ResponseEntity<>();
    }
}
