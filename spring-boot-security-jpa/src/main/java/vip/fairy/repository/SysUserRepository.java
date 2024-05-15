package vip.fairy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.fairy.model.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

  SysUser findTopByUsername(String username);
}
