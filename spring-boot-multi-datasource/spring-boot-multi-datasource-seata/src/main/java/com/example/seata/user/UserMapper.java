package com.example.seata.user;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@DS("user")  // 绑定用户库
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
