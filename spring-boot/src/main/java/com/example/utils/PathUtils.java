package com.example.utils;

import javax.servlet.http.HttpServletRequest;

public class PathUtils {

  public static String getDomain(HttpServletRequest request) {
    return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
  }

}
