package com.example.web;


import com.example.mapper.UserMapper;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/hello")
public class UserController2 {


    private UserMapper userMapper;

    public UserMapper getMuserMapper() {
        return userMapper;
    }

    @Autowired
    public void setMuserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @ResponseBody
    @RequestMapping(value = "/world")
    public String hello() {
        List<User> users = userMapper.selectAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return "Hello World";
    }
}
