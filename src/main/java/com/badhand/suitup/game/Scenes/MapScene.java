package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.events.Event;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.ui.map.*;

import processing.core.*;

public class MapScene implements Scene {
    private static WindowManager wm = WindowManager.getInstance();

    private Map map;

    public void initialize() {
        wm.clear();
        wm.setBackground(new Color(127, 127, 127));

        map = new Map();
        wm.put(map);

        
    }

    public void update() {
        // TODO Auto-generated method stub
        
    }

    public void handle(Event e) {
        switch(e.getType()){
            case KEY_PRESS:
                int key = (int)(e.getData());
                // Pan map with arrow keys
                if(key == PConstants.LEFT) map.pan(false);
                if(key == PConstants.RIGHT) map.pan(true);

                break;
            default:
                break;
        }
        
    }

    
}
