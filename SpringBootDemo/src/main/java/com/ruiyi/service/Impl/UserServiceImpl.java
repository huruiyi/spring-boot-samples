package com.ruiyi.service.Impl;

import com.ruiyi.dao.UserDao;
import com.ruiyi.model.User;
import com.ruiyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User getNameById(User user) {
        User model = userDao.getNameById(user);
        System.out.println(model);
        System.out.println(model.getId() + " " + model.getName());
        return model;
    }
}
