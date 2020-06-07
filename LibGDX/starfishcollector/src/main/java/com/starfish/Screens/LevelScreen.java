package com.starfish.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kinsoftwares.libgdx.Actors.BaseActor;
import com.kinsoftwares.libgdx.Actors.Sign;
import com.kinsoftwares.libgdx.Core.BaseGame;
import com.kinsoftwares.libgdx.Core.BaseScreen;
import com.kinsoftwares.libgdx.Helpers.DialogBox;
import com.starfish.StarfishGame;
import com.starfish.Actors.Rock;
import com.starfish.Actors.Starfish;
import com.starfish.Actors.Turtle;
import com.starfish.Actors.Whirlpool;
import com.starfish.Utils.Coordinates;

public class LevelScreen extends BaseScreen {

  private static final String STARFISH_REMAINING_TEXT = "Starfish Left: ";
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
  private Label _starfishLabel;

  private DialogBox _dialogBox;

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

    super._uiTable.pad(10);
    initializeLabel();
    super._uiTable.add().expandX().expandY();
    initializeButton();

    _turtle = new Turtle(TURTLE_X, TURTLE_Y, _mainStage);
    _isWin = false;

    initializeDialogBox();
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

    _starfishLabel.setText(STARFISH_REMAINING_TEXT + BaseActor.count(_mainStage, "com.starfish.Actors.Starfish"));

    handleSigns();
  }

  private void initializeLabel() {
    _starfishLabel = new Label(STARFISH_REMAINING_TEXT, BaseGame.LabelStyle);
    _starfishLabel.setColor(Color.CYAN);
    super._uiTable.add(_starfishLabel).top();
    // _starfishLabel.setPosition(20, 520);
    // _uiStage.addActor(_starfishLabel);
  }

  private void initializeButton() {
    ButtonStyle buttonStyle = new ButtonStyle();

    Texture buttonTexture = new Texture(Gdx.files.internal("assets/undo.png"));
    TextureRegion buttonRegion = new TextureRegion(buttonTexture);
    buttonStyle.up = new TextureRegionDrawable(buttonRegion);

    Button restartButton = new Button(buttonStyle);
    restartButton.setColor(Color.CYAN);
    super._uiTable.add(restartButton).top();
    // restartButton.setPosition(720, 520);
    // _uiStage.addActor(restartButton);

    restartButton.addListener((Event e) -> {
      if (BaseGame.IsTouchDownInputEvent(e)) {
        StarfishGame.setActiveScreen(new LevelScreen());
        return false;
      }

      return false;
    });
  }

  private void initializeDialogBox() {
    Sign sign1 = new Sign(20, 400, _mainStage);
    sign1.setText("West Starfish Bay");

    Sign sign2 = new Sign(500, 300, _mainStage);
    sign2.setText("East Starfish Bay");

    _dialogBox = new DialogBox(0, 0, _uiStage);
    _dialogBox.setBackgroundColor(Color.TAN);
    _dialogBox.setFontColor(Color.BROWN);
    _dialogBox.setDialogSize(600, 100);
    _dialogBox.setFontScale(0.80f);
    _dialogBox.alignCenter();
    _dialogBox.setVisible(false);

    super._uiTable.row();
    super._uiTable.add(_dialogBox).colspan(3);
  }

  private void handleSigns() {
    for (BaseActor signActor : BaseActor.getList(_mainStage, "com.kinsoftwares.libgdx.Actors.Sign")) {
      Sign sign = (Sign) signActor;

      _turtle.preventOverlap(sign);

      boolean nearby = _turtle.isWithinDistance(4, sign);
      if (nearby && !sign.isViewing()) {
        _dialogBox.setText(sign.getText());
        _dialogBox.setVisible(true);
        sign.setViewing(true);
      }

      if (sign.isViewing() && !nearby) {
        _dialogBox.setText(" ");
        _dialogBox.setVisible(false);
        sign.setViewing(false);
      }
    }
  }
}