package com.badhand.suitup.entities;

public class RandomBJAI extends BlackJackAI{
    
    public boolean hit() {
        boolean result = false;
        
        int rand = random(1,2);

        if (rand == 1) {
            result = true;
        }

        return result;
    }
}
