package com.spacerocks;

import com.kinsoftwares.libgdx.Core.BaseGame;
import com.spacerocks.Screens.LevelScreen;

public class SpaceGame extends BaseGame {

  @Override
  public void create() {
    super.create();
    super.setActiveScreen(new LevelScreen());
  }
}