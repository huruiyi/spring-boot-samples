package com.example.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

  private final UserMapper userMapper;

  private final JdbcTemplate jdbcTemplate;

  public UserServiceImpl(UserMapper userMapper, JdbcTemplate jdbcTemplate) {
    this.userMapper = userMapper;
    this.jdbcTemplate = jdbcTemplate;
  }

  public void saveBatchByNative(List<User> list) {
    userMapper.saveBatchByNative(list);
  }

  public boolean saveBatchCustom(List<User> list) {
    return userMapper.saveBatchCustom(list);
  }

  @Transactional
  public void jdbcBatchInsert(List<User> list, final int batchSize) {
    String sql = "insert into user (name,password,createtime) values(?,?,?)";
    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps, int i) throws SQLException {
        User userPO = list.get(i);
        ps.setString(1, userPO.getName());
        ps.setString(2, userPO.getPassword());
        ps.setDate(3, new Date(new java.util.Date().getTime()));
      }

      @Override
      public int getBatchSize() {
        return list.size();
      }
    });
  }


}
