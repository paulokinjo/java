package com.starfish.Screens;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kinsoftwares.libgdx.Core.BaseScreen;
import com.starfish.Actors.BaseActor;
import com.starfish.Actors.Rock;
import com.starfish.Actors.Starfish;
import com.starfish.Actors.Turtle;
import com.starfish.Actors.Whirlpool;
import com.starfish.Utils.Coordinates;

public class LevelScreen extends BaseScreen {

  private Turtle _turtle;
  public static final float TURTLE_X = 20;
  public static final float TURTLE_Y = 20;

  public static final float OCEAN_X = 0;
  public static final float OCEAN_Y = 0;
  public static final String OCEAN_FILE_NAME = BaseActor.ASSETS_PATH + "water-border.jpg";
  public static final float OCEAN_WIDTH = 1200;
  public static final float OCEAN_HEIGHT = 900;

  public static final String YOU_WIN_MESSAGE_FILE_PATH = BaseActor.ASSETS_PATH + "you-win.png";
  public static final float YOU_WIN_MESSAGE_DELAY_DURATION = 1;

  public static final float ROCK_X = 200;
  public static final float ROCK_Y = 200;

  public static final Coordinates[] STARFISH_LIST = { new Coordinates(400, 400), new Coordinates(500, 100),
      new Coordinates(100, 450), new Coordinates(200, 250) };

  public static final Coordinates[] ROCK_LIST = { new Coordinates(200, 150), new Coordinates(100, 300),
      new Coordinates(300, 350), new Coordinates(450, 200) };

  private boolean _isWin;

  @Override
  public void initialize() {
    BaseActor ocean = new BaseActor(OCEAN_X, OCEAN_Y, _mainStage);
    ocean.loadTexture(OCEAN_FILE_NAME);
    ocean.setSize(OCEAN_WIDTH, OCEAN_HEIGHT);

    BaseActor.setWorldBounds(ocean);

    for (Coordinates coordinates : STARFISH_LIST) {
      new Starfish(coordinates.getRow(), coordinates.getColumn(), _mainStage);
    }

    for (Coordinates coordinates : ROCK_LIST) {
      new Rock(coordinates.getRow(), coordinates.getColumn(), _mainStage);
    }

    _turtle = new Turtle(TURTLE_X, TURTLE_Y, _mainStage);
    _isWin = false;

  }

  @Override
  public void update(float delta) {
    // _rock.preventOverlap(_turtle);
    // _turtle.preventOverlap(_rock);
    for (BaseActor rockActor : BaseActor.getList(_mainStage, "com.starfish.Actors.Rock")) {
      _turtle.preventOverlap(rockActor);
    }

    for (BaseActor starfishActor : BaseActor.getList(_mainStage, "com.starfish.Actors.Starfish")) {
      Starfish starfish = (Starfish) starfishActor;
      if (_turtle.overlaps(starfish) && !starfish.isCollected()) {
        starfish.collect();

        Whirlpool whirlpool = new Whirlpool(0, 0, _mainStage);
        whirlpool.centerAtActor(starfish);
        whirlpool.scaleBy(0.25f);
      }
    }

    if (BaseActor.count(_mainStage, "com.starfish.Actors.Starfish") == 0 && !_isWin) {
      _isWin = true;
      BaseActor youWinMessage = new BaseActor(0, 0, _uiStage);
      youWinMessage.loadTexture(YOU_WIN_MESSAGE_FILE_PATH);
      youWinMessage.centerAtPosition(400, 300);
      youWinMessage.setOpacity(0);
      youWinMessage.addAction(Actions.delay(YOU_WIN_MESSAGE_DELAY_DURATION));
      youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));
    }

  }

}