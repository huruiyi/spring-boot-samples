package com.example.model;


public class User {

  private Integer id;

  private String userName;

  private Byte sex;

  private String mobile;

  private String email;

  private String note;

  private Integer positionId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Byte getSex() {
    return sex;
  }

  public void setSex(Byte sex) {
    this.sex = sex;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Integer getPositionId() {
    return positionId;
  }

  public void setPositionId(Integer positionId) {
    this.positionId = positionId;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", sex=" + sex +
        ", mobile='" + mobile + '\'' +
        ", email='" + email + '\'' +
        ", note='" + note + '\'' +
        ", positionId=" + positionId +
        '}';
  }
}