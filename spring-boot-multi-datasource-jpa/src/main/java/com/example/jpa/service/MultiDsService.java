package com.example.jpa.service;

import com.example.jpa.db1.OrderEntity;
import com.example.jpa.db1.OrderRepository;
import com.example.jpa.db2.UserEntity;
import com.example.jpa.db2.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方式8：JPA 多 EntityManager 演示
 * 不同包的 Repository 自动绑定到各自的 EntityManagerFactory
 */
@Service
public class MultiDsService {

    private final OrderRepository orderRepository;   // → db1
    private final UserRepository userRepository;     // → db2

    public MultiDsService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Map<String, Object> getAllData() {
        List<OrderEntity> orders = orderRepository.findAll();
        List<UserEntity> users = userRepository.findAll();
        Map<String, Object> result = new HashMap<>();
        result.put("orders_from_db_order", orders);
        result.put("users_from_db_user", users);
        return result;
    }
}
