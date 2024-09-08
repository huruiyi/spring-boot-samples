package com.example;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoApplication implements CommandLineRunner {

  private final UserRepository userRepository;

  public MongoApplication(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(MongoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    userRepository.save(new User("1", "Robert", "robert@gmail.com"));
    userRepository.save(new User("2", "Dan", "dan@gmail.com"));
  }

}
