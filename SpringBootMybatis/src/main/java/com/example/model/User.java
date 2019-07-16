package com.example.model;


import com.example.enums.SexEnum;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;


//@Entity(name = "user")
//@Table(name = "t_user")
//@Alias(value = "user")// MyBatis指定别名
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

}