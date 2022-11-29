package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.ui.*;

import processing.core.PImage;

import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

import java.util.*;

public class MenuMain implements Scene{

    WindowManager wm = WindowManager.getInstance();
    AssetManager am = AssetManager.getInstance();

    int width = 1920;
    int height = 1080;

    private ImageElement[] chips = new ImageElement[10];
    private double[] chipVelocity = new double[chips.length];
    private double chipAcceleration = 0.5;

    private Random rand = new Random();

    public MenuMain(){
        
    }

    public void initialize() {
        wm.clear();

        //am.loopSound("catchit.mp3", 0);
        am.playSound("short_test_1.mp3", 0);
        wm.setBackground(new Color(50,50,50));

        PImage[] chipImages = {
            am.getImage("chip_red.png"),
            am.getImage("chip_blue.png"),
            am.getImage("chipBlackWhite.png"),
            am.getImage("chipBlueWhite.png"),
            am.getImage("chipRedWhite.png"),
        };

        for(int i = 0; i < chips.length; i++){
            int x = rand.nextInt(width);
            int y = rand.nextInt(height) - height;
            int v = rand.nextInt(3) + 1;
            int size = rand.nextInt(100) + 70;
            chipVelocity[i] = v;
            chips[i] = new ImageElement(x, y, size, size, chipImages[rand.nextInt(chipImages.length)]);
            wm.put(chips[i]);
        }

        TextElement logo = new TextElement("SuitUp",128,width/2,height/4);
        wm.put(logo);

        TextButton b = new TextButton("Play", 64, width/2, height/2, new Event(Events.SCENE_CHANGE, GameState.MENU_LEVEL_SELECT));
        wm.put(b);

        TextButton c = new TextButton("Options", 64, width/2, (int)(height/1.5)-16, null);
        wm.put(c);

        TextButton d = new TextButton("Quit", 64, width/2, (int) (height/1.25), new Event(Events.QUIT_GAME, null));
        wm.put(d);

        TextElement t = new TextElement("Developed by BadHandStudios", 64, 400, height - 64);
        wm.put(t);




    }

    public void update() {
        for(int i = 0; i < chips.length; i++){
            int x = chips[i].getX();
            int y = chips[i].getY();
            if(y > height){
                y = rand.nextInt(height) - height;
                x = rand.nextInt(width);
                chipVelocity[i] = rand.nextInt(5) + 1;
            }else{
                y += chipVelocity[i];
                chipVelocity[i] += chipAcceleration;
            }
            chips[i].setPos(x, y);
        }

    }

    public void handle(Event e) {

    }
}
