package com.example.websocketbasic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * TextWebSocketHandler: deal with String based messages
 *
 * TextMessage.getPayload(): content of the received websocket message
 */
public class EchoHandler extends TextWebSocketHandler {

  static final String MODE_BROADCAST = "__mode:broadcast";
  static final String MODE_ECHO = "__mode:echo";

  private static final Logger log = LoggerFactory.getLogger(EchoHandler.class);

  /** 全局开关:true = 发给所有连接,false = 只回显给发送者 */
  private volatile boolean broadcast = true;

  private final Map<String, WebSocketSession> clients = new ConcurrentHashMap<String, WebSocketSession>();

  @Override
  public void afterConnectionEstablished(final WebSocketSession session) {
    WebSocketSession safe = new ConcurrentWebSocketSessionDecorator(session, 5000, 512 * 1024);
    clients.put(session.getId(), safe);
    log.info("Connected: {} ({}) online={}", usernameOf(session), session.getId(), clients.size());
    sendTo(safe, currentModeFrame());
  }

  @Override
  protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
    WebSocketSession sender = clients.get(session.getId());
    if (sender == null) {
      return;
    }

    String payload = message.getPayload();
    String username = usernameOf(session);
    log.info("Received from {}: {}", username == null ? "(no username)" : username, payload);

    if ("ping".equals(payload)) {
      sendTo(sender, payload);
      return;
    }

    if (MODE_BROADCAST.equals(payload) || MODE_ECHO.equals(payload)) {
      broadcast = MODE_BROADCAST.equals(payload);
      log.info("Global mode -> {} (by {})", broadcast ? "broadcast" : "echo", username);
      fanout(currentModeFrame());
      return;
    }

    if (username == null) {
      log.warn("Rejecting message from session {} — username is required", session.getId());
      sendTo(sender, "ERROR: username is required before sending messages");
      return;
    }

    String reply = username + ": " + payload;
    if (broadcast) {
      log.info("Broadcasting Message: {}", reply);
      fanout(reply);
    } else {
      log.info("Echoing Message: {}", reply);
      sendTo(sender, reply);
    }
  }

  @Override
  public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) {
    clients.remove(session.getId());
    log.info("Disconnected: {} ({}) {} online={}", usernameOf(session), session.getId(), status, clients.size());
  }

  private String currentModeFrame() {
    return broadcast ? MODE_BROADCAST : MODE_ECHO;
  }

  private void fanout(final String text) {
    for (WebSocketSession session : clients.values()) {
      sendTo(session, text);
    }
  }

  private void sendTo(final WebSocketSession session, final String text) {
    try {
      session.sendMessage(new TextMessage(text));
    } catch (Exception e) {
      clients.remove(session.getId());
    }
  }

  private static String usernameOf(final WebSocketSession session) {
    Object username = session.getAttributes().get(UsernameHandshakeInterceptor.ATTR_USERNAME);
    if (username == null || username.toString().trim().isEmpty()) {
      return null;
    }
    return username.toString();
  }
}
