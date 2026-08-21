package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.utils.SecureClientIpResolver;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

class SecureClientIpResolverTest {

  private SecureClientIpResolver resolver;

  @BeforeEach
  void setUp() {
    resolver = new SecureClientIpResolver(Arrays.asList(
        "10.0.0.10/32",
        "10.0.0.11/32",
        "127.0.0.1/32"
    ));
  }

  @Test
  void directConnectionUsesRemoteAddr() {
    HttpServletRequest request = mockRequest("203.0.113.10", null, null);
    assertEquals("203.0.113.10", resolver.resolve(request));
  }

  @Test
  void ignoresForwardedHeadersWhenNotFromTrustedProxy() {
    HttpServletRequest request = mockRequest(
        "203.0.113.10",
        "8.8.8.8",
        "1.1.1.1"
    );
    assertEquals("203.0.113.10", resolver.resolve(request));
  }

  @Test
  void walksXffFromRightAndSkipsTrustedProxies() {
    // client 伪造了左侧 8.8.8.8；右侧由可信代理追加
    HttpServletRequest request = mockRequest(
        "10.0.0.10",
        "8.8.8.8, 203.0.113.50, 10.0.0.11",
        null
    );
    assertEquals("203.0.113.50", resolver.resolve(request));
  }

  @Test
  void fallsBackToXRealIpWhenXffMissing() {
    HttpServletRequest request = mockRequest(
        "10.0.0.10",
        null,
        "198.51.100.20"
    );
    assertEquals("198.51.100.20", resolver.resolve(request));
  }

  @Test
  void returnsRemoteAddrWhenTrustedProxyOmitsClientIp() {
    HttpServletRequest request = mockRequest("10.0.0.10", null, null);
    assertEquals("10.0.0.10", resolver.resolve(request));
  }

  @Test
  void invalidRemoteAddrReturnsInvalid() {
    HttpServletRequest request = mockRequest("not-an-ip", null, null);
    assertEquals("invalid", resolver.resolve(request));
  }

  private static HttpServletRequest mockRequest(
      String remoteAddr,
      String xForwardedFor,
      String xRealIp
  ) {
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRemoteAddr()).thenReturn(remoteAddr);
    when(request.getHeader("X-Forwarded-For")).thenReturn(xForwardedFor);
    when(request.getHeader("X-Real-IP")).thenReturn(xRealIp);
    return request;
  }
}
