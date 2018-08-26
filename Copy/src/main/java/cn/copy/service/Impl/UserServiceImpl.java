package cn.copy.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.copy.dao.UserDao;
import cn.copy.model.User;
import cn.copy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getNameById(User user) {
        return userDao.getNameById(user);
    }
}
