package com.example.jpa.db1;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

/**
 * 订单实体 — 属于数据源1（db_order）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "product_name")
    private String productName;
}
