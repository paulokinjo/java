package com.starfish;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class StarfishCollectorBeta extends GameBeta {
  private Turtle _turtle;
  private ActorBeta _starfish;
  private ActorBeta _ocean;
  private ActorBeta _winMessage;

  private boolean _isWin;

  public void initialize() {
    _ocean = new ActorBeta();
    _ocean.setTexture(new Texture(Gdx.files.internal("starfishcollector/assets/water.jpg")));
    mainStage.addActor(_ocean);

    _starfish = new ActorBeta();
    _starfish.setTexture(new Texture(Gdx.files.internal("starfishcollector/assets/starfish.png")));
    _starfish.setPosition(380, 380);
    mainStage.addActor(_starfish);

    _turtle = new Turtle();
    _turtle.setTexture(new Texture(Gdx.files.internal("starfishcollector/assets/turtle-1.png")));
    _turtle.setPosition(20, 20);
    mainStage.addActor(_turtle);

    _winMessage = new ActorBeta();
    _winMessage.setTexture(new Texture(Gdx.files.internal("starfishcollector/assets/you-win.png")));
    _winMessage.setPosition(180, 180);
    _winMessage.setVisible(false);
    mainStage.addActor(_winMessage);

    _isWin = false;
  }

  public void update(float dt) {
    // check user input
    mainStage.act(1 / 60f);

    // check win condition: turtle must be overlapping starfish
    if (_turtle.overlaps(_starfish)) {
      _starfish.remove();
      _winMessage.setVisible(true);
    }
  }
}