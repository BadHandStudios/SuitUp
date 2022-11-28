package com.badhand.suitup.entities;

import java.util.*;

public class BlackJackAI extends AI{
    
    public boolean hit() {
        boolean result = false;
        getEnemyTotal();
        if (enemyTotal < 17) {
            result = true;
        }
        return result;
    }

    public void updateTotals() {
        getEnemyTotal();
        getPlayerTotal();
    }
}
