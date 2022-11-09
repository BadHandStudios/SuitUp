package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

import java.util.*;

public class SceneBattle implements Scene {
    
    WindowManager wm = WindowManager.getInstance();
    AssetManager am = AssetManager.getInstance();

    int width = 1920;
    int height = 1080;

    //ArrayList<Card> playerHand = new ArrayList<Card>();
    //ArrayList<Card> enemyHand = new ArrayList<Card>();

    int playerHealth = 25;
    int enemyHealth = 25;

    public void initialize() {
        wm.clear();

        wm.setBackground(new Color(50,50,50));


    }

    public void update() {

    }

    public void handle(Event e) {

    }
}
