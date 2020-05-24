package com.kinsoftwares.libgdx.Helpers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kinsoftwares.libgdx.Actors.BaseActor;

public class Thrusters extends BaseActor {

  public Thrusters(float x, float y, Stage s, String filePath) {
    super(x, y, s);
    super.loadTexture(filePath);
  }
}