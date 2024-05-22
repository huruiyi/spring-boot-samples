package vip.fairy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * WebSecurityConfigurerAdapter 2.7.0 起过时
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


  @Autowired
  private MyAuthSuccessHandler authSuccessHandler;

  @Autowired
  @Qualifier("sysUserService")
  private UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        // 允许对于网站静态资源的无授权访问
        .antMatchers(
            HttpMethod.GET,
            "/",
            "/*.html",
            "/**/*.js",
            "/**/*.html",
            "/**/*.css",
            "/swagger/**",
            "/v2/api-docs",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/druid/**",
            "/favicon.ico"
        ).permitAll()
        .and()
        .formLogin()
        .loginPage("/auth/loginPage").permitAll()
        .loginProcessingUrl("/auth/login")
        .successHandler(authSuccessHandler)
        .and()
        .authorizeRequests()
        .anyRequest().authenticated()
    ;

  }

}
