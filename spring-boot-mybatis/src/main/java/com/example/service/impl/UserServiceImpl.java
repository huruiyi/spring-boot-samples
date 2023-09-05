package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

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
