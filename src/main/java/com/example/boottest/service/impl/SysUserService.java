package com.example.boottest.service.impl;

import com.example.boottest.dao.SysUserDao;
import com.example.boottest.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

public class SysUserService implements UserDetailsService {
    @Resource
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.selectByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名: " + username + " 不存在");
        }
        return sysUser;
    }
}