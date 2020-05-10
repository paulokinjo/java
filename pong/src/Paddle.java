public abstract class Paddle extends Sprite {
  public Paddle(int xInit, int yInit, int width, int height) {
    super(xInit, yInit, width, height);
  }

  @Override
  public void update() {
    if (getXPos() + getWidth() >= Game.WIDTH || getXPos() <= 0) {
      this.stop();
    }
  }
}