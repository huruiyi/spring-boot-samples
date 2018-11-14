package com.enjoylearning.mybatis.basic.mapper;

import java.util.List;

import com.enjoylearning.mybatis.basic.entity.TJobHistory;
import com.enjoylearning.mybatis.basic.entity.TUser;
import org.apache.ibatis.annotations.Param;

public interface TJobHistoryMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(TJobHistory record);

	int insertSelective(TJobHistory record);

	TJobHistory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TJobHistory record);

	int updateByPrimaryKey(TJobHistory record);

	List<TJobHistory> selectByUserId(int userId);

	List<TUser> selectByEmailAndSex2(
			@Param("email") String email, 
			@Param("sex") Byte sex);
}