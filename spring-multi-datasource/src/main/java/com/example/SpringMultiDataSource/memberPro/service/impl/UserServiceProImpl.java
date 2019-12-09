package com.example.SpringMultiDataSource.memberPro.service.impl;


import com.example.SpringMultiDataSource.memberPro.mapper.UserMapperPro;
import com.example.SpringMultiDataSource.memberPro.model.UserPro;
import com.example.SpringMultiDataSource.memberPro.service.UserServicePro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceProImpl implements UserServicePro {

    @Autowired
    private UserMapperPro userMapperPro;

    @Override
    public List<UserPro> findAll() {
        return userMapperPro.findAll();
    }

}
