package com.badhand.suitup.ui;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;

import processing.core.*;

import java.util.*;

public class Card implements GUI{

    private Suit suit;
    private int value;

    private int x,y; //position
    private int width = 250;
    private int height = 350;
    private int textSize = 50;
    private boolean visible = true;
    private Event e;
    private String name;

    private PGraphics texture;

    private WindowManager wm = WindowManager.getInstance();
    private EventManager em = EventManager.getInstance();

    private LinkedList<GUI> enumeration;

    public Card(Suit suit, int value, int x, int y){
        this.suit = suit;
        this.value = value;
        this.x = x;
        this.y = y;
        //this.image = image;
        this.name = String.valueOf(value) + " of " + suit.toString(); //Change this?

        this.texture = wm.newGraphic(width, height);
        texture.beginDraw();
        texture.stroke(0);
        texture.fill(255);
        texture.rect(0,0, width -1, height -1, 5);
        texture.fill(0);
        texture.endDraw();

        enumeration = new LinkedList<GUI>();
        enumeration.add(this);

        //Card text:
        String text = String.valueOf(value);
        Color color = new Color(0,0,0);
        //Suit text:
        switch (suit){
            case CLUBS:
                text += " C";
                break;
            case DIAMONDS:
                text += " D";
                color = new Color(255,0,0);
                break;
            case HEARTS:
                text += " H";
                color = new Color(255,0,0);
                break;
            case SPADES:
                text += " S";
                break;
        }
        TextElement t = new TextElement(text, textSize, x, y);
        t.setColor(color);
        enumeration.add(t);
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
        return texture;
    }

    public String getName(){
        return name;
    }

    public boolean click(int x, int y){
        if(!(x > this.x - (width/2) && x < this.x + width/2 && y > this.y - height/2 && y < this.y + height/2)) return false;
        em.push(e);
        return true;
    }

    public List<GUI> enumerate(){
        return enumeration;
    }
}