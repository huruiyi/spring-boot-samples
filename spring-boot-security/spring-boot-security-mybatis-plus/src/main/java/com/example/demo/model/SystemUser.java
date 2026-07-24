package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("sys_user")
@Accessors(chain = true)
public class SystemUser {
  private Integer id;
  private String username;
  private String password;
}
