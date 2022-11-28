package com.badhand.suitup.entities;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.entities.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;

import java.util.*;

public class Enemy extends Entity {

    private AssetManager am = AssetManager.getInstance();

    public BlackJackAI bjai = new BlackJackAI();


    public Enemy() {
        health = 25;
        setTexture(new ImageElement("Enemy", (1920 - 150), 200, 200,300, am.getImage("Enemy.png")));
        x = texture.getX();
        y = texture.getY();
        deck = new Deck();
        hand = new ArrayList<Card>();
    }

    public BlackJackAI getBJAI() {
        return bjai;
    }
}
