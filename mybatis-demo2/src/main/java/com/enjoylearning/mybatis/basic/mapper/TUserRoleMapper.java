package com.enjoylearning.mybatis.basic.mapper;

import com.enjoylearning.mybatis.basic.entity.TUserRoleKey;

public interface TUserRoleMapper {

	int deleteByPrimaryKey(TUserRoleKey key);

	int insert(TUserRoleKey record);

	int insertSelective(TUserRoleKey record);
}