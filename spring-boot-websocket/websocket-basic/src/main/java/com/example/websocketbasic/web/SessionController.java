package com.example.websocketbasic.web;

import com.example.websocketbasic.config.UsernameHandshakeInterceptor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
public class SessionController {

  @GetMapping("/session")
  public Map<String, String> current(final HttpSession session) {
    Object username = session.getAttribute(UsernameHandshakeInterceptor.ATTR_USERNAME);
    if (username == null || username.toString().trim().isEmpty()) {
      return Collections.emptyMap();
    }
    return Collections.singletonMap("username", username.toString());
  }

  @DeleteMapping("/session")
  public void clear(final HttpSession session) {
    session.removeAttribute(UsernameHandshakeInterceptor.ATTR_USERNAME);
  }
}
