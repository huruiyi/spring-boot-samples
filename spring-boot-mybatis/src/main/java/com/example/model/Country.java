package com.example.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "country") //映射的表名称
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String countryname;

  private String countrycode;

}
