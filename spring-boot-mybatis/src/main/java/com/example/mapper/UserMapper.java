package com.example.mapper;

import com.example.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


  User selectByPrimaryKey(Integer id);

  List<User> selectAllUsers();

  int insertUser(User user);

}
