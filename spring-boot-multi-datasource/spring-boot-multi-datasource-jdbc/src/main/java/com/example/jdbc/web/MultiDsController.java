package com.example.jdbc.web;

import com.example.jdbc.model.Order;
import com.example.jdbc.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 方式3：纯 JDBC 多数据源演示
 * 通过 @Qualifier 注入不同的 JdbcTemplate 操作不同库
 */
@RestController
public class MultiDsController {

    private final JdbcTemplate orderJdbcTemplate;
    private final JdbcTemplate userJdbcTemplate;

    public MultiDsController(
            @Qualifier("orderJdbcTemplate") JdbcTemplate orderJdbcTemplate,
            @Qualifier("userJdbcTemplate") JdbcTemplate userJdbcTemplate) {
        this.orderJdbcTemplate = orderJdbcTemplate;
        this.userJdbcTemplate = userJdbcTemplate;
    }

    @GetMapping("/all-data")
    public Map<String, Object> getAllData() {
        List<Order> orders = orderJdbcTemplate.query(
                "SELECT id, order_no, product_name FROM t_order",
                new BeanPropertyRowMapper<>(Order.class));

        List<User> users = userJdbcTemplate.query(
                "SELECT id, name, email FROM t_user",
                new BeanPropertyRowMapper<>(User.class));

        Map<String, Object> result = new HashMap<>();
        result.put("orders_from_db_order", orders);
        result.put("users_from_db_user", users);
        return result;
    }
}
