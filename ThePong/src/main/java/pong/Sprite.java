package pong;

public abstract class Sprite implements Drawable, Updateable {

  private int _xPos;
  private int _yPos;
  private int _width;
  private int _height;
  private int _speed;

  public Sprite(final int xInit, final int yInit, final int width, final int height) {
    setSpeed(0);
    setXPos(xInit);
    setYPos(yInit);
    setWidth(width);
    setHeight(height);
  }

  public void move(final int direction) {
    if (direction > 0) {
      this.setSpeed(2);
    } else if (direction < 0) {
      this.setSpeed(-2);
    } else {
      this.setSpeed(0);
    }
  }

  public void stop() {
    this.move(0);
  }

  public int getXPos() {
    return this._xPos;
  }

  public void setXPos(final int newXPos) {
    this._xPos = newXPos;
  }

  public int getYPos() {
    return this._yPos;
  }

  public void setYPos(final int newYPos) {
    this._yPos = newYPos;
  }

  public int getWidth() {
    return this._width;
  }

  public void setWidth(final int newWidth) {
    this._width = newWidth;
  }

  public int getHeight() {
    return this._height;
  }

  public void setHeight(final int newHeight) {
    this._height = newHeight;
  }

  protected int getSpeed() {
    return this._speed;
  }

  protected void setSpeed(final int newSpeed) {
    this._speed = newSpeed;
  }
}