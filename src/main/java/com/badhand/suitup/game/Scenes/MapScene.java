package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.events.Event;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.ui.map.*;
import com.badhand.suitup.game.entities.*;
import com.badhand.suitup.assets.*;

import processing.core.*;
import java.util.Random;

public class MapScene implements Scene {
    private static WindowManager wm = WindowManager.getInstance();

    private static AssetManager am = AssetManager.getInstance();

    private Map map;

    private GraphicsWrapper[] cloudElements = new GraphicsWrapper[2];

    private Random rand = new Random();

    Player p = new Player();

    public void initialize() {
        wm.clear();
        wm.setBackground(new Color(127, 127, 127));

        map = new Map();
        p.move(map.getNode(1, 0));
        wm.put(p);
        wm.put(map);
        
        PGraphics clouds = wm.newGraphic(500, 1080);
        clouds.beginDraw();
        clouds.noStroke();
        clouds.shapeMode(PConstants.CENTER);
        clouds.fill(255);
                
        PGraphics clouds2 = wm.newGraphic(500, 1080);
        clouds2.beginDraw();
        clouds2.noStroke();
        clouds2.shapeMode(PConstants.CENTER);
        clouds2.fill(255);

        for(int i = 0; i < 35; i++) {
            clouds.circle(0, rand.nextInt(1200) - 100, rand.nextInt(100) + 200);
            clouds2.circle(500, rand.nextInt(1200) - 100, rand.nextInt(100) + 200);
        }

        clouds.endDraw();
        clouds2.endDraw();

        cloudElements[0] = new GraphicsWrapper(clouds, 250, wm.getHeight() / 2);
        cloudElements[1] = new GraphicsWrapper(clouds2, 1920 - 250, wm.getHeight() / 2);
        
        wm.put(cloudElements[0]);
        wm.put(cloudElements[1]);
        
        AnimatedImage casino_sign = new AnimatedImage(1920/4, 200, 300, 150, am.getImage("casino_1.png"), am.getImage("casino_2.png"));
        casino_sign.setSpeed(20);
        casino_sign.setRotation(20);
        wm.put(casino_sign);
        

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
            case SCENE_EVENT:
                Node requested = (Node)(e.getData());
                // Move character if possible
                Node current = p.getCurrentNode();
                if(map.connected(current, requested)) {
                    p.move(requested);
                }
                break;
            default:
                break;
        }
        
    }

    
}
