package com.example.websocketsockjs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/*
 * withSockJS(): Enable SockJS fallback options
 */
@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
    registry.addHandler(echoHandler(), "/echoHandler").withSockJS();
  }

  @Bean
  public EchoHandler echoHandler() {
    return new EchoHandler();
  }
}
