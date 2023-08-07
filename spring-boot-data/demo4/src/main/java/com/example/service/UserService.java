package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean verifyUser(User user) {
    if (userRepository.findByNameAndPassword(user.getName(), user.getPassword()).isEmpty()) {
      return false;
    } else {
      return true;
    }
  }

  public boolean findByEmail(User user) {
    return userRepository.findByEmail(user.getEmail()).isEmpty();
  }

  public List<User> findByEmail(String email) {
    List<User> list = userRepository.findByEmail(email);
    return list;
  }

  public String registerUser(User user) {
    if (userRepository.findByName(user.getName()).isEmpty()) {
      userRepository.save(user);
      return "用户名  " + user.getName() + " 注册成功";
    } else {
      return "用户名 " + user.getName() + "已被占用！";
    }
  }
}