package com.example.service;

import com.example.model.TodoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);

    private final JmsTemplate jmsTemplate;

    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Send a TodoMessage to a queue (point-to-point).
     */
    public void sendToQueue(String destination, TodoMessage message) {
        jmsTemplate.convertAndSend(destination, message);
        log.info("Sent to queue [{}]: {}", destination, message);
    }

    /**
     * Send a TodoMessage to a topic (publish-subscribe).
     */
    public void sendToTopic(String destination, TodoMessage message) {
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.convertAndSend(destination, message);
        jmsTemplate.setPubSubDomain(false);
        log.info("Sent to topic [{}]: {}", destination, message);
    }

    /**
     * Send a plain text message.
     */
    public void sendText(String destination, String text) {
        jmsTemplate.convertAndSend(destination, text);
        log.info("Sent text to [{}]: {}", destination, text);
    }

    /**
     * Send a plain text message to a topic.
     */
    public void sendTextToTopic(String destination, String text) {
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.convertAndSend(destination, text);
        jmsTemplate.setPubSubDomain(false);
        log.info("Sent text to topic [{}]: {}", destination, text);
    }
}
