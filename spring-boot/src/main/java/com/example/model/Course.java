package com.example.model;

import lombok.Data;

@Data
public class Course {
  private long id;
  private String name;
  private String author;

  public Course(long id, String name, String author) {
    this.id = id;
    this.name = name;
    this.author = author;
  }
}
