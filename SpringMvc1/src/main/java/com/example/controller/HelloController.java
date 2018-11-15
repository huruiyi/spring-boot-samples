package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//类级别的RequestMapping，告诉DispatcherServlet由这个类负责处理URL。
//HandlerMapping依靠这个标签来工作
@RequestMapping(value = "/hello")
public class HelloController {

    @RequestMapping(value = "/mvc", method = RequestMethod.GET)
    public String HelloWorld(Model model) {
        model.addAttribute("message", "Hello Spring MVC !!!~~~~~~~SpringMvc1");
        return "HelloWorld";
    }
}
