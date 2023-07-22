package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public List<User> findAll() {
    return userMapper.selectAllUsers();
  }

  @Override
  public Boolean add(User user) {
    return userMapper.insertUser(user) > 0;
  }

  @Override
  public User selectById(Integer id) {
    return userMapper.selectByPrimaryKey(id);
  }

}