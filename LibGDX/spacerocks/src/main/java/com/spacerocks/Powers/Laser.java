package com.spacerocks.Powers;

import java.io.Console;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kinsoftwares.libgdx.Actors.BaseActor;

public class Laser extends BaseActor {

  public static final String FILE_NAME = "laser.png";
  public static final int DELAY = 1;
  public static final float FADE_OUT = 0.5f;
  public static final int SPEED = 400;
  public static final int MAX_SPEED = 400;
  public static final int DECELERATION = 0;

  public static final int X = 0;
  public static final int Y = 0;

  public Laser(float x, float y, Stage s) {
    super(x, y, s);
    super.loadTexture(FILE_NAME);
    super.addAction(Actions.delay(1));
    super.addAction(Actions.after(Actions.fadeOut(0.5f)));
    super.addAction(Actions.after(Actions.removeActor()));
    super.setSpeed(SPEED);
    super.setMaxSpeed(MAX_SPEED);
    super.setDeceleration(DECELERATION);
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    super.applyPhysics(delta);
    super.wrapAroundWorld();
  }

  @Override
  public void centerAtActor(BaseActor other) {
    super.centerAtActor(other);
    float currentX = super.getX();
    float currentY = super.getY();

    // Make the shot to start from spaceship nose
    // float spaceshipRotation = Math.abs(other.getRotation());
    // if (spaceshipRotation > 0 && spaceshipRotation <= 90) {
    // System.out.println(other.getRotation());
    // currentX += other.getWidth() / 2;
    // currentY += other.getHeight() / 2;
    // } else if (spaceshipRotation > 90 && spaceshipRotation <= 180) {
    // currentX -= other.getWidth() / 2;
    // currentY += other.getHeight() / 2;
    // } else if (spaceshipRotation > 180 && spaceshipRotation <= 270) {
    // currentX -= other.getWidth() / 2;
    // currentY -= other.getHeight() / 2;
    // } else {
    // currentX += other.getWidth() / 2;
    // currentY -= other.getHeight() / 2;
    // }

    super.setPosition(currentX, currentY);
  }
}