package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;
import com.badhand.suitup.entities.*;

import java.util.*;
import processing.core.*;

public class SceneBattle implements Scene {
    
    WindowManager wm = WindowManager.getInstance();
    AssetManager am = AssetManager.getInstance();
    EventManager em = EventManager.getInstance();

    int width = 1920;
    int height = 1080;
    int health = 25;

    //ArrayList<Integer> playerHand = new ArrayList<Integer>();
    //ArrayList<Integer> enemyHand = new ArrayList<Integer>();
    int[] playerPositions = {0,0,0,0,0};
    int[] enemyPositions = {0,0,0,0,0};
    ArrayList<Card> playerHand = new ArrayList<Card>();
    ArrayList<Card> enemyHand = new ArrayList<Card>();
    Deck temp1 = new Deck();
    Deck temp2 = new Deck();

    TextButton hit = new TextButton("Hit",64,840,height/2,new Event(Events.CLICK,"Hit"));
    TextButton stay = new TextButton("Stay",64,1080,height/2,new Event(Events.CLICK,"Stay"));
    TextButton reset = new TextButton("Continue",64,width/2,height/2, new Event(Events.CLICK,"reset"));
    TextElement winner = new TextElement("",64,200,height/2);

    TextElement playerHealthText;
    TextElement enemyHealthText;

    boolean playerTurn = true;
    boolean roundStart = true;
    boolean battle = false;

    Enemy enemy;
    BlackJackAI bjai;
    Player player;

    public void initialize() {
        wm.clear();
        wm.setBackground(new Color(173,101,29));

        enemy = new Enemy();
        player = Player.getInstance();

        bjai = enemy.getBJAI();
        player.setHealth(25);
        player.setDeck(new Deck());
        player.setHand(new ArrayList<Card>());

        player.setTexture(new ImageElement("Player", 150, height - 200, 200, 300, am.getImage("Player.png")));

        ImageElement playerHeart = new ImageElement("playerHeart", 150, height/2 + 140, 100, 100, am.getImage("heart.png"));
        wm.put(playerHeart);
        ImageElement enemyHeart = new ImageElement("enemyHeart", width - 150, height/2 - 140, 100, 100, am.getImage("heart.png"));
        wm.put(enemyHeart);
        playerHealthText = new TextElement("" + player.getHealth(),36,150,height/2 + 140);
        wm.put(playerHealthText);
        enemyHealthText = new TextElement("" + enemy.getHealth(),36,width-150,height/2 - 140);
        wm.put(enemyHealthText);

        player.drawCard();
        player.drawCard();
        enemy.drawCard();
        enemy.drawCard();

        enemy.getHand().get(0).flip();
        drawHands();

        wm.put(hit);
        wm.put(stay);
    }

    public void update() {

    }

    public void handle(Event e) {
        if (e.getType() == Events.CLICK) {
            switch ("" + e.getData()) {
                case "Stay":
                    playerTurn = false;
                    wm.remove(hit);
                    wm.remove(stay);
                    enemyTurn();
                    wm.put(reset);
                    break;
                case "Hit":
                    player.drawCard();
                    drawHands();
                    gameLogic();
                    break;
                case "reset":
                    if (player.getHealth() <= 0 || enemy.getHealth() <= 0) {
                        Event end = new Event(Events.SCENE_CHANGE,GameState.MENU_LEVEL_SELECT);
                        em.push(end);
                    }
                    wm.remove(reset);
                    reset();
                    break;
            }
        }
    }

