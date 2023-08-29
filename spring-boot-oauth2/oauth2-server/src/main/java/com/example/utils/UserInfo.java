package com.example.utils;

import com.example.domain.User;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserInfo {

  public static User getUser() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    String username = authentication.getName();
    Object principal = authentication.getPrincipal();
    List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
    return new User(username, "************", authorities);
  }

}
