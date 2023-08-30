package com.example;

import com.example.model.User;
import com.example.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserControllerTest {

  // 最大循环次数
  private static final int MAXCOUNT = 100000;

  @Autowired
  private UserServiceImpl userService;


  /**
   * 执行时间：71685
   * 循环单次插入
   */
  @Test
  void save() {
    Assertions.assertDoesNotThrow(() -> {
      long time = System.currentTimeMillis(); // 统计开始时间
      for (int i = 0; i < MAXCOUNT; i++) {
        User user = new User();
        user.setName("test:" + i);
        user.setPassword("123456");
        userService.save(user);
      }
      long etime = System.currentTimeMillis(); // 统计结束时间
      System.out.println("执行时间：" + (etime - time));
    });
  }


  /**
   * 执行时间：9432
   * MP 批量插入
   */
  @Test
  void saveBatch() {
    Assertions.assertDoesNotThrow(() -> {
      long time = System.currentTimeMillis(); // 统计开始时间
      List<User> list = new ArrayList<>();
      for (int i = 0; i < MAXCOUNT; i++) {
        User user = new User();
        user.setName("test:" + i);
        user.setPassword("123456");
        list.add(user);
      }
      // MP 批量插入
      userService.saveBatch(list);
      long etime = System.currentTimeMillis(); // 统计结束时间
      System.out.println("执行时间：" + (etime - time));
    });
  }

  /**
   * 执行时间：928
   * 原生自己拼接 SQL，批量插入
   */
  @Test
  void saveBatchByNative() {
    Assertions.assertDoesNotThrow(() -> {
      long time = System.currentTimeMillis(); // 统计开始时间
      List<User> list = new ArrayList<>();
      for (int i = 0; i < MAXCOUNT; i++) {
        User user = new User();
        user.setName("test:" + i);
        user.setPassword("123456");
        list.add(user);
      }
      // 批量插入
      userService.saveBatchByNative(list);
      long etime = System.currentTimeMillis(); // 统计结束时间
      System.out.println("执行时间：" + (etime - time));
    });

  }


}
