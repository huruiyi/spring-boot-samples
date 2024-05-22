package com.example.web;

import com.example.enums.SexEnum;
import com.example.model.User;
import com.example.service.JdbcTmplUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class JdbcTmplController {

    private final JdbcTmplUserService jdbcTmplUserService;

    /**
     * 构造函数注入
     */
    public JdbcTmplController(JdbcTmplUserService jdbcTmplUserService) {
        this.jdbcTmplUserService = jdbcTmplUserService;
    }

    @RequestMapping("/v2/{id}")
    @ResponseBody
    public User getUser2(@PathVariable Long id) {
        return jdbcTmplUserService.getUser2(id);
    }


    @RequestMapping("/v3/get")
    @ResponseBody
    public User getUser3(Long id) {
        return jdbcTmplUserService.getUser3(id);
    }

    @RequestMapping("/findUsers")
    @ResponseBody
    public List<User> findUsers(String userName, String note) {
        return jdbcTmplUserService.findUsers(userName, note);
    }


    @RequestMapping("/user/insert")
    @ResponseBody
    public Map<String, Object> insertUser(String userName, Integer sex, String note) {
        User user = new User();
        user.setNote(note);
        user.setSex(SexEnum.getEnumById(sex));
        user.setUserName(userName);
        int result = jdbcTmplUserService.insertUser(user);
        boolean success = result > 0;
        String msg = success ? "新增用户成功" : "新增用户失败";
        return this.resultMap(success, msg);
    }

    @RequestMapping("/user/delete")
    @ResponseBody
    public Map<String, Object> deleteUser(Long id) {
        int result = jdbcTmplUserService.deleteUser(id);
        boolean success = result > 0;
        String msg = success ? "删除用户成功" : "删除用户失败";
        return this.resultMap(success, msg);
    }


    @RequestMapping("/user/update")
    @ResponseBody
    public Map<String, Object> updateUser(Long id, String userName, Integer sex, String note) {
        User user = new User();
        user.setNote(note);
        user.setUserName(userName);
        user.setId(id);
        int result = jdbcTmplUserService.updateUser(user);
        boolean success = result > 0;
        String msg = success ? "更新用户成功" : "更新用户失败";
        return this.resultMap(success, msg);
    }


    private Map<String, Object> resultMap(Boolean success, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("message", msg);
        return map;
    }

}
