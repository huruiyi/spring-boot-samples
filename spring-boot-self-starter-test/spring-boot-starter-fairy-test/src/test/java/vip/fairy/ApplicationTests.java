package vip.fairy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.fairy.service.HelloService;
import vip.fairy.service.MD5Service;

@SpringBootTest
class ApplicationTests {

  @Autowired
  MD5Service md5Service ;

  @Autowired
  HelloService helloService;

  @Test
  void test() {
    System.out.println(md5Service.getMD5("Hello World,世界你好！"));
    System.out.println(helloService.sayHello());
  }

}
