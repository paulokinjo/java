package com.spacerocks.Actors;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kinsoftwares.libgdx.Actors.BaseActor;

public class Shield extends BaseActor {

  public static final String FILE_NAME = "shields.png";
  public static final int X = 0;
  public static final int Y = 0;

  public static final float SEQ1_PULSE_X = 1.05f;
  public static final float SEQ1_PULSE_Y = 1.05f;
  public static final float SEQ1_PULSE_DURATION = 1;

  public static final float SEQ2_PULSE_X = 0.95f;
  public static final float SEQ2_PULSE_Y = 0.95f;
  public static final float SEQ2_PULSE_DURATION = 1;

  public Shield(float x, float y, Stage s) {
    super(x, y, s);
    super.loadTexture(FILE_NAME);

    Action pulse = Actions.sequence(Actions.scaleTo(SEQ1_PULSE_X, SEQ1_PULSE_Y, SEQ1_PULSE_DURATION),
        Actions.scaleTo(SEQ2_PULSE_X, SEQ2_PULSE_Y, SEQ2_PULSE_DURATION));

    addAction(Actions.forever(pulse));
  }
}