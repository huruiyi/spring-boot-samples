package com.example.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BrowseLogFilter implements Filter {

  private Logger logger = LoggerFactory.getLogger(getClass());


  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String path = ((HttpServletRequest) request).getRequestURI();
    logger.debug("Path=" + path);
    System.out.println("\n****************************");
    System.out.println("Path:" + path);
    System.out.println("****************************");
    chain.doFilter(request, response);
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {

  }

  @Override
  public void destroy() {

  }
}
