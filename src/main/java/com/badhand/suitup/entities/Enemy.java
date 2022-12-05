package com.badhand.suitup.entities;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.entities.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;

import processing.core.PImage;

import java.util.*;

public class Enemy extends Entity {

    private AssetManager am = AssetManager.getInstance();

    public BlackJackAI bjai = new BlackJackAI();
    public CombatAI cbai = new CombatAI();


    public Enemy(PImage texture, String name, int health, int attack, int x, int y, BlackJackAI ai) {
        super(texture, x, y, 200, 300);
        this.setMaxHealth(health);
        this.setDeck(new Deck());
        this.setHand(new ArrayList<Card>());
    }

    public BlackJackAI getBJAI() {
        return bjai;
    }
    public CombatAI getCBAI() {
        return cbai;
    }




   

    
}
