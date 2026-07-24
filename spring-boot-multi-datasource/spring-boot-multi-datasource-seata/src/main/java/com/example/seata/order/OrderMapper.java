package com.example.seata.order;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@DS("order")  // 绑定订单库
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
