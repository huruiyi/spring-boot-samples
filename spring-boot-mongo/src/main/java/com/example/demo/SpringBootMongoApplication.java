package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMongoApplication implements CommandLineRunner {

  private final UserRepository userRepository;

  public SpringBootMongoApplication(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootMongoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    userRepository.save(new User("1", "Robert", "robert@gmail.com"));
    userRepository.save(new User("2", "Dan", "dan@gmail.com"));
  }

}
