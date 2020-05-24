package com.starfish.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kinsoftwares.libgdx.Actors.BaseActor;

public class Rock extends BaseActor {

  public static final String FILE_NAME = BaseActor.ASSETS_PATH + "rock.png";

  public Rock(float x, float y, Stage s) {
    super(x, y, s);
    super.loadTexture(FILE_NAME);
    super.setBoundaryPolygon(8);
  }

}