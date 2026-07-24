package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "ID", type = IdType.AUTO)
  private Long id;

  @TableField("name")
  private String name;

  @TableField("password")
  private String password;

  @TableField("createtime")
  private LocalDateTime createTime;

}
