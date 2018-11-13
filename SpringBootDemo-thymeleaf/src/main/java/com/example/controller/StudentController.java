package com.example.controller;

import java.util.List;

import com.example.bean.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	public UserService userService;

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
