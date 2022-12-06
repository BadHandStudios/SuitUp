package com.badhand.suitup.entities;

public class ByTheBooksAI extends BlackJackAI {
    public boolean hit(){
        if(this.getEnemyTotal() < 11) return true;
        if(this.getPlayerTotal() <= 6 && this.getEnemyTotal() > 12) return false;
        if(this.getPlayerTotal() > 6 && this.getEnemyTotal() < 17) return true;
        return false;
    }
    
}
