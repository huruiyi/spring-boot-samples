package com.example.SpringMultiDataSource.memberDev.service.impl;


import com.example.SpringMultiDataSource.memberDev.mapper.UserMapperDev;
import com.example.SpringMultiDataSource.memberDev.model.UserDev;
import com.example.SpringMultiDataSource.memberDev.service.UserServiceDev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceDevImpl implements UserServiceDev {

    @Autowired
    private UserMapperDev userMapperDev;

    @Override
    public List<UserDev> findAll() {
        return userMapperDev.findAll();
    }

}
