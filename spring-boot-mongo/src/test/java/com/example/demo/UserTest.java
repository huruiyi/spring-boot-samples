package com.example.demo;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
