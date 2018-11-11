package com.example.boottest.controller;

import com.example.boottest.common.ResponseEntity;
import com.example.boottest.thread.SyncDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/sync")
public class SyncController {
    @GetMapping("/class")
    public ResponseEntity<Void> sync() {
        SyncDemo syncDemo1 = new SyncDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> syncDemo1.test1(1));
        executorService.execute(() -> SyncDemo.test2(2));
        executorService.execute(() -> syncDemo1.test3(3));
        return new ResponseEntity<>();
    }
}