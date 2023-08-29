package com.example.api;

import com.example.api.model.UserProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

  @RequestMapping("/hello")
  @ResponseBody
  public String hello() {
    return "Hello World";
  }

  @RequestMapping("/api/profile")
  public ResponseEntity<UserProfile> profile() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String email = user.getUsername() + "@mailinator.com";

    UserProfile profile = new UserProfile();
    profile.setName(user.getUsername());
    profile.setEmail(email);

    return ResponseEntity.ok(profile);
  }

}
