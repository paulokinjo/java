package com.starfish.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.kinsoftwares.libgdx.Actors.BaseActor;
import com.kinsoftwares.libgdx.Core.BaseGame;
import com.kinsoftwares.libgdx.Core.BaseScreen;
import com.starfish.StarfishGame;

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

  // public static final int START_X = 0;
  // public static final int START_Y = 0;
  // public static final String START_FILE_PATH = BaseActor.ASSETS_PATH +
  // "message-start.png";
  // public static final int START_POSITION_X = 400;
  // public static final int START_POSITION_Y = 300;

  @Override
  public void initialize() {
    BaseActor ocean = new BaseActor(OCEAN_X, OCEAN_Y, _mainStage);
    ocean.loadTexture(OCEAN_FILE_NAME);
    ocean.scaleBy(OCEAN_WIDTH, OCEAN_HEIGHT);

    BaseActor title = new BaseActor(TITLE_X, TITLE_Y, _mainStage);
    title.loadTexture(TITLE_FILE_PATH);
    // title.centerAtPosition(TITLE_POSITION_X, TITLE_POSITION_Y);
    super._uiTable.add(title).colspan(2);
    // title.moveBy(0, 100);

    // BaseActor start = new BaseActor(START_X, START_Y, _mainStage);
    // start.loadTexture(START_FILE_PATH);
    // start.centerAtPosition(START_POSITION_X, START_POSITION_X);
    // start.moveBy(0, -200);

    initializeTextButton();

  }

  @Override
  public void update(float delta) {
    if (Gdx.input.isKeyPressed(Keys.S)) {
      StarfishGame.setActiveScreen(new LevelScreen());
    }

  }

  @Override
  public boolean keyDown(int keycode) {
    if (Gdx.input.isKeyPressed(Keys.ENTER)) {
      StarfishGame.setActiveScreen(new LevelScreen());
    }

    if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
      Gdx.app.exit();
    }

    return super.keyDown(keycode);
  }

  private void initializeTextButton() {
    _uiTable.row();
    initializeStartButton();
    initializeQuitButton();
  }

  private void initializeStartButton() {
    TextButton startButton = new TextButton("Start", BaseGame.TextButtonStyle);
    // startButton.setPosition(150, 150);
    super._uiTable.add(startButton);
    // _uiStage.addActor(startButton);

    startButton.addListener((Event e) -> {
      if (BaseGame.IsTouchDownInputEvent(e)) {
        StarfishGame.setActiveScreen(new LevelScreen());
        return false;
      }

      return false;
    });
  }

  private void initializeQuitButton() {
    TextButton quitButton = new TextButton("Quit", BaseGame.TextButtonStyle);
    // quitButton.setPosition(500, 150);
    super._uiTable.add(quitButton);
    // _uiStage.addActor(quitButton);

    quitButton.addListener((Event e) -> {
      if (BaseGame.IsTouchDownInputEvent(e)) {
        Gdx.app.exit();
        return false;
      }

      return false;
    });
  }

}