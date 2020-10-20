package com.example.web;

import com.example.enums.SexEnum;
import com.example.model.User;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/user1")
public class UserController {

    /**
     * 属性注入
     */
    @Autowired
    private UserService userService1;


    private UserService userService2;

    /**
     * 构造函数注入
     *
     * @param userService
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService2 = userService;
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/list1")
    public List<User> users() {
        PageHelper.startPage(2, 3);
        return userService1.findAll();
    }

    @RequestMapping(value = "/list2")
    public String list() {
        List<User> users = userService2.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        return "Hello World";
    }


    @RequestMapping(value = "/showAllUsers")
    @ModelAttribute("users")
    public List<User> mvc1() {
        System.out.println("********************************************");
        List<User> users = userService1.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("********************************************");
        return userService1.findAll();
    }

    @RequestMapping(value = "/addUser")
    public void addUser() {
        User user = new User();
        user.setSex(SexEnum.FEMALE);
        user.setUserName("hello");
        user.setLoginTime(new Date());

        List<String> hobbies = new ArrayList<>();
        hobbies.add("play computer games");
        hobbies.add("listen to music");
        user.setHobbies(hobbies);
        Boolean ret = userService1.add(user);
        System.out.println(ret);
    }

    @RequestMapping(value = "/getUser")
    public void getUser(@RequestParam(value = "id", required = true) Integer id) {
        User user = userService1.selectById(id);
        System.out.println(user.toString());
    }
}
