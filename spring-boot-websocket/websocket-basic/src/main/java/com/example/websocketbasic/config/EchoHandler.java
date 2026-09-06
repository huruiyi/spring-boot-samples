package com.example.websocketbasic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
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
  static final String RECEIVE_ON = "__receive:on";
  static final String RECEIVE_OFF = "__receive:off";
  static final String ONLINE_PREFIX = "__online:";
  static final String PM_PREFIX = "__pm:";

  private static final Logger log = LoggerFactory.getLogger(EchoHandler.class);

  private final Map<String, Client> clients = new ConcurrentHashMap<>();

  private static final class Client {
    final WebSocketSession session;
    volatile boolean sendBroadcast = true;
    volatile boolean receiveBroadcast = true;

    Client(final WebSocketSession session) {
      this.session = session;
    }
  }

  @Override
  public void afterConnectionEstablished(@NonNull final WebSocketSession session) {
    WebSocketSession safe = new ConcurrentWebSocketSessionDecorator(session, 5000, 512 * 1024);
    clients.put(session.getId(), new Client(safe));
    log.info("Connected: {} ({}) online={}", usernameOf(session), session.getId(), clients.size());
    broadcastOnline();
  }

  @Override
  protected void handleTextMessage(final WebSocketSession session, @NonNull final TextMessage message) {
    Client sender = clients.get(session.getId());
    if (sender == null) {
      return;
    }

    String payload = message.getPayload();
    String username = usernameOf(session);
    log.info("Received from {}: {}", username == null ? "(no username)" : username, payload);

    switch (payload) {
      case "ping":
        sendTo(sender.session, payload);
        return;
      case SEND_ON:
      case SEND_OFF:
        sender.sendBroadcast = SEND_ON.equals(payload);
        sendTo(sender.session, payload);
        log.info("{} sendBroadcast={}", username, sender.sendBroadcast);
        return;
      case RECEIVE_ON:
      case RECEIVE_OFF:
        sender.receiveBroadcast = RECEIVE_ON.equals(payload);
        sendTo(sender.session, payload);
        log.info("{} receiveBroadcast={}", username, sender.receiveBroadcast);
        return;
    }

    if (username == null) {
      log.warn("Rejecting message from session {} — username is required", session.getId());
      sendTo(sender.session, "ERROR: username is required before sending messages");
      return;
    }

    if (payload.startsWith(PM_PREFIX)) {
      int bar = payload.indexOf('|', PM_PREFIX.length());
      if (bar < 0) {
        sendTo(sender.session, "ERROR: invalid private message");
        return;
      }
      String to = payload.substring(PM_PREFIX.length(), bar).trim();
      String text = payload.substring(bar + 1);
      whisper(sender, username, to, text);
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
  public void afterConnectionClosed(final WebSocketSession session, @NonNull final CloseStatus status) {
    clients.remove(session.getId());
    log.info("Disconnected: {} ({}) {} online={}", usernameOf(session), session.getId(), status, clients.size());
    broadcastOnline();
  }

  private void whisper(final Client sender, final String from, final String to, final String text) {
    if (to.isEmpty()) {
      sendTo(sender.session, "ERROR: pick someone to message");
      return;
    }
    String reply = "[PM " + from + " → " + to + "] " + from + ": " + text;
    sendTo(sender.session, reply);
    if (to.equals(from)) {
      return;
    }
    boolean found = false;
    for (Client c : clients.values()) {
      if (c == sender) {
        continue;
      }
      if (to.equals(usernameOf(c.session))) {
        sendTo(c.session, reply);
        found = true;
      }
    }
    if (!found) {
      sendTo(sender.session, "ERROR: \"" + to + "\" is not online");
    }
  }

  private void broadcastOnline() {
    StringBuilder names = new StringBuilder();
    for (Client c : clients.values()) {
      String name = usernameOf(c.session);
      if (name == null) {
        continue;
      }
      if (names.length() > 0) {
        names.append(',');
      }
      names.append(name);
    }
    String frame = ONLINE_PREFIX + clients.size() + '|' + names;
    for (Client c : clients.values()) {
      sendTo(c.session, frame);
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
