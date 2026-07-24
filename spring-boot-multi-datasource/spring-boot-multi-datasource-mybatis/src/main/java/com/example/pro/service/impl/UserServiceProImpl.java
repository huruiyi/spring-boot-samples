package com.example.pro.service.impl;


import com.example.pro.mapper.UserMapperPro;
import com.example.pro.model.UserPro;
import com.example.pro.service.UserServicePro;
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
