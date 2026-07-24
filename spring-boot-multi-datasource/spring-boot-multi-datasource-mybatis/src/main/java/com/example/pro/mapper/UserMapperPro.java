package com.example.pro.mapper;

import com.example.pro.model.UserPro;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapperPro {

  List<UserPro> findAll();
}
