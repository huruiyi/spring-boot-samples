package com.example.web;

import java.util.List;

import com.example.bean.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/student")
public class    StudentV2Controller {

    private static final Logger log = LoggerFactory.getLogger(StudentV2Controller.class);

    @Autowired
    public UserService userService;


    /*
         返回字符串
    */

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String greet() {
        return "Hello World";
    }


    /*
         返回视图页
    */
    @RequestMapping(value = "/index")
    public String index() {
        return "/student/index";
    }


    @RequestMapping(value = "/showOneUser")
    public User showOneUser() {
        User user = userService.findOneUser();
        return user;
    }

    @RequestMapping(value = "/showAllUsers")
    @ModelAttribute("users")
    public List<User> showAllUsers() {
        List<User> users = userService.findAllUsers();
        return users;
    }

    @RequestMapping(value = "/showAllUsers2")
    @ModelAttribute("users")
    public List<User> showAllUsers2() {
        List<User> users = userService.findAllUsers();
        return users;
    }
}
