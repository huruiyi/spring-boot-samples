package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * 资源服务器配置 Created by macro on 2019/9/30.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
        .and()
        .authorizeRequests()
        .mvcMatchers("/user/login").permitAll().anyRequest().authenticated()
        .and()
        .requestMatchers().antMatchers("/user/**");
  }
}
