package com.starfish.Actors;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Startfish extends BaseActor {
  public static final String FILE_NAME = BaseActor.ASSETS_PATH + "starfish.png";
  public static final float ROTATION_AMOUNT = 30;
  public static final float DURATION = 1;

  public Startfish(float x, float y, Stage s) {
    super(x, y, s);

    loadTexture(FILE_NAME);

    Action spin = Actions.rotateBy(ROTATION_AMOUNT, DURATION);
    super.addAction(Actions.forever(spin));
  }

}