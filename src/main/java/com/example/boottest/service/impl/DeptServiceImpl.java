package com.example.boottest.service.impl;

import com.example.boottest.dao.DeptDao;
import com.example.boottest.entity.Dept;
import com.example.boottest.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;

    @Override
    public Dept getDeptById(int id) {
        return deptDao.selectByPrimaryKey(id);
    }
}
