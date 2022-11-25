package com.badhand.suitup.ui;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

import processing.core.*;

import java.util.*;

public class Card implements GUI{

    private Suit suit;
    private int value;

    private boolean faceUp = true;
    private PGraphics cardBack;

    private int x,y; //position
    private int width = 250;
    private int height = 350;
    private int textSize = 50;
    private boolean visible = true;
    private Event e;
    private String name;

    private PGraphics texture;

    private WindowManager wm = WindowManager.getInstance();
    private AssetManager am = AssetManager.getInstance();



    private LinkedList<GUI> enumeration;

    public Card(Suit suit, int value, int x, int y, int width, int height){
        
        cardBack = wm.newGraphic(width, height);
        cardBack.beginDraw();
        cardBack.image(am.getImage("CardBack1.png"), 0, 0, width, height);
        cardBack.endDraw();

        


        String textureFile;
        this.suit = suit;
        this.value = value;
        this.x = x;
        this.y = y;


        this.name = String.valueOf(value) + " of " + suit.toString(); //Change this?

        textureFile = this.toString() + ".png";
        PImage image = am.getImage(textureFile);
        this.texture = wm.newGraphic(width, height);
        this.texture.beginDraw();
        this.texture.image(image, 0, 0, width, height);
        this.texture.endDraw();
        
        

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
    }

    public boolean visible(){
        return visible;
    }

    public void setVisibility(boolean visible){
        this.visible = visible;
    }

    public PGraphics getTexture(){
        return faceUp ? texture : cardBack;
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
}