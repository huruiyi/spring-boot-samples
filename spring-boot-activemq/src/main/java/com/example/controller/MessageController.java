package com.example.controller;

import com.example.model.TodoMessage;
import com.example.service.MessageProducer;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageProducer producer;
    private final AtomicLong counter = new AtomicLong(1);

    public MessageController(MessageProducer producer) {
        this.producer = producer;
    }

    /**
     * Send a TodoMessage to a queue.
     */
    @PostMapping("/queue/todo")
    public String sendTodoToQueue(@RequestBody TodoMessage message) {
        if (message.getId() == null) {
            message.setId(counter.getAndIncrement());
        }
        producer.sendToQueue("todo-queue", message);
        return "Queue message sent: " + message.getId();
    }

    /**
     * Send plain text to a queue.
     */
    @PostMapping("/queue/text")
    public String sendTextToQueue(@RequestParam(defaultValue = "text-queue") String destination,
                                  @RequestParam String text) {
        producer.sendText(destination, text);
        return "Text sent to queue [" + destination + "]";
    }

    /**
     * Send a TodoMessage to a topic.
     */
    @PostMapping("/topic/todo")
    public String sendTodoToTopic(@RequestBody TodoMessage message) {
        if (message.getId() == null) {
            message.setId(counter.getAndIncrement());
        }
        producer.sendToTopic("todo-topic", message);
        return "Topic message sent: " + message.getId();
    }

    /**
     * Send notification text to a topic.
     */
    @PostMapping("/topic/notification")
    public String sendNotification(@RequestParam String text) {
        producer.sendTextToTopic("notification-topic", text);
        return "Notification broadcast to topic";
    }

    /**
     * Send to the legacy queue (backward compatible with old demo).
     */
    @PostMapping("/queue/legacy")
    public String sendLegacy(@RequestParam(defaultValue = "hello activemq") String text) {
        producer.sendText("activemq-listener", text);
        return "Legacy message sent";
    }
}
