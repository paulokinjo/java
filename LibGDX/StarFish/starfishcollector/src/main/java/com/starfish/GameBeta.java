package com.starfish;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class GameBeta extends Game {
  protected Stage mainStage;

  public void create() {
    mainStage = new Stage();
    initialize();
  }

  public abstract void initialize();

  @Override
  public void render() {
    float delta = Gdx.graphics.getDeltaTime();

    // act method
    mainStage.act(delta);

    // Defined by user
    update(delta);

    // clear the screen
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // draw the graphics
    mainStage.draw();
  }

  public abstract void update(float delta);
}