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

    private boolean updateLock = false;

    public static GameManager getInstance() {
        if(instance == null) instance = new GameManager();
        
        return instance;
    }

    public synchronized void update() {
        if(updateLock) return;
        updateLock = true;

        if(currentScene == null) changeScene(scene);
        currentScene.update();
        while(!em.isEmpty()) {
            Event e = em.pop();
            if(e == null) return;
            
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
    }

    public synchronized void unlock(){
        updateLock = false;
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
                break;
            case DEBUG:
                currentScene = new Debug();
                break;
            case MAP_SCENE:
                currentScene = new MapScene();
                break;
            case SLOT_SCENE:
                currentScene = new SlotScene();
                break;
        }
        currentScene.initialize();
    }

    public Scene getCurrentScene(){
        return currentScene;
    }
}

