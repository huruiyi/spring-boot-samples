package com.example.websocketchatroom.web;

import com.example.websocketchatroom.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/*
 * @MessageMapping: associates a controller method to the configured endpoint
 *                  receives messages sent to /app/chat
 *
 * @SendTo: send a Message to the defined destination. All subscribers to the
 *          “/topic/messages” destination will receive the message.
 */
@Controller
public class ChatController {

  private static final Logger log = LoggerFactory.getLogger(ChatController.class);

  @MessageMapping("/chat")
  @SendTo("/topic/messages")
  public Message send(Message message) {
    log.info("Received message {}", message);
    log.info("Sending message {}", message);
    return message;
  }
}
