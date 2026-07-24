package com.example.routing.mapper;

import com.example.routing.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    void insert(User user);
}
