package com.example.demo.web;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("register")
    @ResponseBody
    public Response user(User user) {
        Response res = new Response();
        try {
            user.setDisabled(false);
            List<User> users = userRepository.findByName(user.getName());
            if (users.size() <= 0) {
                user.setPassword(DigestUtils.md5Hex(user.getPassword()));
                userRepository.save(user);
                res.setFlag(true);
                res.setMessage("用戶注冊成功");
                return res;
            } else {
                res.setFlag(true);
                res.setMessage("用戶已注册");
                return res;
            }
        } catch (Exception ex) {
            res.setFlag(false);
            res.setMessage("系统异常..");
            return res;
        }
    }

    @PostMapping("get")
    @ResponseBody
    public User change(String name) {
        List<User> users = userRepository.findByName(name);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @PostMapping("change")
    @ResponseBody
    public Response change(User user) {
        Response res = new Response();
        try {
            List<User> users = userRepository.findByName(user.getName());
            if (users.size() > 0) {
                User userInfo = users.get(0);
                userInfo.setSex(user.getSex());
                userInfo.setEmail(user.getEmail());
                userInfo.setTelPhone(user.getTelPhone());
                userInfo.setpSign(user.getpSign());
                userRepository.save(userInfo);
                res.setFlag(true);
                res.setMessage("修改成功");
                return res;
            } else {
                res.setMessage("修改失敗");
                return res;
            }
        } catch (Exception ex) {
            res.setFlag(false);
            res.setMessage("系统异常..");
            return res;
        }
    }
}
