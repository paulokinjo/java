package com.starfish.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseScreen implements Screen {
  protected Stage _mainStage;
  protected Stage _uiStage;

  public BaseScreen() {
    super();

    _mainStage = new Stage();
    _uiStage = new Stage();

    initialize();
  }

  public abstract void initialize();

  public abstract void update(float delta);

  @Override
  public void render(float delta) {
    _uiStage.act(delta);
    _mainStage.act(delta);

    update(delta);

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    _mainStage.draw();
    _uiStage.draw();
  }

  @Override
  public void resize(int width, int height) {
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
  }

  @Override
  public void hide() {
  }
}