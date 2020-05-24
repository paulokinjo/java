package com.spacerocks.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kinsoftwares.libgdx.Actors.BaseActor;
import com.kinsoftwares.libgdx.Helpers.Thrusters;

public class Spaceship extends BaseActor {

  public static final String SPACESHIP_FILE_NAME = BaseActor.ASSETS_PATH + "spaceship.png";
  public static final int SPACESHIP_BOUNDARY_POLYGON = 8;
  public static final int SPACESHIP_MAX_SPEED = 200;
  public static final int SPACESHIP_DECELERATION = 10;

  private Thrusters _thrusters;
  public static final int THRUSTERS_X = 0;
  public static final int THRUSTERS_Y = 0;

  public static final String THRUSTERS_FILE_PATH = BaseActor.ASSETS_PATH + "fire.png";

  public Spaceship(float x, float y, Stage s) {
    super(x, y, s);
    super.loadTexture(SPACESHIP_FILE_NAME);
    super.setBoundaryPolygon(SPACESHIP_BOUNDARY_POLYGON);
    super.setAcceleration(SPACESHIP_MAX_SPEED);
    super.setDeceleration(SPACESHIP_DECELERATION);
    _thrusters = new Thrusters(THRUSTERS_X, THRUSTERS_Y, s, THRUSTERS_FILE_PATH);
    super.addActor(_thrusters);
    _thrusters.setPosition(-_thrusters.getWidth(), super.getHeight() / 2 - _thrusters.getHeight() / 2);
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    float degreesPerSecond = 120f; // rotation speed
    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      super.rotateBy(degreesPerSecond * delta);
    }

    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      super.rotateBy(-degreesPerSecond * delta);
    }

    if (Gdx.input.isKeyPressed(Keys.UP)) {
      super.accelerateAtAngle(super.getRotation());
      _thrusters.setVisible(true);
    } else {
      _thrusters.setVisible(false);
    }

    super.applyPhysics(delta);
    super.wrapAroundWorld();
  }
}