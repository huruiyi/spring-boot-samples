package com.example.demo.pro.service.impl;


import com.example.demo.pro.mapper.UserMapperPro;
import com.example.demo.pro.model.UserPro;
import com.example.demo.pro.service.UserServicePro;
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
