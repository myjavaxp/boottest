package com.example.boottest.controller;

import com.example.boottest.component.Sender;
import com.example.boottest.entity.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/kafka")
public class TestKafka {
    @Resource
    private Sender sender;

    @GetMapping("/sendMsg")
    public Message sendMsg() {
        sender.sendMessage();
        return new Message();
    }
}