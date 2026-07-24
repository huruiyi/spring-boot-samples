package vip.fairy.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import vip.fairy.model.SysUser;

@Component
public class MyAuthSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    SysUser details = (SysUser) authentication.getPrincipal();
    System.out.println(details.getUsername());
    response.sendRedirect("/home");
  }
}
