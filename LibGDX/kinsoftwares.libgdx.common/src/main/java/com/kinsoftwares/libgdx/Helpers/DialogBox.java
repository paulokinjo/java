package com.kinsoftwares.libgdx.Helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kinsoftwares.libgdx.Actors.BaseActor;
import com.kinsoftwares.libgdx.Core.BaseGame;

public class DialogBox extends BaseActor {

  private Label _dialogLabel;
  private float _padding = 16;

  public static final String FILENAME = "dialog-translucent.png";

  public DialogBox(float x, float y, Stage s) {
    super(x, y, s);
    super.loadTexture(FILENAME);

    initializeDialogLabel();
    super.addActor(_dialogLabel);
  }

  private void initializeDialogLabel() {
    _dialogLabel = new Label(" ", BaseGame.LabelStyle);
    _dialogLabel.setWrap(true);
    _dialogLabel.setAlignment(Align.topLeft);
    _dialogLabel.setPosition(_padding, _padding);
    setDialogSize(super.getWidth(), super.getHeight());
  }

  public void setDialogSize(float width, float height) {
    super.setSize(width, height);
    _dialogLabel.setWidth(width - 2 * _padding);
    _dialogLabel.setHeight(height - 2 * _padding);
  }

  public void setText(String text) {
    _dialogLabel.setText(text);
  }

  public void setFontScale(float scale) {
    _dialogLabel.setFontScale(scale);
  }

  public void setFontColor(Color color) {
    _dialogLabel.setColor(color);
  }

  public void setBackgroundColor(Color color) {
    super.setColor(color);
  }

  public void alignTopLeft() {
    _dialogLabel.setAlignment(Align.topLeft);
  }

  public void alignCenter() {
    _dialogLabel.setAlignment(Align.center);
  }
}