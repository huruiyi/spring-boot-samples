package com.example.jta.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO t_user (name, email) VALUES (#{name}, #{email})")
    void insert(User user);
}
