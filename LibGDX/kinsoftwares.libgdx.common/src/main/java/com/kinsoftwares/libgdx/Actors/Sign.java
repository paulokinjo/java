package com.kinsoftwares.libgdx.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Sign extends BaseActor {

  // the text to be displayed
  private String _text;
  // used to determine if sign text is currently being displayed.
  private boolean _viewing;

  public static final String FILENAME = "sign.png";

  public Sign(float x, float y, Stage s) {
    super(x, y, s);
    super.loadTexture(FILENAME);

    _text = " ";
    _viewing = false;
  }

  public void setText(String t) {
    _text = t;
  }

  public String getText() {
    return _text;
  }

  public void setViewing(boolean v) {
    _viewing = v;
  }

  public boolean isViewing() {
    return _viewing;
  }

}