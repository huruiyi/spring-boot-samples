package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByName(String name);

    public List<User> findByNameAndPassword(String name, String password);

    public List<User> findByEmail(String email);

    @Query("from User u where u.name=:name")
    public List<User> findUser(@Param("name") String name);

}