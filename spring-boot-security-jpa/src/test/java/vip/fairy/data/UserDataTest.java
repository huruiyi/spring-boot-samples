package vip.fairy.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import vip.fairy.model.SysUser;
import vip.fairy.repository.SysUserRepository;

@SpringBootTest
public class UserDataTest {

  @Autowired
  private SysUserRepository sysUserRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void testAdd() {
    SysUser user = new SysUser();
    user.setUsername("admin").setPassword(passwordEncoder.encode("123456"));
    sysUserRepository.save(user);
  }
}
