package com.badhand.suitup.entities;

import java.util.*;
import com.badhand.suitup.ui.*;

abstract class AI {

    public ArrayList<Card> enemyHand;
    public ArrayList<Card> playerHand;
    public int enemyHealth;
    public int playerHealth;
    public int enemyTotal;
    public int playerTotal;
    
    public void setEnemyHand(ArrayList<Card> enemyHand) {
        this.enemyHand = enemyHand;
    }
    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }
    public void setEnemyHealth(int health) {
        this.enemyHealth = health;
    }
    public int getEnemyHealth() {
        return enemyHealth;
    }
    public void setPlayerHealth(int health) {
        this.playerHealth = health;
    }
    public int getPlayerHealth() {
        return playerHealth;
    }
    public int getEnemyTotal() {
        int total = 0;
        for (int i = 0; i < enemyHand.size(); i++) {
            total += enemyHand.get(i).getValue();
        }
        if (total > 21) {
            for (int i = 0; i < enemyHand.size(); i++) {
                if (enemyHand.get(i).getValue() == 11 && total > 21) {
                    total -= 10;
                }
            }
        }
        enemyTotal = total;
        return enemyTotal;
    }
    public int getPlayerTotal() {
        int total = 0;
        for (int i = 0; i < playerHand.size(); i++) {
            total += playerHand.get(i).getValue();
        }
        if (total > 21) {
            for (int i = 0; i < playerHand.size(); i++) {
                if (playerHand.get(i).getValue() == 11 && total > 21) {
                    total -= 10;
                }
            }
        }
        playerTotal = total;
        return playerTotal;
    }
    public void updateTotals() {
        getEnemyTotal();
        getPlayerTotal();
    }
    public int random(int min, int max) {
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }
}
