package com.badhand.suitup.game.Scenes;

//import processing.core.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;

import processing.core.*;

public class MenuMain implements Scene{
    WindowManager wm = WindowManager.getInstance();
    int width = 1920;
    int height = 1080;


    public MenuMain(){
        
    }

    public void initialize() {
        wm.clear();

        wm.setBackground(new Color(50,50,50));

        TextButton b = new TextButton("Play", 64, width/2, height/2, null);
        wm.put(b);

        TextButton c = new TextButton("Options", 64, width/2, (int)(height/1.5)-16, null);
        wm.put(c);

        TextButton d = new TextButton("Quit", 64, width/2, (int) (height/1.25), null);
        wm.put(d);

        TextElement t = new TextElement("Developed by BadHandStudios", 64, 400, height - 64);
        wm.put(t);

        TextElement logo = new TextElement("SuitUp",128,width/2,height/4);
        wm.put(logo);
    }

    public void update() {

    }

    public void handle(Event e) {

    }
}
