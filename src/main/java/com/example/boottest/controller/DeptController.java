package com.example.boottest.controller;

import com.example.boottest.common.ResponseEntity;
import com.example.boottest.entity.Dept;
import com.example.boottest.service.DeptService;
import com.example.boottest.thread.local.RequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private DeptService deptService;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeptController.class);

    @GetMapping("/{id}")
    public ResponseEntity<Dept> getDeptById(@PathVariable("id") int id) {
        LOGGER.info("id:{}", id);
        return new ResponseEntity<>(deptService.getDeptById(id));
    }

    @GetMapping("/one")
    public ResponseEntity<Dept> getDept() {
        return new ResponseEntity<>(new Dept(1, "22", "33"));
    }

    @GetMapping("/add")
    public ResponseEntity<Dept> getUser(Dept dept) {
        return new ResponseEntity<>(dept);
    }

    @GetMapping("/thread")
    public ResponseEntity<Long> getThread() {
        return new ResponseEntity<>(RequestHolder.getId());
    }
}
