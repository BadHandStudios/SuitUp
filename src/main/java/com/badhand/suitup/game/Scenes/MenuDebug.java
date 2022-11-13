package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

import java.util.*;

public class MenuDebug implements Scene{

    WindowManager wm = WindowManager.getInstance();
    AssetManager am = AssetManager.getInstance();
    
    public void initialize() {
        wm.clear();

        wm.setBackground(new Color(255,255,255));
    }

    public void update() {

    }

    public void handle(Event e) {

    }
}
