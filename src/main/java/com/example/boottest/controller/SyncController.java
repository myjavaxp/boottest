package com.example.boottest.controller;

import com.example.boottest.common.ResponseEntity;
import com.example.boottest.thread.SyncDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/sync")
public class SyncController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncController.class);
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    @GetMapping("/class")
    public ResponseEntity<Void> sync() {
        SyncDemo syncDemo1 = new SyncDemo();
        EXECUTOR_SERVICE.execute(() -> syncDemo1.test1(1));
        EXECUTOR_SERVICE.execute(() -> SyncDemo.test2(2));
        EXECUTOR_SERVICE.execute(() -> syncDemo1.test3(3));
        return new ResponseEntity<>();
    }

    @PreDestroy
    public void destroy() {
        LOGGER.info("关闭线程池...");
        EXECUTOR_SERVICE.shutdown();
    }
}