package com.badhand.suitup.entities;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.entities.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;

import processing.core.PImage;

import java.util.*;

public class Enemy extends Entity {

    private AssetManager am = AssetManager.getInstance();

    private BlackJackAI bjai;
    private CombatAI cbai;
    
    public Enemy(PImage texture, String name, int health, int attack, int x, int y, BlackJackAI bjai, CombatAI cbai) {
        super(texture, x, y, 200, 300);
        this.setMaxHealth(health);
        this.setDeck(new Deck());
        this.setHand(new ArrayList<Card>());
        this.setAttack(attack);

        this.bjai = bjai;
        this.cbai = cbai;
    }

    public BlackJackAI getBJAI() {
        return bjai;
    }
    public CombatAI getCBAI() {
        return cbai;
    }




   

    
}
