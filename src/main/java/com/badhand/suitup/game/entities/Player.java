package com.badhand.suitup.game.entities;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.ui.map.*;

import java.util.*;

import processing.core.*;

import com.badhand.suitup.game.*;


public class Player implements Entity {
    private int x, y;
    private int width, height;
    private boolean visible;
    private LinkedList<GUI> enumeration;

    ImageElement texture;

    Node currentNode;

    Deck deck;
    
    private static WindowManager wm = WindowManager.getInstance();

    public Player() {
        this.visible = true;

        this.enumeration = new LinkedList<GUI>();
        
        texture = new ImageElement(0, 0, 100, 100, "character.png");
        
    
        enumeration.add(texture);

        wm.registerDiffered(texture);

        this.deck = new Deck();

        
    }   

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
        texture.setPos(x, y);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public boolean visible() {
        return visible;
    }
    public void setVisibility(boolean visible) {
        this.visible = visible;
        texture.setVisibility(visible);
    }

    public boolean click(int x, int y) {
        return false;
    }

    public PGraphics getTexture() {
        return texture.getTexture();
    }

    public Node getCurrentNode() {
        return currentNode;
    }
    public void move(Node node) {
        if(currentNode != null) currentNode.removeEntity();
        currentNode = node;
        node.setEntity(this);
    }


    public LinkedList<GUI> enumerate() {
        return enumeration;
    }
}
