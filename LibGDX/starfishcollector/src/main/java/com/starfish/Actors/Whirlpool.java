package com.starfish.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kinsoftwares.libgdx.Actors.BaseActor;

public class Whirlpool extends BaseActor {
  public static final String FILE_NAME = BaseActor.ASSETS_PATH + "whirlpool.png";
  public static final int ROWS = 2;
  public static final int COLS = 5;
  public static final float FRAME_DURATION = 0.1f;
  public static final boolean LOOP = true;

  public Whirlpool(float x, float y, Stage s) {
    super(x, y, s);
    super.loadAnimationFromSheet(FILE_NAME, ROWS, COLS, FRAME_DURATION, LOOP);
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    if (super.isAnimationFinished())
      super.remove();
  }
}