package com.badhand.suitup.entities;

import java.util.*;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;

abstract class Entity {

    WindowManager wm = WindowManager.getInstance();

    public Deck deck;
    public ArrayList<Card> hand;
    public ImageElement texture;
    public int x;
    public int y;
    public int health;

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
        this.health = health;
    }
    public int getHealth() {
        return this.health;
    }
    public void drawCard() {
        if (deck.cardsLeft() == 0) {
            deck.shuffle();
        }
        Card card = deck.draw();
        hand.add(card);
    }
}