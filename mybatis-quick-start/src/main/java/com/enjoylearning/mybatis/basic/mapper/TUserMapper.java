package com.enjoylearning.mybatis.basic.mapper;

import com.enjoylearning.mybatis.basic.entity.TUser;

public interface TUserMapper {

	TUser selectByPrimaryKey(Integer id);

}