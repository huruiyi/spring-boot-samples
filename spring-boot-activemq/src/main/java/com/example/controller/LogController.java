package com.example.controller;

import com.example.service.MessageLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LogController {

    private final MessageLogService messageLogService;

    public LogController(MessageLogService messageLogService) {
        this.messageLogService = messageLogService;
    }

    @GetMapping("/log")
    public List<Map<String, Object>> getRecentLogs() {
        return messageLogService.getRecentLogs();
    }
}
