package com.example.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sharding.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 方式5：ShardingSphere 读写分离
 * Mapper 无需任何注解，ShardingSphere 自动根据 SQL 类型路由：
 * - SELECT → 自动路由到从库（slave0/slave1 轮询）
 * - INSERT/UPDATE/DELETE → 自动路由到主库（master）
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
