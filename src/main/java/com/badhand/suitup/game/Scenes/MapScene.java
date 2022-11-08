package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.events.Event;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.ui.map.*;

public class MapScene implements Scene {
    private static WindowManager wm = WindowManager.getInstance();


    public void initialize() {
        wm.clear();
        wm.setBackground(new Color(127, 127, 127));

        Map map = new Map();
        wm.put(map);

        
    }

    public void update() {
        // TODO Auto-generated method stub
        
    }

    public void handle(Event e) {
        // TODO Auto-generated method stub
        
    }

    
}
