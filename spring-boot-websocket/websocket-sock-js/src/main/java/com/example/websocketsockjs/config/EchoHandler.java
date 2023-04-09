package com.example.websocketsockjs.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/*
 * TextWebSocketHandler: deal with String based messages
 *
 * TextMessage.getPayload(): content of the received websocket message
 */
public class EchoHandler extends TextWebSocketHandler {
    private static final Logger log = LoggerFactory.getLogger(EchoHandler.class);

    @Override
    protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
        log.info("Received Message: {}", message.getPayload());
        log.info("Sending Message: {}", message.getPayload());

        // simply echos received message
        session.sendMessage(new TextMessage(message.getPayload()));
    }
}
