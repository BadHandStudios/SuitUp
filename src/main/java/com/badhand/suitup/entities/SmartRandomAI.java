package com.badhand.suitup.entities;

public class SmartRandomAI extends BlackJackAI {
    public boolean hit(){
        return random(1, 2) == 1 && getEnemyTotal() <= 15;
    }
}
