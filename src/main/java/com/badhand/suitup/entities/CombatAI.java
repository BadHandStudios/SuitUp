package com.badhand.suitup.entities;

public abstract class CombatAI extends AI {

    public abstract String getAction();
    public abstract void doActions(String playerAction, String EnemyAction, int Attack);
}
