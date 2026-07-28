package com.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MessageLogService {

    private static final Logger log = LoggerFactory.getLogger(MessageLogService.class);
    private static final int MAX_LOG_SIZE = 200;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final List<Map<String, Object>> logEntries = new CopyOnWriteArrayList<>();
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void logSent(String destination, String type, String content) {
        Map<String, Object> entry = Map.of(
                "timestamp", LocalDateTime.now().format(FMT),
                "direction", "SENT",
                "destination", destination,
                "type", type,
                "content", content
        );
        addEntry(entry);
    }

    public void logReceived(String destination, String type, String content) {
        Map<String, Object> entry = Map.of(
                "timestamp", LocalDateTime.now().format(FMT),
                "direction", "RECEIVED",
                "destination", destination,
                "type", type,
                "content", content
        );
        addEntry(entry);
    }

    private void addEntry(Map<String, Object> entry) {
        logEntries.add(entry);
        while (logEntries.size() > MAX_LOG_SIZE) {
            logEntries.remove(0);
        }
        broadcast(entry);
    }

    public List<Map<String, Object>> getRecentLogs() {
        List<Map<String, Object>> reversed = new ArrayList<>(logEntries);
        Collections.reverse(reversed);
        return reversed;
    }

    public void registerSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    private void broadcast(Map<String, Object> entry) {
        try {
            String json = objectMapper.writeValueAsString(entry);
            TextMessage msg = new TextMessage(json);
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    try {
                        synchronized (session) {
                            session.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        log.warn("Failed to send to session {}: {}", session.getId(), e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Broadcast failed: {}", e.getMessage());
        }
    }
}
