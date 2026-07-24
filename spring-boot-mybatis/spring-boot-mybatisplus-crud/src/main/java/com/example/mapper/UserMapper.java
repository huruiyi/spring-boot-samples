package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.User;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

  boolean saveBatchByNative(List<User> list);

  boolean saveBatchCustom(List<User> list);

  @Select("SELECT count(*) as sum FROM user")
  Integer UserSum();

  @Select("select * from user LIMIT #{startLen},5000")
  ArrayList<User> subList(@Param("startLen") int startLen);


  @Select("SELECT count(*) as sum FROM user where id like concat('%',0,'%')")
  Integer UserSumV2();

  @Select("select * from user  where id like concat('%',0,'%') LIMIT #{startLen},5000")
  ArrayList<User> subListV2(@Param("startLen") int startLen);
}
