package com.spacerocks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.spacerocks.Screens.LevelScreen;

public class App {
    public static void main(String[] args) {
        Game spacerocks = new SpaceGame();
        new LwjglApplication(spacerocks, "Space Rocks", LevelScreen.SPACE_WIDTH, LevelScreen.SPACE_HEIGHT);
    }
}
