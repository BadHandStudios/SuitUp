package com.badhand.suitup.entities;

public class SmartBJAI extends BlackJackAI{
    
    public boolean hit() {
        boolean result = false;

        if (getEnemyTotal() < getPlayerTotal()) {
            result = true;
        }

        return result;
    }
}
