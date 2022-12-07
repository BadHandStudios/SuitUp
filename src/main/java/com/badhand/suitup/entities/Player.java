package com.badhand.suitup.entities;

import com.badhand.suitup.ui.map.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.assets.*;


public class Player extends Entity {



    private Node currentNode;
    private Node prevNode;

    private static AssetManager am = AssetManager.getInstance();


    private static Player instance = null;
    private Player() {
        super(am.getImage("character.png"), -500, -500, 90, 100);
        this.setMaxHealth(20);
        this.setAttack(5);
        this.setDeck(new Deck());

        
        wm.registerDiffered(this);

    };
    public static Player getInstance() {
        if(instance == null) instance = new Player();
        return instance;
    }
    
    private int chips;

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

    public Node getCurrentNode() {
        return currentNode;
    }
    public Node getPreviousNode(){
        return prevNode;
    }


    public void move(Node node) {
        if(currentNode != null) currentNode.removePlayer();
        prevNode = currentNode;
        currentNode = node;
        node.setEntity(this);
    }

    public void reset(){
        this.setMaxHealth(20);
        this.setAttack(5);
        this.setDeck(new Deck());
        this.setChips(0);
    }

}
