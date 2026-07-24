package com.example.sharding.service;

import com.example.sharding.mapper.UserMapper;
import com.example.sharding.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ShardingSphere 自动路由：
 * insert → master
 * selectList → slave（轮询 slave0/slave1）
 */
@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /** 读 → 自动走从库 */
    public List<User> listAll() {
        return userMapper.selectList(null);
    }

    /** 写 → 自动走主库 */
    public void addUser(User user) {
        userMapper.insert(user);
    }
}