    public void reset() {
        wm.remove(winner);
        playerTurn = true;
        playerPositions = new int[]{0,0,0,0,0};
        enemyPositions = new int[]{0,0,0,0,0};

        playerHealthText.setText("" + player.getHealth());
        enemyHealthText.setText("" + enemy.getHealth());

        player.setHand(new ArrayList<Card>());
        enemy.setHand(new ArrayList<Card>());
        
        player.drawCard();
        player.drawCard();
        enemy.drawCard();
        enemy.drawCard();

        enemy.getHand().get(0).flip();
        drawHands();

        wm.put(hit);
        wm.put(stay);
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

    public void drawHands() {
        drawPlayerHand();
        drawEnemyHand();
    }

    public void drawPlayerHand() {
        int[] positions = {0,0,0,0,0};
        positions = formatHand(player.getHand().size());

        while (playerHand.size() > 0) {
            if (playerHand.get(0) != null) {
                wm.remove(playerHand.get(0));
                playerHand.remove(0);
            }
        }
        for (int i = 0; i < player.getHand().size(); i++) {
            playerHand.add(player.getHand().get(i));
            playerHand.get(i).setPos(positions[i], height - 200);
            wm.put(playerHand.get(i));
        }
    }
    public void drawEnemyHand() {
        int[] positions = {0,0,0,0,0};
        positions = formatHand(enemy.getHand().size());

        while (enemyHand.size() > 0) {
            if (enemyHand.get(0) != null) {
                wm.remove(enemyHand.get(0));
                enemyHand.remove(0);
            }
        }
        for (int i = 0; i < enemy.getHand().size(); i++) {
            enemyHand.add(enemy.getHand().get(i));
            enemyHand.get(i).setPos(positions[i], 200);
            wm.put(enemyHand.get(i));
        }
    }

    public void enemyTurn() {
        bjai.setEnemyHand(enemy.getHand());
        bjai.setPlayerHand(player.getHand());
        if (bjai.hit()) {
            enemy.drawCard();
            drawHands();
            bjai.updateTotals();
            if (bjai.getEnemyTotal() > 21) {
                gameLogic();
            }
            else {
                enemyTurn();
            }
        }
        else {
            gameLogic();
        }
    }

    public void gameLogic() {
        bjai.setEnemyHand(enemy.getHand());
        bjai.setPlayerHand(player.getHand());
        bjai.updateTotals();

        if (player.getHand().size() == 5 && bjai.playerTotal <= 21) {
            enemy.setHealth(enemy.getHealth() - 5);
            bjai.setEnemyHealth(enemy.getHealth());
            enemyHealthText.setText("" + enemy.getHealth());
            winner = new TextElement("Player Wins!",64, 200, height/2);
            wm.put(winner);
            wm.remove(hit);
            wm.remove(stay);
            wm.put(reset);
            enemy.getHand().get(0).flip();
        }
        else if (enemy.getHand().size() == 5 && bjai.enemyTotal <= 21) {
            player.setHealth(player.getHealth() - 5);
            bjai.setPlayerHealth(player.getHealth());
            playerHealthText.setText("" + player.getHealth());
            winner = new TextElement("Enemy Wins!",64, 200, height/2);
            wm.put(winner);
            enemy.getHand().get(0).flip();
        }
        else if (bjai.playerTotal <= 21 && !playerTurn) {
            if (bjai.enemyTotal > 21) {
                enemy.setHealth(enemy.getHealth() - 5);
                bjai.setEnemyHealth(enemy.getHealth());
                enemyHealthText.setText("" + enemy.getHealth());
                winner = new TextElement("Player Wins!",64, 200, height/2);
                // This is causing the issue
                wm.put(winner);
                enemy.getHand().get(0).flip();
            }
            else {
                if (bjai.getEnemyTotal() > bjai.getPlayerTotal() && bjai.getEnemyTotal() <= 21) {
                    player.setHealth(player.getHealth() - 5);
                    bjai.setPlayerHealth(player.getHealth());
                    playerHealthText.setText("" + player.getHealth());
                    winner = new TextElement("Enemy Wins!",64, 200, height/2);
                    wm.put(winner);
                    enemy.getHand().get(0).flip();
                }
                else if (bjai.getEnemyTotal() < bjai.getPlayerTotal() && bjai.getPlayerTotal() <= 21) {
                    enemy.setHealth(enemy.getHealth() - 5);
                    bjai.setEnemyHealth(enemy.getHealth());
                    enemyHealthText.setText("" + enemy.getHealth());
                    winner = new TextElement("Player Wins!",64, 200, height/2);
                    wm.put(winner);
                    enemy.getHand().get(0).flip();
                }
                else {
                    winner = new TextElement("Draw!",64, 200, height/2);
                    wm.put(winner);
                    enemy.getHand().get(0).flip();
                }
            }
        }
        else if (bjai.playerTotal > 21) {
            player.setHealth(player.getHealth() - 5);
            bjai.setPlayerHealth(player.getHealth());
            playerHealthText.setText("" + player.getHealth());
            winner = new TextElement("Enemy Wins!",64, 200, height/2);
            wm.put(winner);
            wm.remove(hit);
            wm.remove(stay);
            wm.put(reset);
            enemy.getHand().get(0).flip();
        }
    }
}
