package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public List<User> getAllUsers() {
    return this.userRepository.findAll();
  }

  public User addUser(User user) {
    return this.userRepository.save(user);
  }

  public Optional<User> getUserById(int id) {
    return this.userRepository.findById(id);
  }

  public User updateUser(User user) {
    return this.userRepository.save(user);
  }

  public void deleteUserById(int id) {
    this.userRepository.deleteById(id);
  }

  public void deleteAllUsers() {
    this.userRepository.deleteAll();
  }


}
