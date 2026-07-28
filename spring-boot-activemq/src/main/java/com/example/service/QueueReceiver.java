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

    private final MessageLogService messageLogService;

    public QueueReceiver(MessageLogService messageLogService) {
        this.messageLogService = messageLogService;
    }

    @JmsListener(destination = "todo-queue", containerFactory = "queueListenerFactory")
    public void onTodoMessage(@Payload TodoMessage message) {
        log.info("Queue [todo-queue] received: {}", message);
        messageLogService.logReceived("todo-queue", "QUEUE", message.toString());
    }

    @JmsListener(destination = "text-queue", containerFactory = "queueListenerFactory")
    public void onTextMessage(@Payload String message) {
        log.info("Queue [text-queue] received: {}", message);
        messageLogService.logReceived("text-queue", "QUEUE", message);
    }

    @JmsListener(destination = "activemq-listener", containerFactory = "queueListenerFactory")
    public void onLegacyMessage(@Payload String message) {
        log.info("Queue [activemq-listener] received: {}", message);
        messageLogService.logReceived("activemq-listener", "QUEUE", message);
    }
}
