package com.kinsoftwares.libgdx.Utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FreeTypeFontParameterBuilder {
  private int _size;
  private Color _color;
  private int _borderWidth;
  private Color _borderColor;
  private boolean _isBorderStraight;
  private TextureFilter _minFilter;
  private TextureFilter _magFilter;

  public FreeTypeFontParameterBuilder() {
    super();
    _size = 48;
    _color = Color.WHITE;
    _borderWidth = 2;
    _borderColor = Color.BLACK;
    _isBorderStraight = true;
    _minFilter = TextureFilter.Linear;
    _magFilter = TextureFilter.Linear;
  }

  public FreeTypeFontParameterBuilder withSize(int size) {
    _size = size;
    return this;
  }

  public FreeTypeFontParameterBuilder withColor(Color color) {
    _color = color;
    return this;
  }

  public FreeTypeFontParameterBuilder withBorderWidth(int borderWidth) {
    _borderWidth = borderWidth;
    return this;
  }

  public FreeTypeFontParameterBuilder withBorderColor(Color borderColor) {
    _borderColor = borderColor;
    return this;
  }

  public FreeTypeFontParameterBuilder withBorderStraight() {
    _isBorderStraight = true;
    return this;
  }

  public FreeTypeFontParameterBuilder withoutBorderStraight() {
    _isBorderStraight = false;
    return this;
  }

  public FreeTypeFontParameterBuilder withMinFilter(TextureFilter textureFilter) {
    _minFilter = textureFilter;
    return this;
  }

  public FreeTypeFontParameterBuilder withMagFilter(TextureFilter textureFilter) {
    _magFilter = textureFilter;
    return this;
  }

  public FreeTypeFontParameter build() {
    FreeTypeFontParameter fp = new FreeTypeFontParameter();
    fp.size = _size;
    fp.color = _color;
    fp.borderWidth = _borderWidth;
    fp.borderColor = _borderColor;
    fp.borderStraight = _isBorderStraight;
    fp.minFilter = _minFilter;
    fp.magFilter = _magFilter;

    return fp;
  }
}