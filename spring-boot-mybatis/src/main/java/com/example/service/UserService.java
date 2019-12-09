package com.example.service;

import com.example.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    Boolean add(User user);

    User selectById(Integer id);
}
