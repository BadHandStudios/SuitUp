package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;

import processing.core.PGraphics;

import com.badhand.suitup.events.*;

import java.util.Random;

import com.badhand.suitup.assets.*;
import com.badhand.suitup.entities.Player;

public class ShopScene implements Scene {
    private static WindowManager wm = WindowManager.getInstance();
    private static AssetManager am = AssetManager.getInstance();
    private static EventManager em = EventManager.getInstance();
    private static Player p = Player.getInstance();

    private static int width = 1920;
    private static int height = 1080;
    private static Random rand = new Random();
    private int timer = 60;
    private Boolean screenClear = true;
    

    private CaptionedImage playerHealth;
    private CaptionedImage playerCoins;
    private CaptionedImage gildedCards;
    private CaptionedImage maxHealthCost;
    private CaptionedImage healCost;
    private CaptionedImage randGildCost;

    private GraphicsWrapper cardWrapper;

    private ImageElement maxHealth;
    private ImageElement heal;
    private ImageElement randomGilded;

    private TextElement rewardText;
    private TextElement logo;

    private TextButton back;
    private TextButton buyMaxHealth;
    private TextButton buyHeal;
    private TextButton buyRandGild;

    @Override
    public void initialize() {
        wm.clear();
        wm.setBackground(new Color(116,173,229));

        logo = new TextElement("Shop",128,width/2,height/8);
        wm.put(logo);

        maxHealth = new ImageElement(-500, -500, 300, 300, "heart.png");
        maxHealth.setPos(wm.getWidth()/2 - 600 + (600*0), wm.getHeight()/2);
        wm.put(maxHealth);

        maxHealthCost = new CaptionedImage(am.getImage("chip_blue.png"), ""+ 500, wm.getWidth()/2 - 650 + (600*0), maxHealth.getHeight(), 64);
        wm.registerDiffered(maxHealthCost, 2);
        wm.put(maxHealthCost);

        heal = new ImageElement(-500, -500, 300, 300, "heart.png");
        heal.setPos(wm.getWidth()/2 - 600 + (600*1), wm.getHeight()/2);
        wm.put(heal);

        healCost = new CaptionedImage(am.getImage("chip_blue.png"), ""+ 500, wm.getWidth()/2 - 650 + (600*1), maxHealth.getHeight(), 64);
        wm.registerDiffered(healCost, 2);
        wm.put(healCost);

        randomGilded = new ImageElement(-500, -500, 300, 300, "CardBack2.png");
        randomGilded.setPos(wm.getWidth()/2 - 600 + (600*2), wm.getHeight()/2);
        wm.put(randomGilded);

        randGildCost = new CaptionedImage(am.getImage("chip_blue.png"), ""+ 750, wm.getWidth()/2 - 650 + (600*2), maxHealth.getHeight(), 64);
        wm.registerDiffered(randGildCost, 2);
        wm.put(randGildCost);

        back = new TextButton("Leave", 64, 150, 100, new Event(Events.SCENE_CHANGE, GameState.MAP_SCENE));
        wm.put(back);

        buyMaxHealth = new TextButton("Max +1", 64, wm.getWidth()/2 - 600 + (600*0), wm.getHeight() * 3 / 4, new Event(Events.SCENE_EVENT, 0));
        wm.put(buyMaxHealth);
        buyHeal = new TextButton("Heal Max", 64, wm.getWidth()/2 - 600 + (600*1), wm.getHeight() * 3 / 4, new Event(Events.SCENE_EVENT, 1));
        wm.put(buyHeal);
        buyRandGild = new TextButton("Random Gild", 64, wm.getWidth()/2 - 600 + (600*2), wm.getHeight() * 3 / 4, new Event(Events.SCENE_EVENT, 2));
        wm.put(buyRandGild);

        playerHealth = new CaptionedImage(am.getImage("heart.png"), ""+ p.getHealth() + "/" + p.getMaxHealth(), 1600, 50, 64);
        wm.put(playerHealth);
        playerCoins = new CaptionedImage(am.getImage("chip_blue.png"), ""+ p.getChips(), 1600, 125, 64);
        wm.put(playerCoins);
        gildedCards = new CaptionedImage(am.getImage("CardBack3.png"), ""+p.getDeck().numGilded(), 1600, 200, 64);
        wm.put(gildedCards);

        
    }

    @Override
    public void update() {
        playerHealth.setCaption(""+ p.getHealth() + "/" + p.getMaxHealth());
        playerCoins.setCaption("" + p.getChips());
        gildedCards.setCaption(""+p.getDeck().numGilded());
        timer--;
        if(timer < 0) {
            if(cardWrapper != null) {
                wm.remove(cardWrapper);
                wm.registerDiffered(rewardText, 0);
                wm.remove(rewardText);
                for(int i = 0; i < 25; i++);
                screenClear = true;
            }
        }
    }

    @Override
    public void handle(Event e) {
        if(!screenClear) return;
        switch(e.getType()) {
            case SCENE_EVENT:
            rewardText = null;
                switch((int) e.getData()) {
                    case 0: //Max health increase by 1
                    if (p.getChips() >= 500) {
                        p.removeChips(500);
                        p.addMaxHealth(1);
                    }
                    break;
                    case 1: //Fill to max health
                    if(p.getHealth() != p.getMaxHealth() && p.getChips() >= 500) {
                        p.removeChips(500);
                        p.setHealth(p.getMaxHealth());
                    }
                    break;
                    case 2: //Gild rand card
                    if(p.getChips() >= 700 && screenClear == true) {
                        p.removeChips(750);
                        String randomCardString = "2;3;4;5;6;7;8;9;10;J;Q;K;A".split(";")[rand.nextInt(13)] + "of" + "Clubs;Dmnds;Hearts;Spades".split(";")[rand.nextInt(4)];
                        Card randomCard = p.getDeck().getCard(randomCardString);
                        randomCard.gild();
                        PGraphics cardImage = wm.newGraphic(300, 300);
                        cardImage.beginDraw();
                        cardImage.image(randomCard.getTexture(), 0, 0, 300, 300);
                        cardImage.image(randomCard.getGildedTexture(), 0, 0, 300, 300);
                        cardImage.endDraw();
                        cardWrapper = new GraphicsWrapper(cardImage, 0, 0);
                        cardWrapper.setPos(wm.getWidth()/2 - 600 + (600*2), wm.getHeight()/2);
                        
                        String text;
                        Effect j = randomCard.getEffect();
                        switch(j.getEffect()){
                            case BUST_PROOF:
                                text = "Bust Proof!";
                                break;
                            case DAMAGE_MODIFIER:
                                text = "Damage +" + (int)(j.getValue() * 100) + "%";
                                break;
                            case HEAL:
                                text = "Heal +" + (int)(j.getValue()) + "!";
                                break;
                            case INSTANT_DAMAGE:
                                text = "Insta-damage +" + (int)(j.getValue());
                                break;
                            case DEFENSE_BONUS:
                                text = "Defense +" + (int)(j.getValue() * 100) + "%";
                                break;
                            default:
                                text = "ERROR!";
                                break;

                        }

                        rewardText = new TextElement(text, 64,wm.getWidth()/2 - 600 + (600*2), buyRandGild.getHeight() + 610);
                        screenClear = false;
                        wm.put(cardWrapper);
                        wm.put(rewardText);
                        wm.registerDiffered(rewardText, 2);
                        timer = 120;
                    }
                    break;
                }
        }
    }
    
}
