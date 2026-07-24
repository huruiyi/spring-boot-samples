package com.example.jta.service;

import com.example.jta.order.Order;
import com.example.jta.order.OrderMapper;
import com.example.jta.user.User;
import com.example.jta.user.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 方式6：Atomikos JTA 分布式事务演示
 * 单个 @Transactional 即可包裹两个不同库的写入操作
 * 要么全部提交，要么全部回滚（2PC）
 */
@Service
public class CrossDbService {

    private final OrderMapper orderMapper;
    private final UserMapper userMapper;

    public CrossDbService(OrderMapper orderMapper, UserMapper userMapper) {
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
    }

    /**
     * 跨库事务：同时写订单库和用户库
     * 如果任意一步失败，两个库都回滚
     */
    @Transactional
    public void createOrderAndUser(Order order, User user) {
        orderMapper.insert(order);    // → db_order（订单库）
        userMapper.insert(user);      // → db_user（用户库）
    }
}
