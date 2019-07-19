package com.example.model;


import com.example.enums.SexEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class User {

    // 标明主键
    @Id
    // 主键策略，递增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String realName;

    // 定义转换器
    @Convert(converter = SexEnum.class)
    private SexEnum sex = null;

    private String mobile;

    private String email;

    private String note;

    private Integer positionId;

    private List<String> hobbies;

    private Date loginTime;
}