import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends Paddle {
  public Enemy(int xInit, int yInit, int width, int height) {
    super(xInit, yInit, width, height);
  }

  @Override
  public void update() {
    int currPos = getXPos() + (Game.BALL.getXPos() - getXPos() - 6);
    if(currPos <= Game.WIDTH - getWidth() && currPos >= 0) {
      setXPos(currPos);
    }
    
    super.update();
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect(getXPos(), getYPos(), getWidth(), getHeight());
  }
}