package com.example.dao;

import com.example.Model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public User getNameById(User user);
}
