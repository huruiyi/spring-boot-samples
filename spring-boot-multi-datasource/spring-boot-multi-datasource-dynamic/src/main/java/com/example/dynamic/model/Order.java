package com.example.dynamic.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_order")
public class Order {
    private Long id;
    private String orderNo;
    private String productName;
}
