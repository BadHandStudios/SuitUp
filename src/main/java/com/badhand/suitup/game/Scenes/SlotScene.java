package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;


public class SlotScene implements Scene {
    private static WindowManager wm = WindowManager.getInstance();
    private static AssetManager am = AssetManager.getInstance();

    ImageElement slotMachine;
    Glow glow;


    public void initialize(){
        wm.clear();
        wm.setBackground(new Color(0, 0, 0));

        glow = new Glow(wm.getWidth()/2, wm.getHeight() - 200, 1000, 500, 50, new Color(255,255,255));
        wm.put(glow);

        slotMachine =  new ImageElement(wm.getWidth()/2, wm.getHeight()/2, 400, 600, "slotmachine_big.png");
        wm.put(slotMachine);


        

    }

    public void update(){

    }

    public void handle(Event e){

    }
    
}
