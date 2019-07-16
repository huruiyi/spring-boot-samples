package com.example.web;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController1 {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public List<User> users() {
        return userService.findAll();
    }

    @RequestMapping(value = "/showAllUsers")
    @ModelAttribute("users")
    public List<User> mvc1() {
        System.out.println("********************************************");
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("********************************************");
        return userService.findAll();
    }
}
