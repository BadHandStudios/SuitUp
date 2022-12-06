package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

public class ShopScene implements Scene {
    WindowManager wm = WindowManager.getInstance();
    AssetManager am = AssetManager.getInstance();
    EventManager em = EventManager.getInstance();

    int width = 1920;
    int height = 1080;


    @Override
    public void initialize() {
        wm.clear();

        TextElement logo = new TextElement("Shop",128,width/2,height/4);
        wm.put(logo);

        TextButton b = new TextButton("Play", 64, width/2, height/2, new Event(Events.SCENE_CHANGE, GameState.MENU_LEVEL_SELECT));
        wm.put(b);

        TextButton d = new TextButton("Back", 64, 150, 100, new Event(Events.SCENE_CHANGE, GameState.MENU_MAIN));
        wm.put(d);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void handle(Event e) {
        
    }
    
}
