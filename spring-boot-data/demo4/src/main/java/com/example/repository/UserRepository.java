package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByName(String name);

  List<User> findByNameAndPassword(String name, String password);

  List<User> findByEmail(String email);

  @Query("from User u where u.name=:name")
  List<User> findUser(@Param("name") String name);

}
