package com.example.web;

import com.example.enums.SexEnum;
import com.example.model.Country;
import com.example.model.User;
import com.example.service.CountryService;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 属性注入
     */
    @Autowired
    private UserService userService1;

    @Autowired
    private CountryService countryService;

    private UserService userService2;

    /**
     * set注入
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService2 = userService;
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/list1")
    public List<User> users() {
        PageHelper.startPage(2, 3);
        return userService1.findAll();
    }

    @RequestMapping(value = "/list2")
    public List<User> list() {
        return userService2.findAll();
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

    @RequestMapping(value = "/add")
    public void addUser() {
        User user = new User();
        user.setEmail("110@qq.com");
        user.setSex(SexEnum.FEMALE);
        user.setUserName("hello");
        user.setLoginTime(LocalDateTime.now());
        user.setNote("mybatis 入门测试案例！");
        user.setMobile("13612345678");
        user.setPositionId(110112);

        List<String> hobbies = new ArrayList<>();
        hobbies.add("play computer games");
        hobbies.add("listen to music");
        user.setHobbies(hobbies);

        Boolean ret = userService1.add(user);
        System.out.println(ret);
    }

    @RequestMapping(value = "/{id}")
    public User user(@PathVariable Integer id) {
        User user = userService1.selectById(id);
        System.out.println(user.toString());
        return user;
    }

    @RequestMapping(value = "/getCountry")
    public Country country(@RequestParam(value = "id") Integer id) {
        return countryService.getById(id);
    }
}
