package com.example.service;

import com.example.model.TodoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    private static final Logger log = LoggerFactory.getLogger(TopicReceiver.class);

    @JmsListener(destination = "todo-topic", containerFactory = "topicListenerFactory")
    public void onTopicMessage(@Payload TodoMessage message) {
        log.info("Topic [todo-topic] subscriber received: {}", message);
    }

    @JmsListener(destination = "notification-topic", containerFactory = "topicListenerFactory")
    public void onNotification(@Payload String message) {
        log.info("Topic [notification-topic] subscriber received: {}", message);
    }
}
