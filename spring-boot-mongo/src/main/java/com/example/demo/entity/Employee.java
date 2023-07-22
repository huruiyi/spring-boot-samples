package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
public class Employee {

  @Id
  private String id;
  private Integer registration;
  private String name;

  public Employee(String id, Integer registration, String name) {
    super();
    this.id = id;
    this.registration = registration;
    this.name = name;
  }

  public Employee(String name) {
    super();
    this.name = name;
  }

}
