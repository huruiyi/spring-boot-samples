package com.example.demo.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String orderId;
  private String userId;
  private BigDecimal amount;
  private LocalDateTime createTime;
  private String status;

  // getters and setters
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "OrderDTO{" +
        "orderId='" + orderId + '\'' +
        ", userId='" + userId + '\'' +
        ", amount=" + amount +
        ", createTime=" + createTime +
        ", status='" + status + '\'' +
        '}';
  }
}    
