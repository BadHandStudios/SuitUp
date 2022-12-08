package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;
import com.badhand.suitup.entities.*;

import java.util.*;

import processing.core.*;



public class SlotScene implements Scene {
    private final int COIN_REWARD = 100;
    private final int COIN_REWARD_SPREAD = 25;
    //private final int CARD_REWARD = 1;
    //private final int HEART_REWARD = 1;

    private int coinReward = 0;
    private int coinMultiplier = 1;


    //private int numCoins = 0;
    //private int numHearts = 0;

    private static WindowManager wm = WindowManager.getInstance();
    private static AssetManager am = AssetManager.getInstance();

    private static ImageElement slotMachine;
    private static Glow glow;

    private static PImage[] slotImages = new PImage[3];
    private ImageElement[] slotResults = new ImageElement[3];
    private static Glow[] slotGlow = new Glow[3];

    private static Random rand = new Random();
    private int timerAmt = 100;
    private int timer = 100;
    private int slot = 0;


    private static boolean preInitializing = false;

    private float blur = 0;

    private static TextButton collect;

    private static Player p = Player.getInstance();

    private Card gildAward;



    public static void preInitialize(){
        Thread t = new Thread(new Runnable(){
            public void run(){
               preInit();
            }
        });

        t.start();
    }  

    private synchronized static void preInit(){
        preInitializing = true;
        slotImages[0] = am.getImage("heart.png");
        slotImages[1] = am.getImage("chip_blue.png");

        for(int i = 0; i < slotGlow.length; i++){
            slotGlow[i] = new Glow(-500, -500, 800, 800, 50, new Color(170, 180, 20));
        }
        glow = new Glow(wm.getWidth()/2, wm.getHeight()/2 + 100, 1000, 500, 50, new Color(255,255,255));
        slotMachine = new ImageElement(wm.getWidth()/2, wm.getHeight()/3, 400, 600, "slotmachine_big.png");
        collect = new TextButton("Collect", 64, wm.getWidth()/2, wm.getHeight() - 200, new Event(Events.SCENE_CHANGE, GameState.MAP_SCENE));
        System.out.println("Preinit done");
        preInitializing = false;
    }

    public synchronized void initialize(){
        while(preInitializing){
            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        wm.clear();
        wm.setBackground(new Color(0, 0, 0));



        if(slotImages[0] == null){
            slotImages[0] = am.getImage("heart.png");
            slotImages[1] = am.getImage("chipBlueWhite.png");
            for(int i = 0; i < slotResults.length; i++){
                slotGlow[i] = new Glow(-500, -500, 800, 800, 50, new Color(170, 180, 20));
            }
            glow = new Glow(wm.getWidth()/2, wm.getHeight()/2 + 100, 1000, 500, 50, new Color(255,255,255));
            slotMachine = new ImageElement(wm.getWidth()/2, wm.getHeight()/3, 400, 600, "slotmachine_big.png");
            collect = new TextButton("Collect", 64, wm.getWidth()/2, wm.getHeight() - 200, new Event(Events.SCENE_CHANGE, GameState.MAP_SCENE));
        }else{
            for(int i = 0; i < slotGlow.length; i++){
                slotGlow[i].setPos(-500, -500);
            }
        }

        




        wm.put(glow);
        wm.put(slotMachine);
        boolean alreadyDrawnGilded = false;
        //numCoins = 0;
        //numHearts = 0;
        for(int i = 0; i < slotResults.length; i++){
            int value = alreadyDrawnGilded ? rand.nextInt(2) : rand.nextInt(3);
            if(value == 2) alreadyDrawnGilded = true;
            //if(value == 1) numCoins++;
            //if(value == 0) numHearts++;
            String name = value == 0 ? "heart" : value == 1 ? "chip" : "card";
            if(name.equals("card")) gild();
            slotResults[i] = new ImageElement(name, -500, -500, 300, 300, slotImages[value]);
            slotResults[i].setPos(-500, -500);
            wm.put(slotGlow[i]);
            wm.put(slotResults[i]);
        }




        
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
            TextElement rewardText = null;
            if(slotResults[slot].getName().equals("chip")){
                if(coinReward == 0) coinReward = COIN_REWARD + rand.nextInt(COIN_REWARD_SPREAD * 2) - COIN_REWARD_SPREAD;
                int totalReward = coinReward * coinMultiplier;
                rewardText = new TextElement("+" + totalReward, 64, slotResults[slot].getX(), slotResults[slot].getY());
                wm.put(rewardText);
                rewardText.setPos(wm.getWidth()/2 - 600 + (600*slot), wm.getHeight()/2);
                p.addChips(totalReward);
                coinMultiplier++;
            }else if(slotResults[slot].getName().equals("heart")){
                p.addMaxHealth(1);
                rewardText = new TextElement("+1 Max", 64, slotResults[slot].getX(), slotResults[slot].getY());
                wm.put(rewardText);
                rewardText.setPos(wm.getWidth()/2 - 600 + (600*slot), wm.getHeight()/2);
            }else{
                String text;
                Effect e = gildAward.getEffect();
                switch(e.getEffect()){
                    case BUST_PROOF:
                        text = "Bust Proof!";
                        break;
                    case DAMAGE_MODIFIER:
                        text = "Damage +" + (int)(e.getValue() * 100) + "%";
                        break;
                    case HEAL:
                        text = "Heal +" + (int)(e.getValue()) + "!";
                        break;
                    case INSTANT_DAMAGE:
                        text = "Insta-damage +" + (int)(e.getValue());
                        break;
                    case DEFENSE_BONUS:
                        text = "Defense +" + (int)(e.getValue() * 100) + "%";
                        break;
                    default:
                        text = "ERROR!";
                        break;

                }

                rewardText = new TextElement(text, 64, slotResults[slot].getX(), slotResults[slot].getY() + 150);
                rewardText.setPos(wm.getWidth()/2 - 600 + (600*slot), wm.getHeight()/2 + 225);
                wm.put(rewardText);
            }

            timer = timerAmt;
            am.playSound("collect_" + (slot+1) +".mp3", slot + 1);
            slotGlow[slot].setPos(wm.getWidth()/2 - 600 + (600*slot), wm.getHeight()/2);
            slotResults[slot].setPos(wm.getWidth()/2 - 600 + (600*slot), wm.getHeight()/2);
            // if(rewardText != null) rewardText.setPos(wm.getWidth()/2 - 600 + (600*slot), wm.getHeight()/2);
            slot++;
            if(slot == 3){
                collect.setVisibility(true);
                am.playSound("fanfare.mp3", 1);

            }
        }



    }

    public void handle(Event e){
        switch(e.getType()) {
            case SCENE_CHANGE:
                blur = 0;
                break;
            default:
            break;
        }
    }

    private void gild(){
        String randomCardString = "2;3;4;5;6;7;8;9;10;J;Q;K;A".split(";")[rand.nextInt(13)] + "of" + "Clubs;Dmnds;Hearts;Spades".split(";")[rand.nextInt(4)];
        Card randomCard = p.getDeck().getCard(randomCardString);
        gildAward = randomCard;
        randomCard.gild();
        PGraphics cardImage = wm.newGraphic(150, 300);
        cardImage.beginDraw();
        cardImage.image(randomCard.getTexture(), 0, 0, 150, 300);
        cardImage.image(randomCard.getGildedTexture(), 0, 0, 150, 300);
        cardImage.endDraw();
        slotImages[2] = cardImage.get();

    }
    
}
