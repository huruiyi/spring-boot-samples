package com.example.dynamic.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dynamic.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper — 固定走 slave 数据源（读库）
 */
@DS("slave")
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
