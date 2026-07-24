package vip.fairy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vip.fairy.repository.SysUserRepository;

@Service("sysUserService")
public class SysUserService implements UserDetailsService {

  @Autowired
  private SysUserRepository sysUserRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return sysUserRepository.findTopByUsername(s);
  }
}
