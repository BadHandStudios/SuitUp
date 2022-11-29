package com.badhand.suitup.entities;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.entities.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;

import processing.core.PGraphics;

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

    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setPos(int x, int y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public PGraphics getTexture() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean visible() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setVisibility(boolean visible) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean click(int mouseX, int mouseY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<GUI> enumerate() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
