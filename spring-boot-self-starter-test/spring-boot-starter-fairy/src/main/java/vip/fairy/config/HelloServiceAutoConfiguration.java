package vip.fairy.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.fairy.properties.HelloProperties;
import vip.fairy.service.HelloService;

@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

  private HelloProperties helloProperties;

  public HelloServiceAutoConfiguration(HelloProperties helloProperties) {
    this.helloProperties = helloProperties;
  }

  //实例化HelloService并载入Spring IoC容器
  @Bean
  @ConditionalOnMissingBean
  public HelloService helloService() {
    return new HelloService(helloProperties.getName(), helloProperties.getAddress());
  }

}
