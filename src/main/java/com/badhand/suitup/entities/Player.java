package com.badhand.suitup.entities;

import java.util.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.ui.map.*;

import processing.core.PGraphics;

public class Player extends Entity {
    private int x, y;
    private int width, height;
    private boolean visible;
    private LinkedList<GUI> enumeration;

    ImageElement texture;

    Node currentNode;


    private static Player instance = null;
    private Player() {
        this.visible = true;

        this.enumeration = new LinkedList<GUI>();
        
        texture = new ImageElement(0, 0, 100, 100, "character.png");
        
    
        enumeration.add(texture);

        wm.registerDiffered(texture);
    };
    public static Player getInstance() {
        if(instance == null) instance = new Player();
        return instance;
    }
    
    private int chips;
    private String[] items = {"","","","",""};

    public void setChips(int amount) {
        this.chips = amount;
    }
    public void addChips(int amount) {
        this.chips += amount;
    }
    public void removeChips(int amount) {
        this.chips -= amount;
    }
    public void clearChips(int amount) {
        this.chips = 0;
    }
    public int getChips() {
        return this.chips;
    }
    public void addItem(String item) {
        for (int i = 0; i < 5; i++) {
            if (items[i] == "") {
                items[i] = item;
            }
        }
    }
    public void removeItem(int index) {
        items[index] = "";
        for (int i = 0; i < 5; i++) {
            if (items[i] == "" && items[i+1] != null) {
                items[i] = items[i+1];
                items[i+1] = "";
            }
        }
    }

    public void clearItems() {
        items = new String[]{"","","","",""};
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
