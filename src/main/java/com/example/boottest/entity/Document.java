package com.example.boottest.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Document {
    private Integer id;

    private String title;

    private String filename;

    private String remark;

    private Date createDate;

    private Integer userId;
}