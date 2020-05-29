package com.spacerocks.Actors;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.kinsoftwares.libgdx.Actors.BaseActor;

public class Rock extends BaseActor {

  public static String FILE_NAME = "rock.png";
  private float _random;

  public Rock(float x, float y, Stage s) {
    super(x, y, s);
    super.loadTexture(FILE_NAME);

    _random = MathUtils.random(30);
    createRotation(_random);
    handleSpeed(_random);
    setMotionAngle(MathUtils.random(360));

  }

  @Override
  public void act(float delta) {
    super.act(delta);
    super.applyPhysics(delta);
    super.wrapAroundWorld();
  }

  private void createRotation(float random) {
    RotateByAction repreatedAction = Actions.rotateBy(30 + random, 1);
    Action forever = Actions.forever(repreatedAction);
    super.addAction(forever);
  }

  private void handleSpeed(float random) {
    setSpeed(50 + random);
    setMaxSpeed(50 + random);
    setDeceleration(0);
  }

}