package com.example.demo.stock.entity;

import java.io.Serializable;

public class Product implements Serializable {

  private static final long serialVersionUID = 1L;

  private String productId;
  private String productName;
  private Long stock;

  // getters and setters
  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Long getStock() {
    return stock;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }

  @Override
  public String toString() {
    return "Product{" +
        "productId='" + productId + '\'' +
        ", productName='" + productName + '\'' +
        ", stock=" + stock +
        '}';
  }
}    
