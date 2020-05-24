package com.spacerocks.Powers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kinsoftwares.libgdx.Actors.BaseActor;

public class Warp extends BaseActor {

  public static final String FILE_NAME = "warp.png";
  public static final int ROWS = 4;
  public static final int COLS = 8;
  public static final float FRAME_DURATION = 0.05f;
  public static final boolean LOOP = true;
  public static final int DELAY = 1;
  public static final float FADE_OUT = 0.5f;

  public Warp(float x, float y, Stage s) {
    super(x, y, s);

    super.loadAnimationFromSheet(FILE_NAME, ROWS, COLS, FRAME_DURATION, LOOP);

    super.addAction(Actions.delay(DELAY));
    super.addAction(Actions.after(Actions.fadeOut(FADE_OUT)));
    super.addAction(Actions.after(Actions.removeActor()));

  }

}