package com.example.web;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    @RequestMapping(value = "/addUser")
    public void addUser() {
        User user = new User();
        user.setUserName("hello");
        Date date = new Date();

        user.setLoginTime(date);

        List<String> hobbies = new ArrayList<>();
        hobbies.add("play computer games");
        hobbies.add("listen to music");
        user.setHobbies(hobbies);
        Boolean ret = userService.add(user);
        System.out.println(ret);
    }

    @RequestMapping(value = "/getUser")
    public void getUser(@RequestParam(value = "id", required = true) Integer id) {
        User user = userService.selectById(id);
        System.out.println(user.toString());
    }
}
