package com.example.demo.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration(proxyBeanMethods = false)
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

  private final String adminContextPath;

  public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
    this.adminContextPath = adminServerProperties.getContextPath();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    successHandler.setTargetUrlParameter("redirectTo");
    successHandler.setDefaultTargetUrl(this.adminContextPath + "/");

    http.authorizeRequests((authorizeRequests) -> authorizeRequests
            .antMatchers(this.adminContextPath + "/assets/**").permitAll()
            .antMatchers(this.adminContextPath + "/login").permitAll().anyRequest().authenticated())
        .formLogin((formLogin) -> formLogin.loginPage(this.adminContextPath + "/login").successHandler(successHandler))
        .logout((logout) -> logout.logoutUrl(this.adminContextPath + "/logout"))
        .httpBasic(Customizer.withDefaults())
        .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .ignoringRequestMatchers(
                new AntPathRequestMatcher(this.adminContextPath + "/instances", HttpMethod.POST.toString()),
                new AntPathRequestMatcher(this.adminContextPath + "/instances/*", HttpMethod.DELETE.toString()),
                new AntPathRequestMatcher(this.adminContextPath + "/actuator/**")));
  }

}