package com.example.dev.mapper;

import com.example.dev.model.UserDev;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapperDev {

  List<UserDev> findAll();
}
