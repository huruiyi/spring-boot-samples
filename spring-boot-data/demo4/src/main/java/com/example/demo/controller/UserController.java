package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    @ResponseBody
    String welcome() {
        return "welcome my first spring boot project";
    }

    @RequestMapping("/notVerify")
    @ResponseBody
    String notVerify() {
        return "username or password NOT correct";
    }

    @RequestMapping("/loginPage")
    String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/register")
    String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    boolean userLogin(@RequestBody User user, Model model) {
        model.addAttribute("name", user.getName());
        model.addAttribute("password", user.getPassword());
        return userService.verifyUser(user);

    }


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    List<User> get(@RequestBody User user, Model model) {
        List<User> list = userService.findByEmail(user.getEmail());
        return list;

    }

}