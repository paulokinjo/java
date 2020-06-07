package com.kinsoftwares.libgdx.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class BaseScreen implements Screen, InputProcessor {
  protected Stage _mainStage;
  protected Stage _uiStage;
  protected Table _uiTable;

  public BaseScreen() {
    super();
    _uiTable = new Table();
    _uiTable.setFillParent(true);

    _mainStage = new Stage();

    _uiStage = new Stage();
    _uiStage.addActor(_uiTable);

    initialize();
  }

  public abstract void initialize();

  public abstract void update(float delta);

  @Override
  public void render(final float delta) {
    _uiStage.act(delta);
    _mainStage.act(delta);

    update(delta);

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    _mainStage.draw();
    _uiStage.draw();
  }

  @Override
  public void resize(final int width, final int height) {
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void dispose() {
  }

  @Override
  public void show() {
    InputMultiplexer processor = (InputMultiplexer) Gdx.input.getInputProcessor();
    if (processor != null) {
      processor.addProcessor(this);
      processor.addProcessor(_uiStage);
      processor.addProcessor(_mainStage);
    }
  }

  @Override
  public void hide() {
    InputMultiplexer processor = (InputMultiplexer) Gdx.input.getInputProcessor();
    if (processor != null) {
      processor.removeProcessor(this);
      processor.removeProcessor(_uiStage);
      processor.removeProcessor(_mainStage);
    }
  }

  /**
   * Input Processor Section
   */
  @Override
  public boolean keyDown(int keycode) {
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }
}