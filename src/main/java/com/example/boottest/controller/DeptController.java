package com.example.boottest.controller;

import com.example.boottest.aop.LoggerManager;
import com.example.boottest.common.ResponseEntity;
import com.example.boottest.entity.Dept;
import com.example.boottest.service.DeptService;
import com.example.boottest.thread.local.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {
    @Resource
    private DeptService deptService;

    @GetMapping("/{id}")
    public ResponseEntity<Dept> getDeptById(@PathVariable("id") int id) {
        log.info("id:{}", id);
        return new ResponseEntity<>(deptService.getDeptById(id));
    }

    @GetMapping("/one")
    public ResponseEntity<Dept> getDept() {
        return new ResponseEntity<>(new Dept(1, "22", "33"));
    }

    @PostMapping("/add")
    @LoggerManager(description = "添加部门")
    public ResponseEntity<Dept> getUser(@RequestBody @Valid Dept dept) {
        if (dept.getId() == null) {
            throw new RuntimeException("ID不能为空");
        }
        return new ResponseEntity<>(dept);
    }

    @GetMapping("/thread")
    public ResponseEntity<Long> getThread() {
        return new ResponseEntity<>(RequestHolder.getId());
    }
}
