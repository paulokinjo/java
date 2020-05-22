package com.starfish.Core;

import com.badlogic.gdx.Game;

public abstract class BaseGame extends Game {
  private static BaseGame _game;

  public BaseGame() {
    super();
    _game = this;
  }

  public static void setActiveScreen(BaseScreen screen) {
    _game.setScreen(screen);
  }

}