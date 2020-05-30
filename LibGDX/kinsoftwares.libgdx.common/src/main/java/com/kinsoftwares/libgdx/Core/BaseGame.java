package com.kinsoftwares.libgdx.Core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.kinsoftwares.libgdx.Utils.FreeTypeFontParameterBuilder;

public abstract class BaseGame extends Game {
  public static LabelStyle LabelStyle;
  public static TextButtonStyle TextButtonStyle;
  private static BaseGame _game;

  public BaseGame() {
    super();
    _game = this;
  }

  public static void setActiveScreen(BaseScreen screen) {
    _game.setScreen(screen);
  }

  @Override
  public void create() {
    // prepare for multiple classes/stages to receive discrete input
    InputMultiplexer processor = new InputMultiplexer();
    Gdx.input.setInputProcessor(processor);

    LabelStyle = new LabelStyle();
    LabelStyle.font = new BitmapFont();

    FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("assets/OpenSans.ttf"));
    FreeTypeFontParameter fontParameters = new FreeTypeFontParameterBuilder().withSize(48).withColor(Color.WHITE)
        .withBorderWidth(2).withBorderColor(Color.BLACK).withBorderStraight().withMinFilter(TextureFilter.Linear)
        .withMagFilter(TextureFilter.Linear).build();

    BitmapFont customFont = fontGenerator.generateFont(fontParameters);
    LabelStyle.font = customFont;

    initializeTextButton(customFont);

  }

  public static boolean IsTouchDownInputEvent(Event e) {
    return (e instanceof InputEvent) && ((InputEvent) e).getType().equals(Type.touchDown);
  }

  private void initializeTextButton(BitmapFont font) {
    TextButtonStyle = new TextButtonStyle();
    Texture buttonTexture = new Texture(Gdx.files.internal("assets/button.png"));
    NinePatch buttonPatch = new NinePatch(buttonTexture, 24, 24, 24, 24);
    TextButtonStyle.up = new NinePatchDrawable(buttonPatch);
    TextButtonStyle.font = font;
    TextButtonStyle.fontColor = Color.GRAY;
  }
}