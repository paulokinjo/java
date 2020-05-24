package com.spacerocks.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kinsoftwares.libgdx.Actors.BaseActor;
import com.kinsoftwares.libgdx.Helpers.Thrusters;
import com.spacerocks.Powers.Laser;
import com.spacerocks.Powers.Warp;

public class Spaceship extends BaseActor {

  public static final String FILE_NAME = "spaceship.png";
  public static final int BOUNDARY_POLYGON = 8;
  public static final int MAX_SPEED = 200;
  public static final int DECELERATION = 10;

  private Thrusters _thrusters;
  public static final int THRUSTERS_X = 0;
  public static final int THRUSTERS_Y = 0;

  public static final String THRUSTERS_FILE_PATH = "fire.png";

  private Shield _shield;
  public int shieldPower;

  public Spaceship(float x, float y, Stage s) {
    super(x, y, s);
    super.loadTexture(FILE_NAME);
    super.setBoundaryPolygon(BOUNDARY_POLYGON);
    super.setAcceleration(MAX_SPEED);
    super.setDeceleration(DECELERATION);

    _thrusters = new Thrusters(THRUSTERS_X, THRUSTERS_Y, s, THRUSTERS_FILE_PATH);
    super.addActor(_thrusters);
    _thrusters.setPosition(-_thrusters.getWidth(), super.getHeight() / 2 - _thrusters.getHeight() / 2);

    _shield = new Shield(Shield.X, Shield.Y, s);
    _shield.setRotation(-110);
    addActor(_shield);
    _shield.centerAtPosition(super.getWidth() / 2, super.getHeight() / 2);
    shieldPower = 100;
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

    _shield.setOpacity(shieldPower / 100f);
    if (shieldPower <= 0) {
      _shield.setVisible(false);
    }

    super.applyPhysics(delta);
    super.wrapAroundWorld();
  }

  public void warp() {
    if (super.getStage() == null) {
      return;
    }

    Warp warp1 = new Warp(0, 0, super.getStage());
    warp1.centerAtActor(this);
    super.setPosition(MathUtils.random(800), MathUtils.random(600));

    Warp warp2 = new Warp(0, 0, super.getStage());
    warp2.centerAtActor(this);
  }

  public void shoot() {
    if (super.getStage() == null) {
      return;
    }

    Laser laser = new Laser(Laser.X, Laser.Y, super.getStage());
    // laser.centerAtActor(this);
    laser.centerAtActor(this);
    laser.setRotation(super.getRotation());
    laser.setMotionAngle(super.getRotation());
  }
}