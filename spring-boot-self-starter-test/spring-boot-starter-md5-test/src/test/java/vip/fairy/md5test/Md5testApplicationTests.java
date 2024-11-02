package vip.fairy.md5test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.fairy.service.MD5Service;

@SpringBootTest
class Md5testApplicationTests {

  @Autowired
  MD5Service md5Service ;

  @Test
  void md5Test() {
    System.out.println(md5Service.getMD5("Hello World,世界你好！"));
  }

}
