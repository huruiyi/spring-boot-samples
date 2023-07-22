package com.example.demo1.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class ProductCategory {

  @Id
  private String id;
  private String name;
  private String title;
  private String description;
  private String imgUrl;
}