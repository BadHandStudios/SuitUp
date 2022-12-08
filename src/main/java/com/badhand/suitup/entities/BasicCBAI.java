package com.badhand.suitup.entities;

import com.badhand.suitup.ui.Card;
import com.badhand.suitup.game.*;

public class BasicCBAI extends CombatAI{
    
    private Player player = Player.getInstance();

    public String getAction() {
        String action = "";

        int rand = random(1,3);

        switch(rand) {
            case 1:
                action = "Attack";
                break;
            case 2:
                action = "Block";
                break;
            case 3:
                action = "Nothing";
                break;
        }

        return action;
    }
}
