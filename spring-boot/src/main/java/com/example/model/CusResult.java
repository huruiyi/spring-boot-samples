package com.example.model;

import java.util.List;
import lombok.Data;

@Data
public class CusResult {

  private Integer status;
  private String msg;
  private List<Object> data;

  @Override
  public String toString() {
    return "CusResult{" +
        "status=" + status +
        ", msg='" + msg + '\'' +
        ", data=" + data +
        '}';
  }
}
