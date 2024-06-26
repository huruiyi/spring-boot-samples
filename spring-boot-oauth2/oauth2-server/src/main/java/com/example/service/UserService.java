package com.example.service;

import com.example.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Created by macro on 2019/9/30.
 */
@Service
public class UserService implements UserDetailsService {

  private List<User> userList;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostConstruct
  public void initData() {
    String password = passwordEncoder.encode("123456");
    userList = new ArrayList<>();
    userList.add(new User("admin", password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin,ROLE_client")));
    userList.add(new User("user1", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    userList.add(new User("user2", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<User> findUserList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
    if (!CollectionUtils.isEmpty(findUserList)) {
      return findUserList.get(0);
    } else {
      throw new UsernameNotFoundException("用户名或密码错误");
    }
  }
}
