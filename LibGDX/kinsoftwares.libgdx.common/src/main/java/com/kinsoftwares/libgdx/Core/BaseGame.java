package com.kinsoftwares.libgdx.Core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public abstract class BaseGame extends Game {
  private static BaseGame _game;

  public BaseGame() {
    super();
    _game = this;
  }

  public static void setActiveScreen(BaseScreen screen) {
    _game.setScreen(screen);
  }

  @Override
  public void create() {
    // prepare for multiple classes/stages to receive discrete input
    InputMultiplexer processor = new InputMultiplexer();
    Gdx.input.setInputProcessor(processor);
  }
}