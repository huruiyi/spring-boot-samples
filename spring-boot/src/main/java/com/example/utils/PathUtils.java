package com.example.utils;

import javax.servlet.http.HttpServletRequest;

public final class PathUtils {

  private PathUtils() {
  }

  /**
   * 拼装 {@code scheme://host[:port]}，默认端口（http/80、https/443）省略端口号。
   */
  public static String baseUrl(String scheme, String host, int port) {
    boolean defaultPort = ("http".equalsIgnoreCase(scheme) && port == 80)
        || ("https".equalsIgnoreCase(scheme) && port == 443);
    return scheme + "://" + host + (defaultPort ? "" : ":" + port);
  }

  /**
   * 根据当前请求拼装站点根地址（不含 context-path）。
   */
  public static String baseUrl(HttpServletRequest request) {
    return baseUrl(request.getScheme(), request.getServerName(), request.getServerPort());
  }

  /**
   * 站点根地址 + context-path，末尾带 {@code /}。
   */
  public static String getDomain(HttpServletRequest request) {
    String contextPath = request.getContextPath();
    if (contextPath == null || contextPath.isEmpty()) {
      return baseUrl(request) + "/";
    }
    return baseUrl(request) + (contextPath.endsWith("/") ? contextPath : contextPath + "/");
  }

}
