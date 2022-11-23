package com.badhand.suitup.entities;

import java.util.*;

abstract class BlackJackAI extends AI{
    
    public int enemyTotal;
    public int playerTotal;
    
    public boolean compareHands() {
        // return true if enemyTotal is higher than playerTotal
        return (getEnemyTotal() > getPlayerTotal());
    }
}
