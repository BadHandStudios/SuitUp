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

        wm.setBackground(new Color(173,101,29));

        ImageElement player = new ImageElement("Player", 150, height - 200, 200, 300, am.getImage("Player.png"));
        ImageElement enemy  = new ImageElement("Enemy", (width - 150), 200, 200,300, am.getImage("Enemy.png"));
        wm.put(player);
        wm.put(enemy);

        TextElement playerHealthText = new TextElement("Health: " + playerHealth,36,150,height/2 + 140);
        wm.put(playerHealthText);

        TextElement enemyHealthText = new TextElement("Health: " + playerHealth,36,width-150,height/2 - 140);
        wm.put(enemyHealthText);


        int cardNum = 5;
        int[] positions = {0,0,0,0,0};

       switch (cardNum) {
            case 1:
                positions = new int[]{960,0,0,0,0};
                break;
            case 2:
                positions = new int[]{840,1080,0,0,0};
                break;
            case 3:
                positions = new int[]{720,960,1200,0,0};
                break;
            case 4:
                positions = new int[]{600,840,1080,1320,0};
                break;
            case 5:
                positions = new int[]{480,720,960,1200,1440};
                break;
       }

        for (int i = 0; i < cardNum; i++) {
            ImageElement Playercard = new ImageElement("Card", positions[i],height-200,200,300,am.getImage("Card.png"));
            wm.put(Playercard);
            ImageElement Enemycard = new ImageElement("Card", positions[i],200,200,300,am.getImage("Card.png"));
            wm.put(Enemycard);
        }

    }

    public void update() {

    }

    public void handle(Event e) {

    }
}
