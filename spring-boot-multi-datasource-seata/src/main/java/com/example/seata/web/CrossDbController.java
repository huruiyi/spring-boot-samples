package com.example.seata.web;

import com.example.seata.order.Order;
import com.example.seata.service.CrossDbService;
import com.example.seata.user.User;
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
     * Seata AT 模式跨库事务演示
     * POST /api/cross-tx
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
        result.put("transaction", "Seata AT (GlobalTransactional)");
        result.put("order_db", "db_order → t_order");
        result.put("user_db", "db_user → t_user");
        result.put("rollback_mechanism", "undo log 自动补偿");
        result.put("note", "需启动 seata-server");
        return result;
    }
}
