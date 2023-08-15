package com.example;

import com.example.service.unclassified.AsyncServiceV2;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AsyncServiceTest {

  @Autowired
  private AsyncServiceV2 asyncServiceV2;

  @Test
  void testAsync1() throws Exception {
    asyncServiceV2.async1();
    log.info("测试 testAsync1");
  }

  @Test
  void testAsync2() throws Exception {
    Future<Integer> res = asyncServiceV2.async2();
    log.info("测试 testAsync2");
    log.info("返回结果：{}", res.get());
  }


}
