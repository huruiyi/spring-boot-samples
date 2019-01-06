package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping(value = "/list")
    public List<User> users() {
        return userMapper.selectAllUsers();
    }

    @RequestMapping(value = "/showAllUsers")
    @ModelAttribute("users")
    public List<User> mvc1() {
        return userMapper.selectAllUsers();
    }
}
