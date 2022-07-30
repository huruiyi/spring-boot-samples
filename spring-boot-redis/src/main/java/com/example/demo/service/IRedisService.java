package com.example.demo.service;

import java.util.List;

public interface IRedisService {
    void addMessage(String user, String message);
    List<String> listMessages(String user);
}
