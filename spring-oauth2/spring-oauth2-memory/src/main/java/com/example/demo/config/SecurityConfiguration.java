package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
//    }

  public static void main(String[] args) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    System.out.println(bCryptPasswordEncoder.encode("user_1_pass"));
    System.out.println(bCryptPasswordEncoder.encode("user_2_pass"));
    System.out.println(bCryptPasswordEncoder.encode("client_1_pass"));
    System.out.println(bCryptPasswordEncoder.encode("client_2_pass"));
  }

  @Bean
  @Override
  protected UserDetailsService userDetailsService() {
    //password 方案一：明文存储，用于测试，不能用于生产
    //String finalPassword = "12345678";

    //password 方案二：用 BCrypt 对密码编码
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    //String finalPassword = bCryptPasswordEncoder.encode("12345678");

    // password 方案三：支持多种编码，通过密码的前缀区分编码方式
    String finalPassword_user1 = "{bcrypt}" + bCryptPasswordEncoder.encode("user_1_pass");
    String finalPassword_user2 = "{bcrypt}" + bCryptPasswordEncoder.encode("user_2_pass");

    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("user_1").password(finalPassword_user1).authorities("USER").build());
    manager.createUser(User.withUsername("user_2").password(finalPassword_user2).authorities("USER").build());
    return manager;
  }

  /**
   * springboot2.0 删除了原来的 plainTextPasswordEncoder
   * https://docs.spring.io/spring-security/site/docs/5.0.4.RELEASE/reference/htmlsingle/#10.3.2 DelegatingPasswordEncoder
   */


  /**
   * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
   *
   * @return
   * @throws Exception
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    AuthenticationManager manager = super.authenticationManagerBean();
    return manager;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .requestMatchers().anyRequest()
        .and()
        .authorizeRequests()
        .antMatchers("/oauth/**").permitAll();
  }
}
