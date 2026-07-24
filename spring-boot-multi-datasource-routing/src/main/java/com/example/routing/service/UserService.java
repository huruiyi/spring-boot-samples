package com.example.routing.service;

import com.example.routing.annotation.TargetDataSource;
import com.example.routing.mapper.UserMapper;
import com.example.routing.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 方式2：AbstractRoutingDataSource 演示
 * @TargetDataSource("slave") → 读库
 * @TargetDataSource("master") → 写库
 */
@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /** 查询走从库 */
    @TargetDataSource("slave")
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /** 写入走主库 */
    @TargetDataSource("master")
    public void addUser(User user) {
        userMapper.insert(user);
    }
}
