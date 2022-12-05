package com.badhand.suitup.entities;

public class CombatAI extends AI {

    public String getAction() {
        String action = "";

        int rand = random(1,2);

        switch(rand) {
            case 1:
                action = "Attack";
                break;
            case 2:
                action = "Block";
                break;
        }

        return action;
    }

    public void doActions(String playerAction, String EnemyAction, int Attack) {

        double playerOffenseModifier = 1.0;
        double playerDefenseModifier = 1.0;
        double enemyOffenseModifier = 1.0;
        double enemyDefenseModifier = 1.0;

        int playerTotal = getPlayerTotal();
        int enemyTotal = getEnemyTotal();

        if (playerAction == "Attack") {
            playerOffenseModifier = 1.25;
            playerDefenseModifier = 0.75;
        }
        if (playerAction == "Block") {
            playerOffenseModifier = 0.75;
            playerDefenseModifier = 1.25;
        }

        if (EnemyAction == "Attack") {
            enemyOffenseModifier = 1.25;
            enemyDefenseModifier = 0.75;
        }
        if (EnemyAction == "Block") {
            enemyOffenseModifier = 0.75;
            enemyDefenseModifier = 1.25;
        }

        if (playerTotal > enemyTotal  && playerTotal < 21 || enemyTotal > 21) {
            int attack = Attack;

            attack *= playerOffenseModifier;
            attack /= enemyDefenseModifier;

            setEnemyHealth(getEnemyHealth() - (int)attack);
        }
        else if (playerTotal < enemyTotal && enemyTotal < 21 || playerTotal > 21) {
            int attack = Attack;

            attack *= enemyOffenseModifier;
            attack /= playerDefenseModifier;

            setPlayerHealth(getPlayerHealth() - (int)attack);
        }
    } 
}
