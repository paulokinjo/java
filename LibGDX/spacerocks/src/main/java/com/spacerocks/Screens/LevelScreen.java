package com.spacerocks.Screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kinsoftwares.libgdx.Actors.BaseActor;
import com.kinsoftwares.libgdx.Core.BaseScreen;
import com.spacerocks.Actors.Rock;
import com.spacerocks.Actors.Spaceship;
import com.spacerocks.Effects.Explosion;

public class LevelScreen extends BaseScreen {

  public static final int SPACE_X = 0;
  public static final int SPACE_Y = 0;
  public static final String SPACE_FILE_NAME = "space.png";
  public static final int SPACE_WIDTH = 800;
  public static final int SPACE_HEIGHT = 600;

  public static final int SPACESHIP_X = 400;
  public static final int SPACESHIP_Y = 300;

  private Spaceship _spaceship;
  private boolean _gameOver;

  @Override
  public void initialize() {
    final BaseActor space = new BaseActor(SPACE_X, SPACE_Y, _mainStage);
    space.loadTexture(SPACE_FILE_NAME);
    space.setSize(SPACE_WIDTH, SPACE_HEIGHT);
    BaseActor.setWorldBounds(space);

    _gameOver = false;

    _spaceship = new Spaceship(SPACESHIP_X, SPACESHIP_Y, _mainStage);

    // create rocks
    new Rock(600, 500, _mainStage);
    new Rock(600, 300, _mainStage);
    new Rock(600, 100, _mainStage);
    new Rock(400, 100, _mainStage);
    new Rock(200, 100, _mainStage);
    new Rock(200, 300, _mainStage);
    new Rock(200, 500, _mainStage);
    new Rock(400, 500, _mainStage);
  }

  @Override
  public void update(final float arg0) {
    for (BaseActor rockActor : BaseActor.getList(_mainStage, "com.spacerocks.Actors.Rock")) {
      checkCollisionWithSpaceship(rockActor);
      checkCollisionWithLaser(rockActor);
      if (!_gameOver && BaseActor.count(_mainStage, "com.spacerocks.Actors.Rock") == 0)
        winEffect();
    }
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

  private void gameOverEffect() {
    messageWinLose("message-lose.png");
  }

  private void winEffect() {
    messageWinLose("message-win.png");
  }

  private void messageWinLose(String messageAsset) {
    BaseActor message = new BaseActor(0, 0, _uiStage);
    message.loadTexture(messageAsset);
    message.centerAtPosition(400, 300);
    message.setOpacity(0);
    message.addAction(Actions.fadeIn(1));
    _gameOver = true;
  }

  private void checkCollisionWithSpaceship(BaseActor rockActor) {
    if (rockActor.overlaps(_spaceship)) {
      Explosion boom = new Explosion(0, 0, _mainStage);
      if (_spaceship.shieldPower <= 0) {
        boom.centerAtActor(_spaceship);
        _spaceship.remove();
        _spaceship.setPosition(-1000, -1000);

        gameOverEffect();
      } else {
        _spaceship.shieldPower -= 34;
        boom.centerAtActor(rockActor);
        rockActor.remove();
      }
    }
  }

  private void checkCollisionWithLaser(BaseActor rockActor) {
    for (BaseActor laserActor : BaseActor.getList(_mainStage, "com.spacerocks.Powers.Laser")) {
      if (laserActor.overlaps(rockActor)) {
        Explosion boom = new Explosion(0, 0, _mainStage);
        boom.centerAtActor(rockActor);
        laserActor.remove();
        rockActor.remove();
      }
    }
  }
}