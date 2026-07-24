### 不推荐的配置，建议用SecurityFilterChain
Use a org.springframework.security.web.SecurityFilterChain Bean to configure `HttpSecurity` or a `WebSecurityCustomizer` Bean to configure WebSecurity.
```java
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfigV1 extends WebSecurityConfigurerAdapter {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
  String LOGIN_PATH = "/login";
    http.formLogin()
        .usernameParameter("un")
        .passwordParameter("pwd")
        .loginPage(LOGIN_PATH)
        .loginProcessingUrl(LOGIN_PATH)
        .defaultSuccessUrl("/success")
        .failureForwardUrl("/fail");

    http.authorizeRequests()
        .antMatchers(LOGIN_PATH).permitAll()
        .anyRequest().authenticated();

    http.csrf().disable();
    http.httpBasic();
  }

}

```
