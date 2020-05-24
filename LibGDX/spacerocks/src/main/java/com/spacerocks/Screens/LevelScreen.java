package com.spacerocks.Screens;

import com.badlogic.gdx.Input.Keys;
import com.kinsoftwares.libgdx.Actors.BaseActor;
import com.kinsoftwares.libgdx.Core.BaseScreen;
import com.spacerocks.Actors.Spaceship;

public class LevelScreen extends BaseScreen {

  public static final int SPACE_X = 0;
  public static final int SPACE_Y = 0;
  public static final String SPACE_FILE_NAME = "space.png";
  public static final int SPACE_WIDTH = 800;
  public static final int SPACE_HEIGHT = 600;

  public static final int SPACESHIP_X = 400;
  public static final int SPACESHIP_Y = 300;

  private Spaceship _spaceship;

  @Override
  public void initialize() {
    final BaseActor space = new BaseActor(SPACE_X, SPACE_Y, _mainStage);
    space.loadTexture(SPACE_FILE_NAME);
    space.setSize(SPACE_WIDTH, SPACE_HEIGHT);
    BaseActor.setWorldBounds(space);

    _spaceship = new Spaceship(SPACESHIP_X, SPACESHIP_Y, _mainStage);
  }

  @Override
  public void update(final float arg0) {
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Keys.X) {
      _spaceship.warp();
    }

    if (keycode == Keys.SPACE) {
      _spaceship.shoot();
    }

    return super.keyDown(keycode);
  }

}