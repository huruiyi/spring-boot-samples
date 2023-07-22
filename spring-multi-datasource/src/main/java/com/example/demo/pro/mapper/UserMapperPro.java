package com.example.demo.pro.mapper;

import com.example.demo.pro.model.UserPro;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapperPro {

  List<UserPro> findAll();
}
