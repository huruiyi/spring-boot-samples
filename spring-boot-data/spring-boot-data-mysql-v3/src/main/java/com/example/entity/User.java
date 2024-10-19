package com.example.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  private String sex;

  private String email;

  private String telPhone;

  private String personalSign;

  private boolean disabled;

  public User() {
  }

  public User(Integer id, String name, String password, boolean disabled) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.disabled = disabled;
  }


  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + ", password='" + password + '\'' + ", disabled=" + disabled + '}';
  }
}
