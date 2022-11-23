package com.badhand.suitup.entities;

import java.util.*;

abstract class AI {

    public ArrayList<Integer> enemyHand;
    public ArrayList<Integer> playerHand;
    public int enemyHealth;
    public int playerHealth;
    
    public void setEnemyHand(ArrayList<Integer> enemyHand) {
        this.enemyHand = enemyHand;
    }
    public void setPlayerHand(ArrayList<Integer> playerHand) {
        this.playerHand = playerHand;
    }
    public void setEnemyHealth(int health) {
        this.enemyHealth = health;
    }
    public void setPlayerHealth(int health) {
        this.playerHealth = health;
    }
    public int getEnemyTotal() {
        int total = 0;
        for (int i = 0; i < enemyHand.size(); i++) {
            total += enemyHand.get(i);
        }
        return total;
    }
    public int getPlayerTotal() {
        int total = 0;
        for (int i = 0; i < playerHand.size(); i++) {
            total += playerHand.get(i);
        }
        return total;
    }
    public int random(int a, int b) {
        int max = 0;
        int min = 0;
        if (a >= b) {
            max = a;
            min = b;
        }
        else {
            max = b;
            min = a;
        }
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }
}
