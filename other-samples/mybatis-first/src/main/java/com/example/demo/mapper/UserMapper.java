package com.example.demo.mapper;

import com.example.demo.model.User;

import java.util.List;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    List<User> selectAllUsers();
}
