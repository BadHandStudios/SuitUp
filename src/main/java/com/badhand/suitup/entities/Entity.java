package com.badhand.suitup.entities;

import java.util.*;

import com.badhand.suitup.ui.*;

abstract class Entity {

    WindowManager wm = WindowManager.getInstance();

    private ArrayList<Integer> deck;
    private ArrayList<Integer> hand;
    private ImageElement texture;
    private int x;
    private int y;
    private int health;

    public void setDeck(ArrayList<Integer> deck) {
        this.deck = deck;
    }
    public ArrayList<Integer> getDeck() {
        return this.deck;
    }
    public void setHand(ArrayList<Integer> hand) {
        this.hand = hand;
    }
    public ArrayList<Integer> getHand() {
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
        this.health = health;
    }
    public int getHealth() {
        return this.health;
    }
    public void drawCard() {
        Integer card = deck.get(0);
        deck.remove(0);
        if (deck.size() <= 0) {
            // turn deck back into template deck
            // shuffle
        }
        hand.add(card);
    }
}