package com.example.config;

import com.example.service.AccountUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.annotation.Resource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  @Resource
  AppConfig appConfig;

  @Resource
  AuthenticationManager authenticationManager;

  @Resource
  AccountUserDetailsService userDetailsService;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(appConfig.dataSource());
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints.tokenStore(appConfig.tokenStore()).authenticationManager(authenticationManager).userDetailsService(userDetailsService)
        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    endpoints.reuseRefreshTokens(true);

  }

  /**
   * Note：允许表单认证
   *
   * @param oauthServer
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
    oauthServer.allowFormAuthenticationForClients();
  }

}