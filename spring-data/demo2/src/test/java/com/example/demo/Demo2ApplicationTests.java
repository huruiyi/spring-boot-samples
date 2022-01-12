package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class Demo2ApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            System.err.println(user);
        }
        assertNotNull(users);
        assertTrue(!users.isEmpty());
    }

    @Test
    public void findUserById() {
        User user = userRepository.getOne(1);
        assertNotNull(user);
    }

    @Test
    public void createUser() {
        User user = new User(null, "Paul", "paul@gmail.com");
        User savedUser = userRepository.save(user);
        User newUser = userRepository.findById(savedUser.getId()).get();
        assertEquals("Paul", newUser.getName());
        assertEquals("paul@gmail.com", newUser.getEmail());
    }

    @Test
    public void getUsersSortByName() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        for (User user : users) {
            System.err.println(user);
        }
        assertNotNull(users);
    }

    @Test
    public void getUsersSortByNameAscAndIdDesc() {
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "name");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(order1, order2);
        List<User> users = userRepository.findAll(sort);
        assertNotNull(users);
    }

    @Test
    public void getUsersByPage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        Page<User> usersPage = userRepository.findAll(pageable);
        System.out.println(usersPage.getTotalElements()); //Returns the total amount of elements.
        System.out.println(usersPage.getTotalPages());//Returns the number of total pages.
        System.out.println(usersPage.hasNext());
        System.out.println(usersPage.hasPrevious());
        List<User> usersList = usersPage.getContent();
        assertNotNull(usersList);
    }
}
