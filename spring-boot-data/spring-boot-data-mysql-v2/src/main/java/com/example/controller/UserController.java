package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import java.util.List;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseBody
  @RequestMapping({"/", ""})
  String welcome() {
    return "welcome my first spring boot project";
  }

  @ResponseBody
  @RequestMapping("/notVerify")
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

  @ResponseBody
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  String registerUser(@RequestBody User user) {
    return userService.registerUser(user);
  }

  @ResponseBody
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  boolean userLogin(@RequestBody User user, Model model) {
    model.addAttribute("name", user.getName());
    model.addAttribute("password", user.getPassword());
    return userService.verifyUser(user);
  }

  @ResponseBody
  @RequestMapping(value = "/get", method = RequestMethod.POST)
  List<User> get(@RequestBody User user, Model model) {
    return userService.findByEmail(user.getEmail());

  }

}
