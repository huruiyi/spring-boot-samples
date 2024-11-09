package vip.fairy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.fairy.service.MD5Service;

@Configuration
//@EnableAutoConfiguration
public class MD5AutoConfiguration {

  @Bean
  MD5Service md5Service() {
    return new MD5Service();
  }

}
