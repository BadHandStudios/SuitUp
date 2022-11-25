package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.events.Event;
import com.badhand.suitup.events.Events;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.TextButton;
import com.badhand.suitup.ui.WindowManager;

public class Debug implements Scene {

    private static WindowManager wm = WindowManager.getInstance();

    private TextButton[] debugButtons = {
        new TextButton("Map", 128, 1920/2, 200, new Event(Events.SCENE_CHANGE, null)),

    };

    public Debug() {
        
    }

    public void initialize() {
        wm.clear();
        for(TextButton b : debugButtons){
            wm.put(b);
        }
        
    }

    public void update() {
        
    }

    public void handle(Event e) {
        
    }

    
}
