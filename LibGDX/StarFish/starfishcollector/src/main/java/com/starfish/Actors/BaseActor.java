package com.starfish.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class BaseActor extends Actor {
  public static final String ASSETS_PATH = "starfishcollector/assets/";

  private Animation<TextureRegion> _animation;
  private float _elapsedTime;
  private boolean _animationPaused;

  public BaseActor(final float x, final float y, final Stage s) {
    super();
    setPosition(x, y);
    s.addActor(this);

    _animation = null;
    _elapsedTime = 0;
    _animationPaused = false;
  }

  public void setAnimation(final Animation<TextureRegion> animation) {
    _animation = animation;
    final TextureRegion textureRegion = _animation.getKeyFrame(0);
    final float width = textureRegion.getRegionWidth();
    final float height = textureRegion.getRegionHeight();
    super.setSize(width, height);
    super.setOrigin(width / 2, height / 2);
  }

  public void setAnimationPaused(final boolean pause) {
    _animationPaused = pause;
  }

  public Animation<TextureRegion> loadAnimationFromFiles(final String[] fileNames, final float frameDuration,
      final boolean loop) {
    final int fileCount = fileNames.length;
    final Array<TextureRegion> keyFrames = new Array<TextureRegion>();

    for (int n = 0; n < fileCount; n++) {
      final String fileName = fileNames[n];
      final Texture texture = new Texture(Gdx.files.internal(fileName));
      texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
      keyFrames.add(new TextureRegion(texture));
    }

    return processAnimationPlayMode(keyFrames, frameDuration, loop);
  }

  public Animation<TextureRegion> loadAnimationFromSheet(String fileName, int rows, int cols, float frameDuration,
      boolean loop) {
    Texture texture = new Texture(Gdx.files.internal(fileName), true);
    texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    int frameWidth = texture.getWidth() / cols;
    int frameHeight = texture.getHeight() / rows;

    TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);

    Array<TextureRegion> keyFrames = new Array<>();

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        keyFrames.add(temp[r][c]);
      }
    }

    return processAnimationPlayMode(keyFrames, frameDuration, loop);
  }

  public Animation<TextureRegion> loadTexture(String fileName) {
    String[] fileNames = new String[1];
    fileNames[0] = fileName;
    return loadAnimationFromFiles(fileNames, 1, true);
  }

  public boolean isAnimationFinished() {
    return _animation.isAnimationFinished(_elapsedTime);
  }

  @Override
  public void act(final float delta) {
    super.act(delta);

    if (!_animationPaused)
      _elapsedTime += delta;
  }

  @Override
  public void draw(final Batch batch, final float parentAlpha) {
    super.draw(batch, parentAlpha);

    // apply color tint effect
    final Color color = super.getColor();
    batch.setColor(color.r, color.g, color.b, color.a);

    if (_animation != null && super.isVisible()) {
      batch.draw(_animation.getKeyFrame(_elapsedTime), super.getX(), super.getY(), super.getOriginX(),
          super.getOriginY(), super.getWidth(), super.getHeight(), super.getScaleX(), super.getScaleY(),
          super.getRotation());
    }
  }

  private Animation<TextureRegion> processAnimationPlayMode(Array<TextureRegion> keyFrames, float frameDuration,
      boolean loop) {
    Animation<TextureRegion> animation = new Animation<>(frameDuration, keyFrames);
    if (loop)
      animation.setPlayMode(Animation.PlayMode.LOOP);
    else
      animation.setPlayMode(Animation.PlayMode.NORMAL);

    if (_animation == null)
      setAnimation(animation);

    return animation;
  }
}