package com.example.model;


import com.example.converter.SexConverter;
import com.example.enums.SexEnum;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "t_user") //映射的表名称
public class User {

    // 标明主键
    @Id
    // 主键策略，递增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    // 定义转换器
    @Convert(converter = SexConverter.class)
    private SexEnum sex;

    private String mobile;

    private String email;

    private String note;

    private Integer positionId;

    private List<String> hobbies;

    private Date loginTime;
}