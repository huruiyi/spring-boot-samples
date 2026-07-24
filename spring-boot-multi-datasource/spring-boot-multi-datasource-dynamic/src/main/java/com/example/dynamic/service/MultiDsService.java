package com.example.dynamic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.dynamic.mapper.OrderMapper;
import com.example.dynamic.mapper.UserMapper;
import com.example.dynamic.model.Order;
import com.example.dynamic.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方式4：dynamic-datasource 演示
 * @DS 注解可以在类上也可以加在方法上，方法级优先级高于类级
 */
@Service
public class MultiDsService {

    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    public MultiDsService(UserMapper userMapper, OrderMapper orderMapper) {
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    public Map<String, Object> getAllData() {
        List<User> users = userMapper.selectList(null);     // → slave
        List<Order> orders = orderMapper.selectList(null);  // → master
        Map<String, Object> result = new HashMap<>();
        result.put("users_from_slave", users);
        result.put("orders_from_master", orders);
        return result;
    }

    /**
     * 方法级 @DS 覆盖类级：即使 OrderMapper 默认走 master，
     * 这里显式指定仍然走 master，展示方法级覆盖能力
     */
    @DS("master")
    public List<Order> getOrdersFromMaster() {
        return orderMapper.selectList(null);
    }
}
