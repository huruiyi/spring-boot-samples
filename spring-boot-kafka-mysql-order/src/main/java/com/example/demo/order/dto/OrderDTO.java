package com.example.demo.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String orderNumber;
  private String userId;
  private BigDecimal totalAmount;
  private String status;
  private LocalDateTime createTime;

  // getters and setters
  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "OrderDTO{" +
        "orderNumber='" + orderNumber + '\'' +
        ", userId='" + userId + '\'' +
        ", totalAmount=" + totalAmount +
        ", status='" + status + '\'' +
        ", createTime=" + createTime +
        '}';
  }
}    
