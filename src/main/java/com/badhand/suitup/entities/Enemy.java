package com.badhand.suitup.entities;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.entities.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;

import processing.core.*;

import java.util.*;

public class Enemy extends Entity {

    private AssetManager am = AssetManager.getInstance();

    private BlackJackAI bjai;
    private CombatAI cbai;

    private String name;

    private PGraphics rawTexture;
    
    public Enemy(PImage texture, String name, int health, int attack, int x, int y, BlackJackAI bjai, CombatAI cbai) {
        super(texture, x, y, 100, 100);
        this.setMaxHealth(health);
        this.setDeck(new Deck());
        this.setHand(new ArrayList<Card>());
        this.setAttack(attack);

        this.rawTexture = wm.newGraphic(texture.width, texture.height);
        rawTexture.beginDraw();
        rawTexture.image(texture, 0, 0, texture.width, texture.height);
        rawTexture.endDraw();

        this.bjai = bjai;
        this.cbai = cbai;

        this.name = name;
    }

    public BlackJackAI getBJAI() {
        return bjai;
    }
    public CombatAI getCBAI() {
        return cbai;
    }


    public PGraphics rawTexture(){
        return this.rawTexture;
    }

    public String getName(){
        return name;
    }

   

    
}
