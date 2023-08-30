package com.example.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean saveBatchByNative(List<User> list) {
        return userMapper.saveBatchByNative(list);
    }
      public boolean saveBatchCustom(List<User> list){
        return userMapper.saveBatchCustom(list);
    }


}
