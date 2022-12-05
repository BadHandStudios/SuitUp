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

        TextButton c = new TextButton("Options", 64, width/2, (int)(height/1.5)-16, null);
        wm.put(c);

        TextButton d = new TextButton("Back", 64, 0, 0, new Event(Events.SCENE_CHANGE, GameState.MENU_MAIN));
        wm.put(d);

        TextElement t = new TextElement("Developed by BadHandStudios", 64, 400, height - 64);
        wm.put(t);

        
    }

    @Override
    public void update() {
        
    }

    @Override
    public void handle(Event e) {
        
    }
    
}
