package cn.copy.dao;


import org.apache.ibatis.annotations.Mapper;

import cn.copy.model.User;


@Mapper
public interface UserDao {
    public User getNameById(User user);
}
