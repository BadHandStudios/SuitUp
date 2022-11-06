package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

import java.util.*;

public class MenuMain implements Scene{

    WindowManager wm = WindowManager.getInstance();
    AssetManager am = AssetManager.getInstance();

    int width = 1920;
    int height = 1080;

    private ImageElement[] chips = new ImageElement[4];
    private double[] chipVelocity = new double[4];
    private double chipAcceleration = 0.5;

    private Random rand = new Random();

    public MenuMain(){
        
    }

    public void initialize() {
        wm.clear();

        wm.setBackground(new Color(50,50,50));

        TextButton b = new TextButton("Play", 64, width/2, height/2, new Event(Events.SCENE_CHANGE, GameState.MENU_LEVEL_SELECT));
        wm.put(b);

        TextButton c = new TextButton("Options", 64, width/2, (int)(height/1.5)-16, null);
        wm.put(c);

        TextButton d = new TextButton("Quit", 64, width/2, (int) (height/1.25), null);
        wm.put(d);

        TextElement t = new TextElement("Developed by BadHandStudios", 64, 400, height - 64);
        wm.put(t);

        TextElement logo = new TextElement("SuitUp",128,width/2,height/4);
        wm.put(logo);

        for(int i = 0; i < chips.length; i++){
            int x = rand.nextInt(width);
            int y = rand.nextInt(height) - height;
            int v = rand.nextInt(3) + 1;
            chipVelocity[i] = v;
            if(i % 2 == 0){
                chips[i] = new ImageElement("chip"+i, x, y, 128, 128, am.getImage("chip_red.png"));
            }else{
                chips[i] = new ImageElement("chip"+i, x, y, 128, 128, am.getImage("chip_blue.png"));
            }
            wm.put(chips[i]);
        }
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
