package com.example.dev.service.impl;


import com.example.dev.mapper.UserMapperDev;
import com.example.dev.model.UserDev;
import com.example.dev.service.UserServiceDev;
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
