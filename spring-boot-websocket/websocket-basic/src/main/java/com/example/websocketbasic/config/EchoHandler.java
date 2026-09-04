package com.example.websocketbasic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
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
  public void afterConnectionEstablished(final WebSocketSession session) {
    log.info("Connected: {} ({})", usernameOf(session), session.getId());
  }

  @Override
  protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
    String payload = message.getPayload();
    String username = usernameOf(session);
    log.info("Received from {}: {}", username == null ? "(no username)" : username, payload);

    // keep ping frames unprefixed so the client can route them to the ping panel
    if ("ping".equals(payload)) {
      session.sendMessage(new TextMessage(payload));
      return;
    }

    if (username == null) {
      log.warn("Rejecting message from session {} — username is required", session.getId());
      session.sendMessage(new TextMessage("ERROR: username is required before sending messages"));
      return;
    }

    String reply = username + ": " + payload;
    log.info("Sending Message: {}", reply);
    session.sendMessage(new TextMessage(reply));
  }

  @Override
  public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) {
    log.info("Disconnected: {} ({}) {}", usernameOf(session), session.getId(), status);
  }

  private static String usernameOf(final WebSocketSession session) {
    Object username = session.getAttributes().get(UsernameHandshakeInterceptor.ATTR_USERNAME);
    if (username == null || username.toString().trim().isEmpty()) {
      return null;
    }
    return username.toString();
  }
}
