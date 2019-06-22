package com.example.boottest.controller;

import com.example.boottest.aop.LoggerManager;
import com.example.boottest.common.ResponseEntity;
import com.example.boottest.thread.SyncDemo;
import com.example.boottest.thread.UnsafePublish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/sync")
@Slf4j
public class SyncController {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    @GetMapping("/class")
    public ResponseEntity<Void> sync() {
        SyncDemo syncDemo1 = new SyncDemo();
        EXECUTOR_SERVICE.execute(() -> syncDemo1.test1(1));
        EXECUTOR_SERVICE.execute(() -> SyncDemo.test2(2));
        EXECUTOR_SERVICE.execute(() -> syncDemo1.test3(3));
        return new ResponseEntity<>();
    }

    @GetMapping("/publish")
    @LoggerManager(description = "不安全发布对象")
    public ResponseEntity<Void> publish() {
        UnsafePublish publish = new UnsafePublish();
        log.info(Arrays.toString(publish.getArray()));
        String[] array = publish.getArray();
        array[0] = "修改私有域";
        log.info(Arrays.toString(publish.getArray()));
        return new ResponseEntity<>();
    }

    @PreDestroy
    public void destroy() {
        log.info("关闭线程池...");
        EXECUTOR_SERVICE.shutdown();
    }
}