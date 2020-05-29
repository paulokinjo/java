package com.spacerocks.Effects;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kinsoftwares.libgdx.Actors.BaseActor;

public class Explosion extends BaseActor {

  public static final String FILE_BANE = "explosion.png";
  public static final int ROWS = 6;
  public static final int COLS = 6;
  public static final float FRAME_DURATION = 0.03f;
  public static final boolean LOOP = false;

  public Explosion(float x, float y, Stage s) {
    super(x, y, s);
    super.loadAnimationFromSheet(FILE_BANE, ROWS, COLS, 0.03f, LOOP);
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    if (super.isAnimationFinished()) {
      super.remove();
    }
  }
}