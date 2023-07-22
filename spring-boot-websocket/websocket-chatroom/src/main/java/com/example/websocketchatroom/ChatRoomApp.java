package com.example.websocketchatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/*
 * Websocket Endpoint is exposed at: http://localhost:8080/chatroom
 *
 * Server receives messages under /app/chat
 *
 * Server publishes Messages to: /topic/messages
 */
@SpringBootApplication
@EnableAsync
public class ChatRoomApp {

  public static void main(String[] args) {
    SpringApplication.run(ChatRoomApp.class, args);
  }

}
