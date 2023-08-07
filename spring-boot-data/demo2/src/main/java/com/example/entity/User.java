package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "User")
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

  private String pSign;

  private boolean disabled;

  public User() {
  }

  public User(Integer id, String name, String password, boolean disabled) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.disabled = disabled;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelPhone() {
    return telPhone;
  }

  public void setTelPhone(String telPhone) {
    this.telPhone = telPhone;
  }

  public String getpSign() {
    return pSign;
  }

  public void setpSign(String pSign) {
    this.pSign = pSign;
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + ", password='" + password + '\'' + ", disabled=" + disabled + '}';
  }
}
