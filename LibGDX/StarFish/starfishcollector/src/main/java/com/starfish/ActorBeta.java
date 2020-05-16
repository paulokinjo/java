package com.starfish;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorBeta extends Actor {

  private TextureRegion _textureRegion;
  private Rectangle _rectangle;

  public ActorBeta() {
    super();
    _textureRegion = new TextureRegion();
    _rectangle = new Rectangle();
  }

  public void setTexture(Texture texture) {
    _textureRegion.setRegion(texture);

    int width = texture.getWidth();
    int height = texture.getHeight();

    setSize(width, height);
    _rectangle.setSize(width, height);
  }

  public Rectangle getRectangle() {
    _rectangle.setPosition(getX(), getY());
    return _rectangle;
  }

  public boolean overlaps(ActorBeta other) {
    return this.getRectangle().overlaps(other.getRectangle());
  }

  public void act(float dt) {
    super.act(dt);
  }

  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    Color c = getColor();

    batch.setColor(c.r, c.g, c.b, c.a);

    if (isVisible()) {
      batch.draw(_textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
          getScaleY(), getRotation());
    }
  }
}