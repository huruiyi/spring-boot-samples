package com.example.dynamic.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dynamic.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 Mapper — 固定走 master 数据源（写库）
 */
@DS("master")
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
