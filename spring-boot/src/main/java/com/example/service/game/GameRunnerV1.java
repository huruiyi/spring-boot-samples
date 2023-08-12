package com.example.service.game;

import com.example.service.game.service.GamingConsole;
import org.springframework.stereotype.Component;

@Component
public class GameRunnerV1 {

  private GamingConsole gamingConsole;

  public GameRunnerV1(GamingConsole game) {
    System.out.println("Using Constructor");
    this.gamingConsole = game;
  }


  public void run() {
    gamingConsole.up();
    gamingConsole.down();
    gamingConsole.left();
    gamingConsole.right();
  }

}
