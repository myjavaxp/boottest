package com.example.boottest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {
    private static final long serialVersionUID = -7283019425712693996L;
    @Min(value = 1L, message = "ID必须大于0")
    private Integer id;
    @NotBlank(message = "名字不能为空")
    private String name;
    @Length(max = 10, message = "备注长度最大为10")
    private String remark;
}