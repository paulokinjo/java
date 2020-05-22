package com.starfish.Actors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class BaseActor extends Actor {
  public static final String ASSETS_PATH = "starfishcollector/assets/";

  private Animation<TextureRegion> _animation;
  private float _elapsedTime;
  private boolean _animationPaused;

  private final Vector2 VelocityVec;
  private final Vector2 AccelerationVec;
  private float _acceleration;

  private float _maxSpeed;
  private float _deceleration;

  private Polygon _boundaryPolygon;

  private static Rectangle _worldBounds;

  public BaseActor(final float x, final float y, final Stage s) {
    super();
    setPosition(x, y);
    s.addActor(this);

    _animation = null;
    _elapsedTime = 0;
    _animationPaused = false;

    VelocityVec = new Vector2(0, 0);

    AccelerationVec = new Vector2(0, 0);
    _acceleration = 0;

    _maxSpeed = 1000;
    _deceleration = 0;
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

  public void setAnimation(final Animation<TextureRegion> animation) {
    _animation = animation;
    final TextureRegion textureRegion = _animation.getKeyFrame(0);
    final float width = textureRegion.getRegionWidth();
    final float height = textureRegion.getRegionHeight();
    super.setSize(width, height);
    super.setOrigin(width / 2, height / 2);

    if (_boundaryPolygon == null)
      setBoundaryRectangle();
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

  public Animation<TextureRegion> loadAnimationFromSheet(final String fileName, final int rows, final int cols,
      final float frameDuration, final boolean loop) {
    final Texture texture = new Texture(Gdx.files.internal(fileName), true);
    texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    final int frameWidth = texture.getWidth() / cols;
    final int frameHeight = texture.getHeight() / rows;

    final TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);

    final Array<TextureRegion> keyFrames = new Array<>();

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        keyFrames.add(temp[r][c]);
      }
    }

    return processAnimationPlayMode(keyFrames, frameDuration, loop);
  }

  public Animation<TextureRegion> loadTexture(final String fileName) {
    final String[] fileNames = new String[1];
    fileNames[0] = fileName;
    return loadAnimationFromFiles(fileNames, 1, true);
  }

  public boolean isAnimationFinished() {
    return _animation.isAnimationFinished(_elapsedTime);
  }

  public void setSpeed(final float speed) {
    // if length is zero, then assume motion angle is zero degrees
    if (VelocityVec.len() == 0) {
      VelocityVec.set(speed, 0);
    } else {
      VelocityVec.setLength(speed);
    }
  }

  public float getSpeed() {
    return VelocityVec.len();
  }

  public void setMotionAngle(final float angle) {
    VelocityVec.setAngle(angle);
  }

  public float getMotionAngle() {
    return VelocityVec.angle();
  }

  public boolean isMoving() {
    return (getSpeed() > 0);
  }

  public void setAcceleration(final float acceleration) {
    _acceleration = acceleration;
  }

  public void accelerateAtAngle(final float degrees) {
    AccelerationVec.add(new Vector2(_acceleration, 0).setAngle(degrees));
  }

  public void accelerateForward() {
    accelerateAtAngle(super.getRotation());
  }

  public void setMaxSpeed(final float maxSpeed) {
    _maxSpeed = maxSpeed;
  }

  public void setDeceleration(final float deceleration) {
    _deceleration = deceleration;
  }

  public void applyPhysics(final float delta) {
    // apply acceleration
    VelocityVec.add(AccelerationVec.x * delta, AccelerationVec.y * delta);

    float speed = getSpeed();

    // decrease speed (decelerate) when not accelerating
    if (AccelerationVec.len() == 0) {
      speed -= _deceleration * delta;
    }

    // Keep speed within set bounds
    speed = MathUtils.clamp(speed, 0, _maxSpeed);

    // update velocity
    setSpeed(speed);

    // apply velocity
    super.moveBy(VelocityVec.x * delta, VelocityVec.y * delta);

    // reset acceleration
    AccelerationVec.set(0, 0);
  }

  public void setBoundaryRectangle() {
    final float width = super.getWidth();
    final float height = super.getHeight();
    final float[] vertices = { 0, 0, width, 0, width, height, 0, height };
    _boundaryPolygon = new Polygon(vertices);
  }

  public void setBoundaryPolygon(final int numSides) {
    final float width = getWidth();
    final float height = getHeight();

    final float[] vertices = new float[2 * numSides];
    for (int i = 0; i < numSides; i++) {
      final float angle = i * 6.28f / numSides;

      // x-coordinate
      vertices[2 * i] = (width / 2 * MathUtils.cos(angle)) + width / 2;

      // y-coordinate
      vertices[(2 * i) + 1] = (height / 2 * MathUtils.sin(angle)) + height / 2;
    }

    _boundaryPolygon = new Polygon(vertices);
  }

  public Polygon getBoundaryPolygon() {
    _boundaryPolygon.setPosition(super.getX(), super.getY());
    _boundaryPolygon.setOrigin(super.getOriginX(), super.getOriginY());
    _boundaryPolygon.setRotation(super.getRotation());
    _boundaryPolygon.setScale(super.getScaleX(), super.getScaleY());
    return _boundaryPolygon;
  }

  public boolean overlaps(final BaseActor other) {
    final Polygon thisPoly = this.getBoundaryPolygon();
    final Polygon otherPoly = other.getBoundaryPolygon();

    // initial test to improve performance
    if (!thisPoly.getBoundingRectangle().overlaps(otherPoly.getBoundingRectangle()))
      return false;

    return Intersector.overlapConvexPolygons(thisPoly, otherPoly);
  }

  public void centerAtPosition(final float x, final float y) {
    super.setPosition(x - super.getWidth() / 2, y - super.getHeight() / 2);
  }

  public void centerAtActor(final BaseActor other) {
    centerAtPosition(other.getX() + other.getWidth() / 2, other.getY() + other.getHeight() / 2);
  }

  public void setOpacity(final float opacity) {
    super.getColor().a = opacity;
  }

  public Vector2 preventOverlap(final BaseActor other) {
    final Polygon thisPoly = this.getBoundaryPolygon();
    final Polygon otherPoly = other.getBoundaryPolygon();

    // initial test to improve performance
    if (!thisPoly.getBoundingRectangle().overlaps(otherPoly.getBoundingRectangle()))
      return null;

    final MinimumTranslationVector mtv = new MinimumTranslationVector();
    final boolean polygonOverlap = Intersector.overlapConvexPolygons(thisPoly, otherPoly, mtv);

    if (!polygonOverlap)
      return null;

    super.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
    return mtv.normal;
  }

  public static ArrayList<BaseActor> getList(final Stage stage, final String className) {
    final ArrayList<BaseActor> list = new ArrayList<>();

    Class theClass = null;
    try {
      theClass = Class.forName(className);
    } catch (final Exception e) {
      e.printStackTrace();
    }

    for (final Actor actor : stage.getActors()) {
      if (theClass.isInstance(actor))
        list.add((BaseActor) actor);
    }

    return list;
  }

  public static int count(final Stage stage, final String className) {
    return getList(stage, className).size();
  }

  public static void setWorldBounds(final float width, final float height) {
    _worldBounds = new Rectangle(0, 0, width, height);
  }

  public static void setWorldBounds(final BaseActor baseActor) {
    setWorldBounds(baseActor.getWidth(), baseActor.getHeight());
  }

  public void boundToWorld() {
    if (super.getX() < 0) {
      super.setX(0);
    }

    if (super.getX() + super.getWidth() > _worldBounds.width) {
      super.setX(_worldBounds.width - super.getWidth());
    }

    if (super.getY() < 0) {
      super.setY(0);
    }

    if (super.getY() + super.getHeight() > _worldBounds.height) {
      super.setY(_worldBounds.height - super.getHeight());
    }
  }

  public void alignCamera() {
    final Camera cam = super.getStage().getCamera();

    // center camera on actor
    cam.position.set(super.getX() + super.getOriginX(), super.getY() + super.getOriginY(), 0);

    // bound camera to layout
    cam.position.x = MathUtils.clamp(cam.position.x, cam.viewportWidth / 2, _worldBounds.width - cam.viewportWidth / 2);
    cam.position.y = MathUtils.clamp(cam.position.y, cam.viewportHeight / 2,
        _worldBounds.height - cam.viewportHeight / 2);

    cam.update();
  }

  private Animation<TextureRegion> processAnimationPlayMode(final Array<TextureRegion> keyFrames,
      final float frameDuration, final boolean loop) {
    final Animation<TextureRegion> animation = new Animation<>(frameDuration, keyFrames);
    if (loop)
      animation.setPlayMode(Animation.PlayMode.LOOP);
    else
      animation.setPlayMode(Animation.PlayMode.NORMAL);

    if (_animation == null)
      setAnimation(animation);

    return animation;
  }
}