package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.events.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.ui.map.*;
import com.badhand.suitup.entities.*;
import com.badhand.suitup.assets.*;

import processing.core.*;
import java.util.Random;

public class MapScene implements Scene {
    private static WindowManager wm = WindowManager.getInstance();

    private static AssetManager am = AssetManager.getInstance();

    private static EventManager em = EventManager.getInstance();

    private static Map map;

    private static boolean doubleBack = false;
    private static boolean preInit = false;

    private int maxMoves = 10;
    private static int movesRemaining;

    private static int cloudOffsetY;
    private static boolean cloudOffsetYIncreasing;
    private int moveDelay = 0;
    private static boolean playMusic = true;


    private static ProgressBar movesRemainingBar;


    private static GraphicsWrapper[] cloudElements = new GraphicsWrapper[2];

    private Random rand = new Random();

    private static Player p = Player.getInstance();

    private static CaptionedImage playerHealth;
    private static CaptionedImage playerCoins;

    private static TextElement movesRemainingText;




    public void initialize() {
        if(playMusic) {
            am.stopSound(0);
            am.loopSound("swing.mp3", 0);
            playMusic = false;
        }
        
        if(!preInit){
            SlotScene.preInitialize();
            preInit = true;
        }


        wm.clear();
        wm.setBackground(new Color(80, 80, 80));

        if(map != null){
            wm.put(p);
            wm.put(map);
            map.replaceEntities();
            wm.put(cloudElements[0]);
            wm.put(cloudElements[1]);
            wm.put(movesRemainingBar);
            wm.put(movesRemainingText);

            playerHealth.setCaption(""+ p.getHealth() + "/" + p.getMaxHealth());
            playerCoins.setCaption("" + p.getChips());

            wm.put(playerHealth);
            wm.put(playerCoins);
            return;
        }



        movesRemaining = maxMoves;
        cloudOffsetY = 0;
        cloudOffsetYIncreasing = true;

        playerHealth = new CaptionedImage(am.getImage("heart.png"), ""+ p.getHealth() + "/" + p.getMaxHealth(), 200, 50, 64);
        playerCoins = new CaptionedImage(am.getImage("chip_blue.png"), ""+ p.getChips(), 200, 125, 64);
        wm.registerDiffered(playerHealth, 2);
        wm.registerDiffered(playerCoins, 2);
        wm.put(playerHealth);
        wm.put(playerCoins);

        map = new Map();
        p.move(map.getNode(1, 0));
        wm.put(p);
        wm.put(map);

        int cloudHeight = 1280;
        
        PGraphics clouds = wm.newGraphic(500, cloudHeight);
        clouds.beginDraw();
        clouds.noStroke();
        clouds.shapeMode(PConstants.CENTER);
        clouds.fill(255);
                
        PGraphics clouds2 = wm.newGraphic(500, cloudHeight);
        clouds2.beginDraw();
        clouds2.noStroke();
        clouds2.shapeMode(PConstants.CENTER);
        clouds2.fill(255);

        for(int i = 0; i < 40; i++) {
            clouds.circle(0, rand.nextInt(cloudHeight + 100) - 100, rand.nextInt(100) + 200);
            clouds2.circle(500, rand.nextInt(cloudHeight + 100) - 100, rand.nextInt(100) + 200);
        }

        clouds.endDraw();
        clouds2.endDraw();

        cloudElements[0] = new GraphicsWrapper(clouds, 250, wm.getHeight() / 2);
        cloudElements[1] = new GraphicsWrapper(clouds2, 1920 - 250, wm.getHeight() / 2);
        wm.registerDiffered(cloudElements[0], 2);
        wm.registerDiffered(cloudElements[1], 2);
        
        wm.put(cloudElements[0]);
        wm.put(cloudElements[1]);
        
        movesRemainingBar = new ProgressBar(wm.getWidth() / 2, 100, 500, 50, maxMoves, movesRemaining);
        movesRemainingBar.setColor(new Color(30, 150, 250));
        wm.registerDiffered(movesRemainingBar, 3);
        wm.put(movesRemainingBar);

        movesRemainingText = new TextElement("Moves until Boss Encounter", 32, wm.getWidth()/2, 150);
        wm.registerDiffered(movesRemainingText, 3);
        wm.put(movesRemainingText);
    }

    public void update() {
        map.update();
        if(moveDelay > 0) moveDelay--;

        Node n = p.getCurrentNode();
        if(map.isEdge(n) && n.connectingEdges() != 0) {
            map.pan(false);
            doubleBack = true;
        }

        if(cloudOffsetYIncreasing) {
            cloudOffsetY += 1;
            if(cloudOffsetY > 100) cloudOffsetYIncreasing = false;
        } else {
            cloudOffsetY -= 1;
            if(cloudOffsetY < -100) cloudOffsetYIncreasing = true;
        }

        cloudElements[0].setPos(cloudElements[0].getX(), wm.getHeight() / 2 + cloudOffsetY);
        cloudElements[1].setPos(cloudElements[1].getX(), wm.getHeight() / 2 - cloudOffsetY);
        
        
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
                if(moveDelay > 0) return;
                Node current = p.getCurrentNode();
                if(current == requested){
                    if(current.getEntity() != null){
                        if(current.getEntity() instanceof SlotMachine){
                            current.removeEntity();
                            em.push(new Event(Events.SCENE_CHANGE, GameState.SLOT_SCENE));
                        }
                    }else if(current.isDebug()){
                        em.push(new Event(Events.SCENE_CHANGE, GameState.SCENE_BATTLE));
                    }
                }

                if(map.connected(current, requested)) {
                    movesRemaining--;
                    moveDelay = 10;
                    movesRemainingBar.setValue(movesRemaining);
                    if(movesRemaining < maxMoves * 0.25) movesRemainingBar.setColor(new Color(255, 100, 100));
                    if(map.isFirst(requested) && doubleBack) {
                        doubleBack = false;
                        map.pan(true);
                    }
                    p.move(requested);
                    if(movesRemaining == 0) {
                        map.stopGeneration();
                        movesRemainingText.setText("Approaching Boss!");
                    }
                    
                }
                break;
            default:
                break;
        }
        
    }

    
}
