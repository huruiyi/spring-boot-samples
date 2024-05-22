package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.SystemUsersMapper;
import com.example.demo.model.SystemUser;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService implements UserDetailsService {

  private final SystemUsersMapper usersMapper;

  public SystemUserService(SystemUsersMapper usersMapper) {
    this.usersMapper = usersMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    QueryWrapper<SystemUser> wrapper = new QueryWrapper<>();
    wrapper.eq("username", username);
    SystemUser users = usersMapper.selectOne(wrapper);
    if (users == null) {
      throw new UsernameNotFoundException("用户名不存在！");
    }
    List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin,ROLE_client");
    return new User(users.getUsername(), users.getPassword(), authorities);
  }


}
