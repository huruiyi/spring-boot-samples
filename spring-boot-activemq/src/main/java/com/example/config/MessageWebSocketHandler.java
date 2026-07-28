package com.example.config;

import com.example.service.MessageLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class MessageWebSocketHandler implements WebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(MessageWebSocketHandler.class);

    private final MessageLogService messageLogService;

    public MessageWebSocketHandler(MessageLogService messageLogService) {
        this.messageLogService = messageLogService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("WebSocket connected: {}", session.getId());
        messageLogService.registerSession(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.warn("WebSocket error: {}", exception.getMessage());
        messageLogService.removeSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        log.info("WebSocket disconnected: {}", session.getId());
        messageLogService.removeSession(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
