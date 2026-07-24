package com.example.jta.order;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private String orderNo;
    private String productName;
}
