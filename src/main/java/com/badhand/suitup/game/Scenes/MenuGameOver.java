package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.events.Event;
import com.badhand.suitup.events.Events;
import com.badhand.suitup.game.*;

public class MenuGameOver implements Scene {

    WindowManager wm = WindowManager.getInstance();
    AssetManager am = AssetManager.getInstance();
    
    public void initialize() {
        wm.clear();
        am.stopSound(0);
        am.loopSound("test_1.mp3", 0);
        wm.setBackground(new Color(50,50,50));

        TextElement gameOver = new TextElement("Game Over",256,1920/2,250);
        wm.put(gameOver);

        TextButton restart = new TextButton("Restart",128,1920/2,1080/2,new Event(Events.SCENE_CHANGE, GameState.MENU_LEVEL_SELECT));
        wm.put(restart);
    }

    public void update() {

    }

    public void handle(Event e) {

    }
}
