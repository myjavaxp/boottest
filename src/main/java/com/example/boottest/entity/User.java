package com.example.boottest.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String loginName;

    private String password;

    private Integer status;

    private Date createDate;

    private String username;
}