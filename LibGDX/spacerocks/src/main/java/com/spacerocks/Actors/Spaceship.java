package com.spacerocks.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kinsoftwares.libgdx.Actors.BaseActor;

public class Spaceship extends BaseActor {

  public static final String SPACESHIP_FILE_NAME = BaseActor.ASSETS_PATH + "spaceship.png";
  public static final int SPACESHIP_BOUNDARY_POLYGON = 8;
  public static final int SPACESHIP_MAX_SPEED = 200;
  public static final int SPACESHIP_DECELERATION = 10;

  public Spaceship(float x, float y, Stage s) {
    super(x, y, s);
    super.loadTexture(SPACESHIP_FILE_NAME);
    super.setBoundaryPolygon(SPACESHIP_BOUNDARY_POLYGON);
    super.setAcceleration(SPACESHIP_MAX_SPEED);
    super.setDeceleration(SPACESHIP_DECELERATION);
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
    }

    super.applyPhysics(delta);
    super.wrapAroundWorld();
  }
}