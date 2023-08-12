package com.example.service.game.service.impl;

import com.example.service.game.service.GamingConsole;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class SuperContraGame implements GamingConsole {

  public void up() {
    System.out.println("SuperContraGame up");
  }

  public void down() {
    System.out.println("SuperContraGame down");
  }

  public void left() {
    System.out.println("SuperContraGame left");
  }

  public void right() {
    System.out.println("SuperContraGame right");
  }


}
