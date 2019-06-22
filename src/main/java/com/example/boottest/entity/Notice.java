package com.example.boottest.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private Integer id;

    private String title;

    private Date createDate;

    private Integer userId;

    private String content;
}