package com.badhand.suitup.game;
import com.badhand.suitup.game.Scenes.*;

public class GameManager {

    private state scene = state.MENU_MAIN;
    
    private static GameManager instance = null;

    private Scene currentScene;

    private GameManager(){};

    public static GameManager getInstance() {
        if(instance == null) instance = new GameManager();
        return instance;
    }

    public void update() {
        if(currentScene == null) return;
        currentScene.update();
    }

    public void changeScene(state state) {
        scene = state;
        switch(scene){
            case MENU_MAIN:
                currentScene = new MenuMain();
        }
        currentScene.initialize();
    }

    public enum state {
        MENU_MAIN,
        MENU_SETTINGS,
        MENU_LEVEL_SELECT,
    }
}

