package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    List<User> selectAllUsers();

    int insertUser(User user);

 }
