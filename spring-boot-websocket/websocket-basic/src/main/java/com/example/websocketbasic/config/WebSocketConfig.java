package com.example.websocketbasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/*
 * @EnableWebSocket: enables processing of Websocket requests
 *
 * WebSocketConfigurer: defines callback methods to configure the
 * Websocket request handling
 */
@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
    registry.addHandler(echoHandler(), "/echoHandler");
  }

  @Bean
  public EchoHandler echoHandler() {
    return new EchoHandler();
  }
}
