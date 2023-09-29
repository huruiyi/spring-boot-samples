package com.example.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("hello")
@RestController
public class HelloWorld {
	@RequestMapping("/world")
	public String sayHi() {
		return "Hello World!";
	}
}
