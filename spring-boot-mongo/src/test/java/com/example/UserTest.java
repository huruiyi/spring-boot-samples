package com.example;


import com.example.entity.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void findAllUsers() {
    List<User> users = userRepository.findAll();
    assertNotNull(users);
    assertTrue(!users.isEmpty());
    System.out.println("********************************************************************************");
    System.out.println(users);
    System.out.println("********************************************************************************");
  }

  @Test
  public void findUserById() {
    User user = userRepository.findById("1").get();
    assertNotNull(user);
    System.out.println("********************************************************************************");
    System.out.println(user);
    System.out.println("********************************************************************************");
  }

  @Test
  public void createUser() {
    User user = new User("3", "Joseph", "joseph@gmail.com");
    User savedUser = userRepository.save(user);
    User newUser = userRepository.findById(savedUser.getId()).get();
    assertEquals("Joseph", newUser.getName());
    assertEquals("joseph@gmail.com", newUser.getEmail());
    System.out.println("********************************************************************************");
    System.out.println(savedUser);
    System.out.println(newUser);
    System.out.println("********************************************************************************");
  }

  @Test
  public void findUserByName() {
    User user = userRepository.findByUserName("Robert");
    assertNotNull(user);
  }
}
