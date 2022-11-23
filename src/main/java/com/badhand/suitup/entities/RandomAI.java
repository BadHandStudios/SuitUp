package com.badhand.suitup.entities;

public class RandomAI extends BlackJackAI{
    
    public boolean hit() {
        boolean result = false;
        int rand = random(0,1);
        if (rand == 0 && enemyTotal <= 21) {
            result = true;
        }
        return result;
    }
}
