package com.example;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@Slf4j
@SpringBootTest
class BatchInsertDataTest {

  private static final int MAX_COUNT = 10000;

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private UserService userService;

  @BeforeEach
  public void deleteTest() {
    Wrapper<User> query = new QueryWrapper<User>();
    userMapper.delete(query);
    log.info("清空数据表");
  }

  /**
   * 543
   */
  @Test
  void mybatisForeach() {
    StopWatch sw = new StopWatch("testBatchInsert2");
    sw.start();
    List<User> list = getUsers();
    userService.saveBatchCustom(list);
    sw.stop();
    log.info("saveBatchCustom:{}", sw.getTotalTimeMillis());
  }

  /**
   * 1960
   */
  @Test
  void mybatisPlusBatchSave() {
    StopWatch sw = new StopWatch("mybatisPlusBatchSave");
    sw.start();
    List<User> list = getUsers();
    userService.saveBatch(list, 500);
    sw.stop();
    log.info("mybatisPlusBatchSave:{}", sw.getTotalTimeMillis());
  }

  /**
   * 1555
   */
  @Test
  public void jdbcBatchSave() {
    StopWatch sw = new StopWatch("jdbcBatchSave");
    sw.start();
    List<User> list = getUsers();
    userService.jdbcBatchInsert(list, 500);
    sw.stop();
    log.info("jdbcBatchSave:{}", sw.getTotalTimeMillis());
  }

  private static List<User> getUsers() {
    List<User> list = new ArrayList<>();
    User user;
    for (int i = 0; i < MAX_COUNT; i++) {
      user = new User();
      user.setName("用户" + i);
      user.setPassword("p" + i);
      user.setCreateTime(LocalDateTime.now());
      list.add(user);
    }
    return list;
  }

}
