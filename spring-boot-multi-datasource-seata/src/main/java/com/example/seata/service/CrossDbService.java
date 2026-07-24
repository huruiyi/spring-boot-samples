package com.example.seata.service;

import com.example.seata.order.Order;
import com.example.seata.order.OrderMapper;
import com.example.seata.user.User;
import com.example.seata.user.UserMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

/**
 * 方式7：Seata AT 模式分布式事务演示
 * @GlobalTransactional 替代 @Transactional
 * Seata 通过 TC（事务协调器）管理全局事务，
 * 使用 undo log 实现自动补偿回滚
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
     * Seata 全局事务：同时写两个库
     * 任意一步失败 → TC 通知各 RM 回滚（通过 undo log）
     */
    @GlobalTransactional(name = "create-order-and-user", rollbackFor = Exception.class)
    public void createOrderAndUser(Order order, User user) {
        orderMapper.insert(order);       // → db_order
        userMapper.insert(user);         // → db_user
        // 如果这里抛异常，两个库都自动回滚
    }
}
