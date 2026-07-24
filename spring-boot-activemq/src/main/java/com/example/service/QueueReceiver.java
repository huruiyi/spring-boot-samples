package com.example.service;

import com.example.model.TodoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver {

    private static final Logger log = LoggerFactory.getLogger(QueueReceiver.class);

    @JmsListener(destination = "todo-queue", containerFactory = "queueListenerFactory")
    public void onTodoMessage(@Payload TodoMessage message) {
        log.info("Queue [todo-queue] received: {}", message);
    }

    @JmsListener(destination = "text-queue", containerFactory = "queueListenerFactory")
    public void onTextMessage(@Payload String message) {
        log.info("Queue [text-queue] received: {}", message);
    }

    @JmsListener(destination = "activemq-listener", containerFactory = "queueListenerFactory")
    public void onLegacyMessage(@Payload String message) {
        log.info("Queue [activemq-listener] received: {}", message);
    }
}
