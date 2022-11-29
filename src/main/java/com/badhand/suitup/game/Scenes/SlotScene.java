package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

import java.util.*;

import processing.core.*;



public class SlotScene implements Scene {
    private static WindowManager wm = WindowManager.getInstance();
    private static AssetManager am = AssetManager.getInstance();

    private ImageElement slotMachine;
    private Glow glow;
    private ImageElement heart;
    private ImageElement chip;

    private PImage[] slotImages = new PImage[2];
    private ImageElement[] slotResults = new ImageElement[3];
    private Glow[] slotGlow = new Glow[3];

    private static Random rand = new Random();
    private int timerAmt = 100;
    private int timer = 100;
    private int slot = 0;

    private float blur = 0;

    private TextButton collect;





    public void initialize(){
        wm.clear();
        wm.setBackground(new Color(0, 0, 0));

        glow = new Glow(wm.getWidth()/2, wm.getHeight()/2 + 100, 1000, 500, 50, new Color(255,255,255));
        wm.put(glow);

        slotMachine =  new ImageElement(wm.getWidth()/2, wm.getHeight()/3, 400, 600, "slotmachine_big.png");
        wm.put(slotMachine);

        slotImages[0] = am.getImage("heart.png");
        slotImages[1] = am.getImage("chipBlueWhite.png");

        for(int i = 0; i < slotResults.length; i++){
            slotResults[i] = new ImageElement(-500, -500, 300, 300, slotImages[rand.nextInt(slotImages.length)]);
            slotResults[i].setPos(-500, -500);
            slotGlow[i] = new Glow(-500, -500, 800, 800, 50, new Color(170, 180, 20));
            wm.put(slotGlow[i]);
            wm.put(slotResults[i]);
        }

        collect = new TextButton("Collect", 64, wm.getWidth()/2, wm.getHeight() - 200, new Event(Events.SCENE_CHANGE, GameState.MAP_SCENE));
        wm.put(collect);
        collect.setVisibility(false);
        



        
    }

    public void update(){
        if(timer > 0 && slot < slotResults.length){
            timer--;
            if(slot == 1 && blur <= 1){
                slotMachine.getTexture().filter(PConstants.BLUR, blur += 0.1f);
            }
        }else if(slot < slotResults.length){
            timer = timerAmt;
            slotGlow[slot].setPos(wm.getWidth()/2 - 600 + (600*slot), wm.getHeight()/2);
            slotResults[slot].setPos(wm.getWidth()/2 - 600 + (600*slot), wm.getHeight()/2);
            slot++;
            if(slot == 3){
                collect.setVisibility(true);
            }
        }



    }

    public void handle(Event e){

    }
    
}
