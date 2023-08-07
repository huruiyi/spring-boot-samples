package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Document(collection = "article_collection")
public class Article implements Serializable {

  @Id
  private String id;

  private String title;

  private String description;

  private String by;

  private String url;
}
