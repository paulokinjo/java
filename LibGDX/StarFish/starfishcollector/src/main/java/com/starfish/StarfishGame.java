package com.starfish;

import com.starfish.Core.BaseGame;
import com.starfish.Screens.MenuScreen;

public class StarfishGame extends BaseGame {

  @Override
  public void create() {
    super.setActiveScreen(new MenuScreen());
  }
}