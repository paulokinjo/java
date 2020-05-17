package com.starfish.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Turtle extends BaseActor {

  public static final String[] FILE_NAMES = { BaseActor.ASSETS_PATH + "turtle-1.png",
      BaseActor.ASSETS_PATH + "turtle-2.png", BaseActor.ASSETS_PATH + "turtle-3.png",
      BaseActor.ASSETS_PATH + "turtle-4.png", BaseActor.ASSETS_PATH + "turtle-5.png",
      BaseActor.ASSETS_PATH + "turtle-6.png" };

  public static final float FRAME_DURATION = 0.1f;
  public static final boolean LOOP = true;

  public Turtle(float x, float y, Stage s) {
    super(x, y, s);
    super.loadAnimationFromFiles(FILE_NAMES, FRAME_DURATION, LOOP);
    super.setAcceleration(400);
    super.setMaxSpeed(100);
    super.setDeceleration(400);
    super.setBoundaryPolygon(8);
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      super.accelerateAtAngle(180);
    }

    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      super.accelerateAtAngle(0);
    }

    if (Gdx.input.isKeyPressed(Keys.UP)) {
      super.accelerateAtAngle(90);
    }

    if (Gdx.input.isKeyPressed(Keys.DOWN)) {
      super.accelerateAtAngle(270);
    }

    super.applyPhysics(delta);
    super.setAnimationPaused(!super.isMoving());

    if (super.isMoving()) {
      super.setRotation(super.getMotionAngle());
    }
  }
}