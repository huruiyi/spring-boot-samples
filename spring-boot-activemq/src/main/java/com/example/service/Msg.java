package com.example.service;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;


public class Msg implements MessageCreator {

  @Override
  public Message createMessage(Session session) throws JMSException {
    return session.createTextMessage(new Date() + " 测试消息");
  }

}
