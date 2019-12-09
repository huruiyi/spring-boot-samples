package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @RequestMapping(value = "/mvc", method = RequestMethod.GET)
    public String HelloWorld(Model model) {

        model.addAttribute("message", "Hello Spring MVC, SpringMvc2");
        return "HelloWorld";
    }

    @RequestMapping("/two")
    public String two(ModelMap model) {

        model.addAttribute("message", "Hello Spring MVC, jsp two two two two!!!");
        return "two";
    }
}
