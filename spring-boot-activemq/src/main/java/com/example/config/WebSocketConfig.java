package com.example.config;

import com.example.service.MessageLogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MessageLogService messageLogService;

    public WebSocketConfig(MessageLogService messageLogService) {
        this.messageLogService = messageLogService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(messageWebSocketHandler(), "/ws/log")
                .setAllowedOrigins("*");
    }

    @Bean
    public MessageWebSocketHandler messageWebSocketHandler() {
        return new MessageWebSocketHandler(messageLogService);
    }
}
