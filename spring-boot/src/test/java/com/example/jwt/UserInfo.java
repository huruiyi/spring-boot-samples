package com.example.jwt;

import lombok.Data;

@Data
public class UserInfo {
  private Long id;
  private String userName;
  private String password;

  public UserInfo() {
  }

  public UserInfo(Long id, String userName, String password) {
    this.id = id;
    this.userName = userName;
    this.password = password;
  }
}
