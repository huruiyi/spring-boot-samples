package com.ruiyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.DispatcherServlet;

//url:  http://localhost:8080/HelloSpringMVC/hello/mvc

//告诉DispatcherServlet相关的容器， 这是一个Controller，
@Controller

//类级别的RequestMapping，告诉DispatcherServlet由这个类负责处理URL。
//HandlerMapping依靠这个标签来工作
@RequestMapping(value = "/hello")
public class HelloController {

    //方法级别的RequestMapping， 限制并缩小了URL路径匹配，同类级别的标签协同工作，最终确定拦截到的URL由那个方法处理
    //并指定访问方法为GET
    @RequestMapping(value = "/mvc", method = RequestMethod.GET)
    public String HelloWorld(Model model) {

        model.addAttribute("message", "Hello Spring MVC !!!~~~~~~~SpringMvc1");  //传参数给前端
        //视图渲染，/WEB-INF/view/HelloWorld.jsp
        return "HelloWorld";  //页面的名称，根据此字符串会去寻找名为HelloWorld.jsp的页面
    }
}
