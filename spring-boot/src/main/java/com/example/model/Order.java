package com.example.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Builder
public class Order {

  private Integer id;

  private Date orderDate;

  private Integer quantity;

  private Customer customer;

  private String productName;

}
