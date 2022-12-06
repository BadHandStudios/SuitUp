package com.badhand.suitup.entities;

import java.util.*;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;

import processing.core.*;

public abstract class Entity implements GUI {

    WindowManager wm = WindowManager.getInstance();

    private Deck deck;
    private ArrayList<Card> hand;
    private PGraphics texture;
    private int x;
    private int y;
    private int width, height;
    private int health;
    private int maxHealth;
    private int attack;
    private boolean visibility;

    private LinkedList<GUI> enumeration;

    public Entity(PImage texture, int x, int y, int width, int height){
        this.texture = wm.newGraphic(width, height);
        this.texture.beginDraw();
        this.texture.image(texture, 0, 0, width, height);
        this.texture.endDraw();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visibility = true;

        enumeration = new LinkedList<GUI>();
        enumeration.add(this);


    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }
    public PGraphics getTexture() {
        return this.texture;
    }

    public boolean visible() {
        return visibility;
    }

    public void setVisibility(boolean visible) {
        this.visibility = visible;
        
    }
    public boolean click(int mouseX, int mouseY) {
        return false;
    }

    public List<GUI> enumerate() {
        return this.enumeration;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    public Deck getDeck() {
        return this.deck;
    }
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
    public ArrayList<Card> getHand() {
        return this.hand;
    }
    // public void setTexture(ImageElement texture) {
    //     this.texture = texture;
    //     this.x = texture.getX();
    //     this.y = texture.getY();
    //     wm.put(texture);
    // }
    // public void removeTexture() {
    //     wm.remove(texture);
    // }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getAttack() {
        return this.attack;
    }
    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }
    public void addHealth(int amt){
        this.setHealth(this.getHealth() + amt);
    }
    public int getHealth() {
        return this.health;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        this.setHealth(maxHealth);
    }
    public void addMaxHealth(int amt){
        this.setMaxHealth(this.getMaxHealth() + amt);
        this.addHealth(amt);
    }
    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void drawCard() {
        if (deck.cardsLeft() == 0) {
            deck.shuffle();
        }
        Card card = deck.draw();
        hand.add(card);
    }

}