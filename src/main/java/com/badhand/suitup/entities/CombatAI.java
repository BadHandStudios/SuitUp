package com.badhand.suitup.entities;

import com.badhand.suitup.ui.Card;

public abstract class CombatAI extends AI {

    public abstract String getAction();
    public abstract void doActions(String playerAction, String EnemyAction, int Attack, Card c);
}
