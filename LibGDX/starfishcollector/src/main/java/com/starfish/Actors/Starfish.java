package com.starfish.Actors;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kinsoftwares.libgdx.Actors.BaseActor;

public class Starfish extends BaseActor {
  public static final String FILE_NAME = BaseActor.ASSETS_PATH + "starfish.png";
  public static final float ROTATION_AMOUNT = 30;
  public static final float DURATION = 1;
  public static final float STARFISH_FADEOUT_DURATION = 1;

  private boolean _isCollected;

  public Starfish(float x, float y, Stage s) {
    super(x, y, s);

    loadTexture(FILE_NAME);

    Action spin = Actions.rotateBy(ROTATION_AMOUNT, DURATION);
    super.addAction(Actions.forever(spin));
    super.setBoundaryPolygon(8);

    _isCollected = false;
  }

  public boolean isCollected() {
    return _isCollected;
  }

  public void collect() {
    _isCollected = true;
    super.clearActions();
    super.addAction(Actions.fadeOut(STARFISH_FADEOUT_DURATION));
    super.addAction(Actions.after(Actions.removeActor()));
  }

}