package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Paddle {

  public Player(int xInit, int yInit, int width, int height) {
    super(xInit, yInit, width, height);
  }

  @Override
  public void update() {
    setXPos(getXPos() + getSpeed());
    super.update();
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(getXPos(), getYPos(), getWidth(), getHeight());
  }
}