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

  static final String SEND_ON = "__send:on";
  static final String SEND_OFF = "__send:off";
  static final String RECV_ON = "__recv:on";
  static final String RECV_OFF = "__recv:off";

  private static final Logger log = LoggerFactory.getLogger(EchoHandler.class);

  private final Map<String, Client> clients = new ConcurrentHashMap<String, Client>();

  private static final class Client {
    final WebSocketSession session;
    volatile boolean sendBroadcast = true;
    volatile boolean receiveBroadcast = true;

    Client(final WebSocketSession session) {
      this.session = session;
    }
  }

  @Override
  public void afterConnectionEstablished(final WebSocketSession session) {
    WebSocketSession safe = new ConcurrentWebSocketSessionDecorator(session, 5000, 512 * 1024);
    clients.put(session.getId(), new Client(safe));
    log.info("Connected: {} ({}) online={}", usernameOf(session), session.getId(), clients.size());
  }

  @Override
  protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
    Client sender = clients.get(session.getId());
    if (sender == null) {
      return;
    }

    String payload = message.getPayload();
    String username = usernameOf(session);
    log.info("Received from {}: {}", username == null ? "(no username)" : username, payload);

    if ("ping".equals(payload)) {
      sendTo(sender.session, payload);
      return;
    }

    if (SEND_ON.equals(payload) || SEND_OFF.equals(payload)) {
      sender.sendBroadcast = SEND_ON.equals(payload);
      sendTo(sender.session, payload);
      log.info("{} sendBroadcast={}", username, sender.sendBroadcast);
      return;
    }

    if (RECV_ON.equals(payload) || RECV_OFF.equals(payload)) {
      sender.receiveBroadcast = RECV_ON.equals(payload);
      sendTo(sender.session, payload);
      log.info("{} receiveBroadcast={}", username, sender.receiveBroadcast);
      return;
    }

    if (username == null) {
      log.warn("Rejecting message from session {} — username is required", session.getId());
      sendTo(sender.session, "ERROR: username is required before sending messages");
      return;
    }

    String reply = username + ": " + payload;
    sendTo(sender.session, reply);
    if (!sender.sendBroadcast) {
      log.info("Echo only (send off): {}", reply);
      return;
    }

    log.info("Broadcasting Message: {}", reply);
    for (Client c : clients.values()) {
      if (c != sender && c.receiveBroadcast) {
        sendTo(c.session, reply);
      }
    }
  }

  @Override
  public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) {
    clients.remove(session.getId());
    log.info("Disconnected: {} ({}) {} online={}", usernameOf(session), session.getId(), status, clients.size());
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
