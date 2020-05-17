package com.starfish;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.starfish.Actors.BaseActor;
import com.starfish.Actors.Startfish;
import com.starfish.Actors.Turtle;
import com.starfish.Actors.Whirlpool;

public class StarfishCollector extends GameBeta {

  private Turtle _turtle;
  public static final float TURTLE_X = 20;
  public static final float TURTLE_Y = 20;

  private Startfish _startfish;
  public static final float STARFISH_X = 380;
  public static final float STARFISH_Y = 380;

  private BaseActor _ocean;
  public static final float OCEAN_X = 0;
  public static final float OCEAN_Y = 0;
  public static final String OCEAN_FILE_NAME = BaseActor.ASSETS_PATH + "water.jpg";
  public static final float OCEAN_WIDTH = 800;
  public static final float OCEAN_HEIGHT = 600;

  public static final String YOU_WIN_MESSAGE_FILE_PATH = BaseActor.ASSETS_PATH + "you-win.png";
  public static final float YOU_WIN_MESSAGE_DELAY_DURATION = 1;

  @Override
  public void initialize() {
    _ocean = new BaseActor(OCEAN_X, OCEAN_Y, mainStage);
    _ocean.loadTexture(OCEAN_FILE_NAME);
    _ocean.setSize(OCEAN_WIDTH, OCEAN_HEIGHT);

    _startfish = new Startfish(STARFISH_X, STARFISH_Y, mainStage);

    _turtle = new Turtle(TURTLE_X, TURTLE_Y, mainStage);
  }

  @Override
  public void update(float delta) {
    if (_turtle.overlaps(_startfish) && !_startfish.isCollected()) {
      _startfish.collect();

      Whirlpool whirlpool = new Whirlpool(0, 0, mainStage);
      whirlpool.centerAtActor(_startfish);
      whirlpool.scaleBy(0.25f);

      BaseActor youWinMessage = new BaseActor(0, 0, mainStage);
      youWinMessage.loadTexture(YOU_WIN_MESSAGE_FILE_PATH);
      youWinMessage.centerAtPosition(400, 300);
      youWinMessage.setOpacity(0);
      youWinMessage.addAction(Actions.delay(YOU_WIN_MESSAGE_DELAY_DURATION));
      youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));
    }
  }

}