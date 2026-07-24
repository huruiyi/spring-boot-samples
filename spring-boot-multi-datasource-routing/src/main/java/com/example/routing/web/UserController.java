package com.example.routing.web;

import com.example.routing.model.User;
import com.example.routing.service.UserService;
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

    /** 读操作 → 走 slave */
    @GetMapping
    public Map<String, Object> list() {
        List<User> users = userService.findAll();
        Map<String, Object> result = new HashMap<>();
        result.put("source", "slave (read)");
        result.put("users", users);
        return result;
    }

    /** 写操作 → 走 master */
    @PostMapping
    public Map<String, Object> add(@RequestBody User user) {
        userService.addUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("source", "master (write)");
        result.put("message", "User added");
        return result;
    }
}
