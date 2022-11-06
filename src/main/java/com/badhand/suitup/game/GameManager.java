package com.badhand.suitup.game;

import com.badhand.suitup.events.*;
import com.badhand.suitup.game.Scenes.*;

public class GameManager {

    private GameState scene = GameState.SPLASH;
    
    private static GameManager instance = null;

    private Scene currentScene = null;

    private static EventManager em = EventManager.getInstance();

    private GameManager(){};

    public static GameManager getInstance() {
        if(instance == null) instance = new GameManager();
        
        return instance;
    }

    public void update() {
        if(currentScene == null) changeScene(scene);
        currentScene.update();

        Event e = em.pop();
        if(e == null) return;
        
        switch(e.getType()){
            case SCENE_CHANGE:
                changeScene((GameState)(e.getData()));
                break;
            default:
                currentScene.handle(e);
                break;
        }


    }

    public void changeScene(GameState state) {
        scene = state;
        switch(scene){
            case SPLASH:
                currentScene = new Splash();
                break;
            case MENU_MAIN:
                currentScene = new MenuMain();
                break;
        }
        currentScene.initialize();
    }

}

