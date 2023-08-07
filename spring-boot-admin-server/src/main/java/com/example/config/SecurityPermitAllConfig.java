package com.example.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Profile("insecure")
@Configuration(proxyBeanMethods = false)
public class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {

  private final String adminContextPath;

  public SecurityPermitAllConfig(AdminServerProperties adminServerProperties) {
    this.adminContextPath = adminServerProperties.getContextPath();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())
        .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .ignoringRequestMatchers(
                new AntPathRequestMatcher(this.adminContextPath + "/instances", HttpMethod.POST.toString()),
                new AntPathRequestMatcher(this.adminContextPath + "/instances/*", HttpMethod.DELETE.toString()),
                new AntPathRequestMatcher(this.adminContextPath + "/actuator/**")));

  }

}