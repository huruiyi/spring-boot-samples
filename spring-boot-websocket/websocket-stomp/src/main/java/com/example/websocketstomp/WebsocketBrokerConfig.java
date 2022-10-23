package com.example.websocketstomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
 * Configure STOMP Broker
 *
 * AbstractWebSocketMessageBrokerConfigurer: provides
 * optional methods for message handling
 *
 * @EnableWebSocketMessageBroker: enables broker-backed
 * messaging over Websocket using a subprotocol (STOMP)
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    // configure stomp endpoint
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    // configure message broker
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
