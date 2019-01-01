package com.ruiyi.demo.mapper;

import com.ruiyi.demo.model.User;

import java.util.List;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    List<User> selectAllUsers();
}
