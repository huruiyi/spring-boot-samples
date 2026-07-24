package com.example.jta.web;

import com.example.jta.order.Order;
import com.example.jta.service.CrossDbService;
import com.example.jta.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CrossDbController {

    private final CrossDbService crossDbService;

    public CrossDbController(CrossDbService crossDbService) {
        this.crossDbService = crossDbService;
    }

    /**
     * 演示跨库事务：一次请求同时写两个库
     * POST /api/cross-tx
     * {"orderNo":"ORD001","productName":"iPhone","name":"张三","email":"zhangsan@test.com"}
     */
    @PostMapping("/cross-tx")
    public Map<String, Object> crossTransaction(@RequestBody Map<String, String> body) {
        Order order = new Order();
        order.setOrderNo(body.get("orderNo"));
        order.setProductName(body.get("productName"));

        User user = new User();
        user.setName(body.get("name"));
        user.setEmail(body.get("email"));

        crossDbService.createOrderAndUser(order, user);

        Map<String, Object> result = new HashMap<>();
        result.put("transaction", "JTA 2PC");
        result.put("order_db", "db_order → t_order 已写入");
        result.put("user_db", "db_user → t_user 已写入");
        result.put("note", "任一失败则全部回滚");
        return result;
    }
}
