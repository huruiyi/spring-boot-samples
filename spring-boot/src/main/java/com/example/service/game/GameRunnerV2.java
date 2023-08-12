package com.example.service.game;

import com.example.service.game.service.GamingConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameRunnerV2 {

  @Autowired
  private GamingConsole gamingConsole;

  public void setGame(GamingConsole game) {
    System.out.println("Using Setter");
    this.gamingConsole = game;
  }

  public void run() {
    gamingConsole.up();
    gamingConsole.down();
    gamingConsole.left();
    gamingConsole.right();
  }

}
