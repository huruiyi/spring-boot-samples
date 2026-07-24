package com.example.dynamic.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
    private Long id;
    private String name;
    private String email;
}
