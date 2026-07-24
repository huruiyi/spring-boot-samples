package com.example.sharding.web;

import com.example.sharding.model.User;
import com.example.sharding.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** 查询 → ShardingSphere 自动路由到从库（轮询） */
    @GetMapping
    public Map<String, Object> list() {
        List<User> users = userService.listAll();
        Map<String, Object> result = new HashMap<>();
        result.put("route", "slave (round_robin: slave0/slave1)");
        result.put("users", users);
        return result;
    }

    /** 写入 → ShardingSphere 自动路由到主库 */
    @PostMapping
    public Map<String, Object> add(@RequestBody User user) {
        userService.addUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("route", "master (write)");
        result.put("message", "User added");
        return result;
    }
}
