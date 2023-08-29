package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * SpringSecurity配置 Created by macro on 2019/10/8.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
        .and()
        .authorizeRequests()
        .antMatchers("/image/**", "/css/**").permitAll()
        .antMatchers("/oauth/**", "/login/**", "/logout/**").permitAll()
        .antMatchers("/admin/**").hasAnyRole("admin")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .usernameParameter("un")
        .passwordParameter("pwd")
        .loginPage("/user/login").permitAll()
        .loginProcessingUrl("/login")
        .failureUrl("/user/login?error=true")
        .defaultSuccessUrl("/welcome").permitAll()

    ;
    http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);

    //LoginUrlAuthenticationEntryPoint

//    http.exceptionHandling()
//        .authenticationEntryPoint(new MacLoginUrlAuthenticationEntryPoint("/user/login"))
//        .accessDeniedHandler(new CustomAccessDeniedHandler());
  }

  @Autowired
  private CustomAccessDeniedHandler myAccessDeniedHandler;

}
