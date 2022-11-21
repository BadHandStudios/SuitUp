package com.badhand.suitup.game;

import com.badhand.suitup.events.*;
import com.badhand.suitup.game.Scenes.*;
import com.badhand.suitup.ui.WindowManager;

import processing.core.PConstants;

public class GameManager {

    private GameState scene = GameState.SPLASH;
    
    private static GameManager instance = null;

    private Scene currentScene = null;

    private static EventManager em = EventManager.getInstance();

    private static WindowManager wm = WindowManager.getInstance();

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

        if(e.getType() == Events.KEY_PRESS && (int)(e.getData()) == PConstants.ENTER) {
            changeScene(GameState.DEBUG);
            return;
        }
        
        switch(e.getType()){
            case SCENE_CHANGE:
                changeScene((GameState)(e.getData()));
                break;
            case QUIT_GAME:
                wm.destroyWindow();
                System.exit(0);
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
            case MENU_LEVEL_SELECT:
                currentScene = new MenuLevelSelect();
                break;
            case SCENE_BATTLE:
                currentScene = new SceneBattle();
            case DEBUG:
                currentScene = new Debug();
                break;
        }
        currentScene.initialize();
    }

}

