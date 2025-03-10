package com.example.config;

import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  private static final String DEMO_RESOURCE_ID = "order";

  @Resource
  AuthenticationManager authenticationManager;

  @Resource
  RedisConnectionFactory redisConnectionFactory;

  @Resource
  UserDetailsService userDetailsService;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    //password 方案一：明文存储，用于测试，不能用于生产
    //String finalSecret = "123456";

    //password 方案二：用 BCrypt 对密码编码
    //String finalSecret = new BCryptPasswordEncoder().encode("123456");

    // password 方案三：支持多种编码，通过密码的前缀区分编码方式
    String finalSecret_user1 = "{bcrypt}" + new BCryptPasswordEncoder().encode("client_1_pass");
    String finalSecret_user2 = "{bcrypt}" + new BCryptPasswordEncoder().encode("client_2_pass");
    //配置两个客户端,一个用于password认证一个用于client认证
    clients.inMemory()
        .withClient("client_1")
        .resourceIds(DEMO_RESOURCE_ID)
        .authorizedGrantTypes("client_credentials", "refresh_token")
        .scopes("select").authorities("oauth2").secret(finalSecret_user1)
        .and()
        .withClient("client_2")
        .resourceIds(DEMO_RESOURCE_ID)
        .authorizedGrantTypes("password", "refresh_token")
        .scopes("select").authorities("oauth2").secret(finalSecret_user2);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService)
        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    endpoints.reuseRefreshTokens(true);
  }

  /**
   * Note：允许表单认证
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
    oauthServer.allowFormAuthenticationForClients();
  }

}
