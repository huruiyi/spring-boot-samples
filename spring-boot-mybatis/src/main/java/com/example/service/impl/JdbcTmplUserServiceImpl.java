package com.example.service.impl;

import com.example.model.User;
import com.example.service.JdbcTmplUserService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class JdbcTmplUserServiceImpl implements JdbcTmplUserService {

  private final JdbcTemplate jdbcTemplate;

  public JdbcTmplUserServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // 获取映射关系
  private RowMapper<User> getUserMapper() {
    // 使用Lambda表达式创建用户映射关系
    return (ResultSet rs, int rownum) -> {
      User user = new User();
      user.setId(rs.getLong("id"));
      user.setUserName(rs.getString("user_name"));
      user.setNote(rs.getString("note"));
      return user;
    };
  }

  @Override
  public User getUser(Long id) {
    String sql = " select id, user_name,  note from mybatis_user where id = ?";
    Object[] params = new Object[]{id};
    return jdbcTemplate.queryForObject(sql, params, getUserMapper());
  }

  @Override
  public List<User> findUsers(String userName, String note) {
    String sql = " select id, user_name, note from mybatis_user where user_name like concat('%', ?, '%') and note like concat('%', ?, '%')";
    Object[] params = new Object[]{userName, note};
    return jdbcTemplate.query(sql, params, getUserMapper());
  }

  @Override
  public int insertUser(User user) {
    String sql = " insert into mybatis_user (user_name,  note) values( ? ,  ?)";
    return jdbcTemplate.update(sql, user.getUserName(), user.getNote());
  }

  @Override
  public int updateUser(User user) {
    String sql = " update mybatis_user set user_name = ?, note = ?  " + " where id = ?";
    return jdbcTemplate.update(sql, user.getUserName(), user.getNote(), user.getId());
  }

  @Override
  public int deleteUser(Long id) {
    String sql = " delete from mybatis_user where id = ?";
    return jdbcTemplate.update(sql, id);
  }

  public User getUser2(Long id) {
    return this.jdbcTemplate.execute((Statement stmt) -> {
      String sql1 = "select count(*) total from mybatis_user where id= " + id;
      ResultSet rs1 = stmt.executeQuery(sql1);
      while (rs1.next()) {
        int total = rs1.getInt("total");
        System.out.println(total);
      }
      String sql2 = " select id, user_name,  note from mybatis_user where id = " + id;
      ResultSet rs2 = stmt.executeQuery(sql2);
      User user = null;
      while (rs2.next()) {
        int rowNum = rs2.getRow();
        user = getUserMapper().mapRow(rs2, rowNum);
      }
      return user;
    });
  }

  public User getUser3(Long id) {
    return this.jdbcTemplate.execute((Connection conn) -> {
      String sql1 = " select count(*) as total from mybatis_user where id = ?";
      PreparedStatement ps1 = conn.prepareStatement(sql1);
      ps1.setLong(1, id);
      ResultSet rs1 = ps1.executeQuery();
      while (rs1.next()) {
        System.out.println(rs1.getInt("total"));
      }
      String sql2 = " select id, user_name,  note from mybatis_user where id = ?";
      PreparedStatement ps2 = conn.prepareStatement(sql2);
      ps2.setLong(1, id);
      ResultSet rs2 = ps2.executeQuery();
      User user = null;
      while (rs2.next()) {
        int rowNum = rs2.getRow();
        user = getUserMapper().mapRow(rs2, rowNum);
      }
      return user;
    });
  }
}
