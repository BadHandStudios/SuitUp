package com.badhand.suitup.entities;

import java.util.*;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;

public abstract class Entity implements GUI {

    WindowManager wm = WindowManager.getInstance();

    public Deck deck;
    public ArrayList<Card> hand;
    public ImageElement texture;
    public int x;
    public int y;
    public int health;
    public int maxHealth;

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
    public void setTexture(ImageElement texture) {
        this.texture = texture;
        this.x = texture.getX();
        this.y = texture.getY();
        wm.put(texture);
    }
    public void removeTexture() {
        wm.remove(texture);
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
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