package cn.copy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.copy.model.User;
import cn.copy.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }


    /*
    http://localhost:8080/get?id=1
     */
    @RequestMapping("/get")
    @ResponseBody
    public String get(User user) {
        User u = userService.getNameById(user);
        return JSONObject.toJSONString(u);
    }
}
