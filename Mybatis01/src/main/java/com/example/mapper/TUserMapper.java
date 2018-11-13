package com.example.mapper;


import com.example.entity.TUser;

import java.util.List;

public interface TUserMapper {

	TUser selectByPrimaryKey(Integer id);

	List<TUser> selectAllUsers();

}

