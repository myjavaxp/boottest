package com.example.boottest.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Dept implements Serializable {
    private static final long serialVersionUID = -7283019425712693996L;
    @Min(value = 1L, message = "ID必须大于0")
    private Integer id;
    @NotBlank(message = "名字不能为空")
    private String name;
    @Length(max = 10, message = "备注长度最大为10")
    private String remark;

    public Dept() {
    }

    public Dept(Integer id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}