package com.badhand.suitup.entities;

public class BasicBJAI extends BlackJackAI{

    public boolean hit() {
        boolean result = false;
        getEnemyTotal();
        if (enemyTotal < 17) {
            result = true;
        }
        return result;
    }
}
