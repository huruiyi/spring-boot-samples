package com.example.web;

import com.example.model.User;
import com.example.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/u")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 批量插入（自定义）
     */
    @RequestMapping("/mysavebatch")
    public boolean mySaveBatch(){
        List<User> list = new ArrayList<>();
        // 待添加（用户）数据
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setName("test:"+i);
            user.setPassword("123456");
            list.add(user);
        }
        return userService.saveBatchCustom(list);
    }
}
