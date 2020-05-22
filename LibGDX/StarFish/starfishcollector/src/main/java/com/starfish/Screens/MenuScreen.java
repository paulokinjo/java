package com.starfish.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.starfish.StarfishGame;
import com.starfish.Actors.BaseActor;
import com.starfish.Core.BaseScreen;

public class MenuScreen extends BaseScreen {

  public static final float OCEAN_X = 0;
  public static final float OCEAN_Y = 0;
  public static final String OCEAN_FILE_NAME = BaseActor.ASSETS_PATH + "water.jpg";
  public static final float OCEAN_WIDTH = 800;
  public static final float OCEAN_HEIGHT = 600;

  public static final int TITLE_X = 0;
  public static final int TITLE_Y = 0;
  public static final String TITLE_FILE_PATH = BaseActor.ASSETS_PATH + "starfish-collector.png";
  public static final int TITLE_POSITION_X = 400;
  public static final int TITLE_POSITION_Y = 300;

  public static final int START_X = 0;
  public static final int START_Y = 0;
  public static final String START_FILE_PATH = BaseActor.ASSETS_PATH + "message-start.png";
  public static final int START_POSITION_X = 400;
  public static final int START_POSITION_Y = 300;

  @Override
  public void initialize() {
    BaseActor ocean = new BaseActor(OCEAN_X, OCEAN_Y, _mainStage);
    ocean.loadTexture(OCEAN_FILE_NAME);
    ocean.scaleBy(OCEAN_WIDTH, OCEAN_HEIGHT);

    BaseActor title = new BaseActor(TITLE_X, TITLE_Y, _mainStage);
    title.loadTexture(TITLE_FILE_PATH);
    title.centerAtPosition(TITLE_POSITION_X, TITLE_POSITION_Y);
    title.moveBy(0, 100);

    BaseActor start = new BaseActor(START_X, START_Y, _mainStage);
    start.loadTexture(START_FILE_PATH);
    start.centerAtPosition(START_POSITION_X, START_POSITION_X);
    start.moveBy(0, -200);
  }

  @Override
  public void update(float delta) {
    if (Gdx.input.isKeyPressed(Keys.S)) {
      StarfishGame.setActiveScreen(new LevelScreen());
    }

  }

}