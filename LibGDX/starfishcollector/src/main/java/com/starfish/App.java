package com.starfish;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class App {
    public static void main(String[] args) {
        Game myGame = new StarfishGame();
        new LwjglApplication(myGame, "Starfish Collector", 800, 600);
    }
}
