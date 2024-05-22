package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class SystemUser {
  private Integer id;
  private String username;
  private String password;
}
