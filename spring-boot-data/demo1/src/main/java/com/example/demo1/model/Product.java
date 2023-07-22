package com.example.demo1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {

  @Id
  private String id;
  private String name;
  private String code;
  private String title;
  private String description;
  private String imgUrl;
  private Double price;
  private String productCategoryName;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null) ? 0 : code.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Product other = (Product) obj;
    if (code == null) {
      if (other.code != null) {
        return false;
      }
    } else if (!code.equals(other.code)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", code='" + code + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", imgUrl='" + imgUrl + '\'' +
        ", price=" + price +
        ", productCategoryName='" + productCategoryName + '\'' +
        '}';
  }
}
