package com.example.websocketbasic.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Resolves username from the handshake query string, falling back to the HTTP session
 * so a page refresh can reconnect without typing the name again. Handshake is rejected
 * when neither source has a username.
 */
public class UsernameHandshakeInterceptor implements HandshakeInterceptor {

  public static final String ATTR_USERNAME = "username";

  @Override
  public boolean beforeHandshake(
      @NonNull final ServerHttpRequest request,
      @NonNull final ServerHttpResponse response,
      @NonNull final WebSocketHandler wsHandler,
      @NonNull final Map<String, Object> attributes) {
    if (!(request instanceof ServletServerHttpRequest)) {
      return false;
    }

    HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
    HttpSession httpSession = servletRequest.getSession(true);

    String username = trimToNull(servletRequest.getParameter("username"));
    if (username != null) {
      httpSession.setAttribute(ATTR_USERNAME, username);
    } else {
      Object saved = httpSession.getAttribute(ATTR_USERNAME);
      username = saved == null ? null : trimToNull(saved.toString());
    }

    if (username == null) {
      return false;
    }
    attributes.put(ATTR_USERNAME, username);
    return true;
  }

  @Override
  public void afterHandshake(
      @NonNull final ServerHttpRequest request,
      @NonNull final ServerHttpResponse response,
      @NonNull final WebSocketHandler wsHandler, final Exception exception) {
    // no-op
  }

  private static String trimToNull(final String value) {
    if (value == null) {
      return null;
    }
    String trimmed = value.trim();
    return trimmed.isEmpty() ? null : trimmed;
  }
}
