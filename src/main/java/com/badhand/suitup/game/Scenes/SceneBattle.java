package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

import java.util.*;

public class SceneBattle implements Scene {
    
    WindowManager wm = WindowManager.getInstance();
    AssetManager am = AssetManager.getInstance();
    EventManager em = EventManager.getInstance();

    int width = 1920;
    int height = 1080;
    int playerHealth = 25;
    int enemyHealth = 25;
    int playerTotal = 0;
    int enemyTotal = 0;

    ArrayList<Integer> playerHand = new ArrayList<Integer>();
    ArrayList<Integer> enemyHand = new ArrayList<Integer>();
    int[] playerPositions = {0,0,0,0,0};
    int[] enemyPositions = {0,0,0,0,0};

    TextButton hit = new TextButton("Hit",64,width/2 - 150,height/2,new Event(Events.CLICK,"Hit"));
    TextButton stay = new TextButton("Stay",64,width/2 + 150,height/2,new Event(Events.CLICK,"Stay"));
    TextElement winner = new TextElement("",64,200,height/2);

    boolean playerTurn = true;
    boolean roundStart = true;
    boolean roundEnd = false;
    boolean battle = false;

    public void initialize() {
        wm.clear();

        wm.setBackground(new Color(173,101,29));

        ImageElement player = new ImageElement("Player", 150, height - 200, 200, 300, am.getImage("Player.png"));
        ImageElement enemy  = new ImageElement("Enemy", (width - 150), 200, 200,300, am.getImage("Enemy.png"));
        wm.put(player);
        wm.put(enemy);

        TextElement playerHealthText = new TextElement("Health: " + playerHealth,36,150,height/2 + 140);
        wm.put(playerHealthText);

        TextElement enemyHealthText = new TextElement("Health: " + enemyHealth,36,width-150,height/2 - 140);
        wm.put(enemyHealthText);

        if (roundStart) {
            getCard(playerHand);
            getCard(playerHand);
            getCard(enemyHand);
            getCard(enemyHand);
            roundStart = false;
        }

        if (roundEnd) {
            TextButton reset = new TextButton("Continue",64,width/2,height/2, new Event(Events.CLICK,"reset"));
            wm.put(reset);
            wm.put(winner);
        }

        if (playerTurn) {
            wm.put(hit);
            wm.put(stay);
        }
    }

    public void update() {
        initialize();
        drawHand(playerHand,height-200);
        drawHand(enemyHand, 200);
    }

    public void handle(Event e) {
        if (e.getType() == Events.CLICK) {
            switch ("" + e.getData()) {
                case "Stay":
                    playerTurn = false;
                    gameLogic();
                    break;
                case "Hit":
                    getCard(playerHand);
                    update();
                    gameLogic();
                    break;
                case "reset":
                    if (playerHealth <= 0 || enemyHealth <= 0) {
                        Event end = new Event(Events.SCENE_CHANGE,GameState.MENU_LEVEL_SELECT);
                        em.push(end);
                    }
                    reset();
                    break;
            }
        }
    }

    public void reset() {
        playerHand.clear();
        enemyHand.clear();
        playerTotal = 0;
        enemyTotal = 0;
        playerTurn = true;
        roundStart = true;
        roundEnd = false;
        battle = false;
        playerPositions = new int[]{0,0,0,0,0};
        enemyPositions = new int[]{0,0,0,0,0};
    }

    public ArrayList<Integer> getCard(ArrayList<Integer> hand) {
        ArrayList<Integer> result = hand;

        int card = (int) (Math.random() * 10) + 1;

        result.add(card);

        return result;
    }

    public int[] formatHand(int size) {
        int[] result = {0,0,0,0,0};

        switch (size) {
            case 1:
                result = new int[]{960,0,0,0,0};
                break;
            case 2:
                result = new int[]{840,1080,0,0,0};
                break;
            case 3:
                result = new int[]{720,960,1200,0,0};
                break;
            case 4:
                result = new int[]{600,840,1080,1320,0};
                break;
            case 5:
                result = new int[]{480,720,960,1200,1440};
                break;
       }

        return result;
    }

    public void drawHand(ArrayList<Integer> hand, int handHeight) {
        int[] positions = {0,0,0,0,0};
        positions = formatHand(hand.size());

        for (int i = 0; i < hand.size(); i++) {
            //ImageElement Playercard = new ImageElement("Card", playerPositions[i],handHeight,200,300,am.getImage("Card.png"));
            TextElement card = new TextElement("" + hand.get(i),64,positions[i], handHeight);
            wm.put(card);
        }

    }

    public int handValue(ArrayList<Integer> hand) {
        int result = 0;

        for (int i = 0; i < hand.size(); i++) {
            result += hand.get(i);
        }

        return result;
    }

    public void enemyTurn() {
        if (enemyTotal <= 10) {
            getCard(enemyHand);
        }
        else {
            battle = true;
        }
    }

    public void gameLogic() {
        playerTotal = handValue(playerHand);
        enemyTotal = handValue(enemyHand);

        if (playerHand.size() >= 5) {
            playerTurn = false;
            if (playerTotal <= 21) {
                roundEnd = true;
                winner = new TextElement("Player Wins!",64, 200, height/2);
                enemyHealth -= 5;
            }
        }
        if (playerTotal > 21) {
            playerTurn = false;
            roundEnd = true;
            winner = new TextElement("Enemy Wins!",64, 200, height/2);
            playerHealth -= 5;
        }
        if (!playerTurn && !roundEnd) {
            enemyTurn();
            if (battle) {
                roundEnd = true;
            }
            else {
                gameLogic();
            }
        }

        if (battle) {
            if (playerTotal > enemyTotal) {
                winner = new TextElement("Player Wins!",64, 200, height/2);
                enemyHealth -= 5;
            }
            else {
                winner = new TextElement("Enemy Wins!",64, 200, height/2);
                playerHealth -= 5;
            }
            battle = false;
        }

        update();
    }
}
