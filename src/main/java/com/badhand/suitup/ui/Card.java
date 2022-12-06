package com.badhand.suitup.ui;


import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

import processing.core.*;

import java.util.*;

public class Card implements GUI{

    private Suit suit;
    private int value;

    private boolean faceUp = true;
    private static PGraphics cardBack;

    private int x,y; //position
    private static int width = 200;
    private static int height = 300;
    private int textSize = 50;
    private boolean visible = true;
    private Event e;
    private String name;
    private boolean gilded = false;
    private Effect effect;

    private Deck deck;

    private PGraphics texture;

    private static  WindowManager wm = WindowManager.getInstance();
    private static AssetManager am = AssetManager.getInstance();

    private static PImage gildedTexture;
    private static PGraphics gildedBack;

    private ImageElement gildedElement;

    private static Random rand = new Random();
    private static Hashtable<String, PGraphics> cardFaces = new Hashtable<String, PGraphics>();


    private TextElement toolTip;



    private LinkedList<GUI> enumeration;


    public static void preInitialize(){
        new Thread(new Runnable(){
            public void run(){
                preInit();
            }
        }).start();
    }

    private static void preInit(){
        for(String value : "2;3;4;5;6;7;8;9;10;A;J;Q;K".split(";")){
            for(String suit : "Clubs;Dmnds;Spades;Hearts".split(";")){
                String name = value + "of" + suit + ".png";
                PGraphics texture = WindowManager.getInstance().newGraphic(width, height);
                PImage image = AssetManager.getInstance().getImage(name);
                texture.beginDraw();
                texture.beginDraw();
                texture.image(image, 0, 0, width, height);
                texture.endDraw();
                cardFaces.put(name, texture);
            }
        }
    }


    public Card(Suit suit, int value, int x, int y, int width, int height){

        
        
        if(cardBack == null){
            cardBack = wm.newGraphic(width, height);
            cardBack.beginDraw();
            cardBack.image(am.getImage("CardBack1.png"), 0, 0, width, height);
            cardBack.endDraw();

            gildedBack = wm.newGraphic(width, height);
            gildedBack.beginDraw();
            gildedBack.image(am.getImage("CardBack3.png"), 0, 0, width, height);
            gildedBack.endDraw();
        }
        

        String textureFile;
        this.suit = suit;
        this.value = value;
        this.x = x;
        this.y = y;

        this.name = String.valueOf(value) + " of " + suit.toString(); //Change this?

        textureFile = this.toString() + ".png";
        PImage image;
        if(cardFaces.containsKey(textureFile)){
            texture = cardFaces.get(textureFile);
        } else {
            image = am.getImage(textureFile);
            this.texture = wm.newGraphic(width, height);
            this.texture.beginDraw();
            this.texture.image(image, 0, 0, width, height);
            this.texture.endDraw();

            cardFaces.put(textureFile, texture);
        }
        
        
        

        enumeration = new LinkedList<GUI>();
        enumeration.add(this);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;

        if(this.gilded){
            this.gildedElement.setPos(x, y);
            this.toolTip.setPos(x, y - 175);
        }
    }

    public boolean visible(){
        return visible;
    }

    public void setVisibility(boolean visible){
        this.visible = visible;
    }

    public PGraphics getTexture(){
        return faceUp ? texture : gilded ? gildedBack : cardBack;
    }

    public String getName(){
        return name;
    }

    public boolean click(int x, int y){
        if(!(x > this.x - (width/2) && x < this.x + width/2 && y > this.y - height/2 && y < this.y + height/2)) return false;
        return true;
    }

    public List<GUI> enumerate(){
        return enumeration;
    }

    public int getValue(){
        if(value == 1) return 11;
        if(value > 10) return 10;
        return value;
    }

    public String suitName(){
        switch(suit){
            case CLUBS:
                return "Clubs";
            case DIAMONDS:
                return "Dmnds";
            case HEARTS:
                return "Hearts";
            case SPADES:
                return "Spades";
        }
        return "ERROR";
    }

    public void flip(){
        faceUp = !faceUp;
    }

    public String toString(){
        if(value == 1) return "Aof" + suitName();
        if(value == 11) return "Jof" + suitName();
        if(value == 12) return "Qof" + suitName();
        if(value == 13) return "Kof" + suitName();
        return String.valueOf(value) + "of" + suitName();
    }

    public void gild(){
        if(!gilded){
            if(this.deck != null){
                deck.registerGilded(this);
            }
            gilded = true;
            if(gildedTexture == null){
                gildedTexture = am.getImage("gilded.png");
            }
            
            gildedElement = new ImageElement(x, y, width, height, gildedTexture);
            

            this.effect = new Effect(Effects.values()[rand.nextInt(Effects.values().length)]);
            this.enumeration.add(gildedElement);

            String text;
            switch(effect.getEffect()){
                case BUST_PROOF:
                    text = "Bust Proof!";
                    break;
                case DAMAGE_MODIFIER:
                    text = "Damage +" + (int)(effect.getValue() * 100) + "%";
                    break;
                case HEAL:
                    text = "Heal +" + (int)(effect.getValue()) + "!";
                    break;
                case INSTANT_DAMAGE:
                    text = "" + (int)(effect.getValue()) + " damage!";
                    break;
                default:
                    text = "ERROR!";
                    break;
            }
            toolTip = new TextElement(text, 32, x, y - 175);
            toolTip.setVisibility(false);
            this.enumeration.add(toolTip);
        }else{
            this.effect.upgrade();
        }
    }

    public void activate(){
        if(!gilded) return;
        am.playSound("clang.mp3", 3);
        if(effect.getEffect() == Effects.BUST_PROOF) this.flip();
        this.toolTip.setVisibility(true);
    }

    public void deactivate(){
        if(!gilded) return;
        this.toolTip.setVisibility(false);
    }

    public boolean isGilded(){
        return gilded;
    }

    public Effect getEffect(){
        return effect;
    }

    public boolean isFlipped(){
        return !this.faceUp;
    }

    public PImage getGildedTexture(){
        return gildedTexture;
    }

    public void registerDeck(Deck d){
        this.deck = d;
    }
}