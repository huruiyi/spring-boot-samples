package com.example.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class WebConfig {

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setFallbackToSystemLocale(false);
    messageSource.setBasename("application_zh_CN");
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setCacheSeconds(2);
    return messageSource;
  }

  @Bean
  public HttpMessageConverters fastJsonHttpMessageConverters() {
    // 1、需要先定义一个 convert 转换消息的对象;
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

    //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

    //3、在convert中添加配置信息.
    fastConverter.setFastJsonConfig(fastJsonConfig);

    HttpMessageConverter<?> converter = fastConverter;
    return new HttpMessageConverters(converter);
  }

}
