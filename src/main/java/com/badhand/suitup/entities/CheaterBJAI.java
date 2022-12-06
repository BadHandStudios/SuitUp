package com.badhand.suitup.entities;

import java.util.*;
import com.badhand.suitup.ui.*;

public class CheaterBJAI extends BlackJackAI{
    
    public boolean hit() {
        boolean result = false;

        ArrayList<Card> temp = this.getEnemyHand();
        
        ArrayList<Card> newHand = this.getEnemyHand();
        newHand.add(enemyDeck.peek());
        

        if (this.getEnemyTotal() <= 21) {
            result = true;
        }

        setEnemyHand(temp);
        this.getEnemyTotal();

        return result;
    }
}
