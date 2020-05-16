package com.starfish.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Turtle extends BaseActor {

  public static final String[] FILE_NAMES = { BaseActor.ASSETS_PATH + "turtle-1.png",
      BaseActor.ASSETS_PATH + "turtle-2.png", BaseActor.ASSETS_PATH + "turtle-3.png",
      BaseActor.ASSETS_PATH + "turtle-4.png", BaseActor.ASSETS_PATH + "turtle-5.png",
      BaseActor.ASSETS_PATH + "turtle-6.png" };

  public static final float FRAME_DURATION = 0.1f;
  public static final boolean LOOP = true;

  public Turtle(float x, float y, Stage s) {
    super(x, y, s);
    super.loadAnimationFromFiles(FILE_NAMES, FRAME_DURATION, LOOP);
  }
}