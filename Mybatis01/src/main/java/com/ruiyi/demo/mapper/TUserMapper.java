package com.ruiyi.demo.mapper;


import com.ruiyi.demo.entity.TUser;

import java.util.List;

public interface TUserMapper {

	TUser selectByPrimaryKey(Integer id);

	List<TUser> selectAllUsers();

}

