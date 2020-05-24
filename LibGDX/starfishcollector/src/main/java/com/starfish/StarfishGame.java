package com.starfish;

import com.kinsoftwares.libgdx.Core.BaseGame;
import com.starfish.Screens.MenuScreen;

public class StarfishGame extends BaseGame {

  @Override
  public void create() {
    super.setActiveScreen(new MenuScreen());
  }
}