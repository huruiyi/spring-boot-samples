package com.example.jta.order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO t_order (order_no, product_name) VALUES (#{orderNo}, #{productName})")
    void insert(Order order);
}
