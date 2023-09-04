package com.example.demo.config;

import com.example.demo.service.SystemUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  final SystemUserService systemUserService;

  public SecurityConfig(SystemUserService systemUserService) {
    this.systemUserService = systemUserService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer ignoringCustomizer() {
    return (web) -> web.ignoring().antMatchers("/css/**", "/image/**");
  }

  @Bean
  protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    String LOGIN_PATH = "/user/login";
    http.formLogin()
        .usernameParameter("un")
        .passwordParameter("pwd")
        .loginPage(LOGIN_PATH)
        .loginProcessingUrl(LOGIN_PATH)
        .defaultSuccessUrl("/index");

    http.logout(logoutConfigurer -> {
      logoutConfigurer.deleteCookies("remove");
      logoutConfigurer.invalidateHttpSession(false);
      logoutConfigurer.logoutUrl("/user/logout");
      logoutConfigurer.logoutSuccessUrl(LOGIN_PATH);
    });

    http.authorizeRequests()
        .antMatchers("/index").authenticated()
        .antMatchers(LOGIN_PATH).permitAll()
        .anyRequest().authenticated();

    http.csrf(httpSecurityCsrfConfigurer -> {
      httpSecurityCsrfConfigurer.csrfTokenRepository(new CookieCsrfTokenRepository());
    });

    http.httpBasic(Customizer.withDefaults());
    return http.build();
  }

}
