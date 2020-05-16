package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

public class Ball extends Sprite {
  private int _dx;
  private int _dy;

  public Ball(int xInit, int yInit, int width, int height) {
    super(xInit, yInit, width, height);

    if (new Random().nextBoolean()) {
      setDx(1);
    } else {
      setDx(-1);
    }

    if (new Random().nextBoolean()) {
      setDy(1);
    } else {
      setDy(-1);
    }

    setSpeed(2);
  }

  @Override
  public void update() {
    int currXPos = getXPos() + (getDx() * getSpeed());
    if (currXPos + getWidth() >= Game.WIDTH || currXPos < 0) {
      setDx(getDx() * -1);
    }

    if (getYPos() >= Game.HEIGHT) {

    } else if (getYPos() <= 0) {
      // TODO: Implemetar
    }

    int currYPos = getYPos() + (getDy() * getSpeed());
    Rectangle bounds = new Rectangle(currXPos, currYPos, getWidth(), getHeight());

    Rectangle boundsPlayer = new Rectangle(Game.PLAYER.getXPos(), Game.PLAYER.getYPos(), Game.PLAYER.getWidth(),
        Game.PLAYER.getHeight());

    Rectangle boundsEnemy = new Rectangle(Game.ENEMY.getXPos(), Game.ENEMY.getYPos(), Game.ENEMY.getWidth(),
        Game.ENEMY.getHeight());

    if (bounds.intersects(boundsPlayer) || bounds.intersects(boundsEnemy)) {
      setDy(getDy() * -1);
    }

    setXPos(getXPos() + (getDx() * getSpeed()));
    setYPos(getYPos() + (getDy() * getSpeed()));
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.YELLOW);
    g.fillRect(getXPos(), getYPos(), getWidth(), getHeight());
  }

  public int getDx() {
    return this._dx;
  }

  public void setDx(int newDx) {
    this._dx = newDx;
  }

  public int getDy() {
    return this._dy;
  }

  public void setDy(int newDy) {
    this._dy = newDy;
  }
}