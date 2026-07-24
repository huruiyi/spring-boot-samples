package com.example;

import com.example.mapper.UserMapper;
import com.example.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
public class UserExecutorUtils {


  @Autowired
  private UserMapper userMapper;


  // 一个线程最大处理数据量
  private static final int THREAD_COUNT_SIZE = 5000;

  @Test
  void test() {

    //计算表总数
    Integer integer = userMapper.UserSum();

    //记录开始时间
    long start = System.currentTimeMillis();

    //new个和表总数一样长的ArrayList
    List<User> threadList = new ArrayList<>(integer);

    // 线程数，以5000条数据为一个线程，总数据大小除以5000，再加1
    int round = integer / THREAD_COUNT_SIZE + 1;

    //new一个临时储存List的Map，以线程名为k，用做list排序
    Map<Integer, List<User>> temporaryMap = new HashMap<>(round);

    // 程序计数器
    final CountDownLatch count = new CountDownLatch(round);

    // 创建线程
    ExecutorService executor = Executors.newFixedThreadPool(round);

    // 分配数据
    for (int i = 0; i < round; i++) {
      //该线程的查询开始值
      int startLen = i * THREAD_COUNT_SIZE;
      int k = i + 1;
      executor.execute(() -> {
        ArrayList<User> users = userMapper.subList(startLen);
        //把查出来的List放进临时Map
        temporaryMap.put(k, users);
        System.out.println("正在处理线程【" + k + "】的数据，数据大小为：" + users.size());
        // 计数器 -1(唤醒阻塞线程)
        count.countDown();
      });
    }
    try {
      // 阻塞线程(主线程等待所有子线程 一起执行业务)
      count.await();
      //结束时间
      long end = System.currentTimeMillis();
      System.out.println("100万数据查询耗时:" + (end - start) + "ms");
      //通过循环遍历临时map，把map的值有序的放进List里
      temporaryMap.keySet().forEach(k -> {
        threadList.addAll(temporaryMap.get(k));
      });
    } catch (Exception e) {
      log.error(e.getMessage());
    } finally {
      //清除临时map，释放内存
      temporaryMap.clear();
      // 终止线程池
      // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。若已经关闭，则调用没有其他作用。
      executor.shutdown();
    }
    //输出list的长度
    System.out.println("list长度为：" + threadList.size());
  }
}
