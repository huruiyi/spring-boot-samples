package com.example.service.Impl;

import com.example.dao.UserDao;
import com.example.Model.User;
import com.example.service.UserService;
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
