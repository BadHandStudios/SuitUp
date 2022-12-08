package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;
import com.badhand.suitup.entities.*;

import java.util.*;

public class SceneBattle implements Scene {
    
    WindowManager wm = WindowManager.getInstance();
    AssetManager am = AssetManager.getInstance();
    EventManager em = EventManager.getInstance();

    int width = wm.getWidth();
    int height = wm.getHeight();
    int animWidth = width;
    int animHeight = height/2;
    int timer = 0;
    int enemyHandIndex = 0;
    int playerHandIndex = 0;

    String playerAction;
    String enemyAction;
    String filename = "";

    int[] playerPositions = {0,0,0,0,0};
    int[] enemyPositions = {0,0,0,0,0};
    ArrayList<Card> playerHand = new ArrayList<Card>();
    ArrayList<Card> enemyHand = new ArrayList<Card>();
    // Deck temp1 = new Deck();
    // Deck temp2 = new Deck();

    TextButton hit = new TextButton("Hit",64,840,height/2,new Event(Events.CLICK,"Hit"));
    TextButton stay = new TextButton("Stay",64,1080,height/2,new Event(Events.CLICK,"Stay"));
    TextButton attack = new TextButton("Strong",64,width/2 - 300,height/2,new Event(Events.CLICK,"Attack"));
    TextButton block = new TextButton("Block",64,width/2 + 300,height/2,new Event(Events.CLICK,"Block"));
    TextButton nothing = new TextButton("Attack",64,width/2,height/2,new Event(Events.CLICK,"Nothing"));
    TextButton reset = new TextButton("Continue",64,width/2,height/2, new Event(Events.CLICK,"reset"));
    TextElement winner = new TextElement("",64,200,height/2);

    ImageElement playerDeckGilded = new ImageElement(width - 200, height - 200,200,300,am.getImage("CardBack3.png"));
    ImageElement enemyDeckGilded = new ImageElement(200, 200,200,300,am.getImage("CardBack3.png"));

    ImageElement playerDeckTop = new ImageElement(width - 200, height - 200,200,300,am.getImage("CardBack1.png"));
    ImageElement enemyDeckTop = new ImageElement(200, 200,200,300,am.getImage("CardBack1.png"));
    ImageElement playerDrawnCard = new ImageElement(width - 200, height - 200,200,300,am.getImage("CardBack1.png"));
    ImageElement playerDrawnCardGilded = new ImageElement(width - 200, height - 200,200,300,am.getImage("CardBack3.png"));
    ImageElement enemyDrawnCard = new ImageElement(200, 200,200,300,am.getImage("CardBack1.png"));
    boolean animatePlayerDrawnCard = false;
    boolean animateEnemyDrawnCard = false;

    TextElement playerHealthText;
    TextElement enemyHealthText;

    ImageElement animCard;

    boolean playerTurn = true;
    boolean roundStart = true;
    boolean battle = false;
    boolean hitVisible = false;
    boolean resetRound = false;
    boolean animate = false;

    boolean optionsShowing = false;
    boolean hitStayShowing = false;
    boolean continueShowing = false;

    Enemy enemy;
    BlackJackAI bjai;
    CombatAI cbai;
    Player player;

    private Card mostRecentGildedCard;

    public SceneBattle(Enemy enemy) {
        this.enemy = enemy;
    }

    public void initialize() {

        am.stopSound(0);
        am.loopSound("combat_background_music.mp3", 0);
        wm.clear();
        wm.setBackground(new Color(10, 60, 20));

        timer = 0;
        playerHandIndex = 0;
        enemyHandIndex = 0;

        player = Player.getInstance();
        bjai = enemy.getBJAI();
        cbai = enemy.getCBAI();
        cbai.setPlayerHealth(player.getHealth());
        cbai.setEnemyHealth(enemy.getHealth());

        player.setHand(new ArrayList<Card>());
        
        player.setPos(150, height-200);
        enemy.setPos(wm.getWidth() - 150, 200);

        ImageElement playerImage = new ImageElement(player.getX(), player.getY(), 200, 300, am.getImage("character.png"));
        ImageElement enemyImage = new ImageElement(enemy.getX(), enemy.getY(), 200, 300, enemy.rawTexture());
        
        wm.put(enemyImage);
        wm.put(playerImage);

        int offset = 100;

        ImageElement playerHeart = new ImageElement("playerHeart", 150, height/2 + offset, 100, 100, am.getImage("heart.png"));
        wm.put(playerHeart);
        ImageElement enemyHeart = new ImageElement("enemyHeart", width - 150, height/2 - offset, 100, 100, am.getImage("heart.png"));
        wm.put(enemyHeart);
        playerHealthText = new TextElement("" + player.getHealth(),36,150,height/2 + offset);
        wm.put(playerHealthText);
        enemyHealthText = new TextElement("" + enemy.getHealth(),36,width-150,height/2 - offset);
        wm.put(enemyHealthText);

        mostRecentGildedCard = null;
        Card c = player.drawCard();
        animPlayerCard();
        
        Effect eff = c.getEffect();
        if(c.isGilded()){
            if(eff.getEffect() == Effects.INSTANT_DAMAGE){
                c.activate();
                if(enemy.getHealth() > eff.getValue()) enemy.addHealth(-1 * (int) eff.getValue());
                updateHealth();
            }else if(eff.getEffect() == Effects.HEAL){
                c.activate();
                player.addHealth((int) eff.getValue());
                updateHealth();
            }
            else  mostRecentGildedCard = c;
        }
        
        c = player.drawCard();
        animPlayerCard();

        eff = c.getEffect();
        if(c.isGilded()){
            if(eff.getEffect() == Effects.INSTANT_DAMAGE){
                c.activate();
                if(enemy.getHealth() > eff.getValue()) enemy.addHealth(-1 * (int) eff.getValue());
                updateHealth();
            }else if(eff.getEffect() == Effects.HEAL){
                c.activate();
                player.addHealth((int) eff.getValue());
                updateHealth();
            }
            else  mostRecentGildedCard = c;
        }
        enemy.drawCard();
        enemy.drawCard();

        enemy.getHand().get(0).flip();
        drawHands();
        playerHand.get(0).setVisibility(false);
        playerHand.get(1).setVisibility(false);
        enemyHand.get(0).setVisibility(false);
        enemyHand.get(1).setVisibility(false);
        
        wm.put(playerDeckGilded);
        wm.put(enemyDeckGilded);
        wm.put(playerDrawnCard);
        playerDrawnCard.setVisibility(false);
        wm.registerDiffered(playerDrawnCard);
        wm.put(playerDeckTop);
        wm.put(enemyDeckTop);

        wm.put(playerDrawnCardGilded);
        playerDrawnCardGilded.setVisibility(false);
        wm.registerDiffered(playerDrawnCardGilded);

        wm.put(enemyDrawnCard);
        enemyDrawnCard.setVisibility(false);
        wm.registerDiffered(enemyDrawnCard);





        // wm.put(attack);
        // wm.put(nothing);
        // wm.put(block);
    }

    public void update() {
        if(player.getDeck().peek().isGilded()) playerDeckTop.setVisibility(false);
        else playerDeckTop.setVisibility(true);

        if(animatePlayerDrawnCard){
            ImageElement drawnCard = player.getDeck().peek().isGilded() ? playerDrawnCardGilded : playerDrawnCard;
            if(!drawnCard.visible()) drawnCard.setVisibility(true);
            if(drawnCard.getY() < height + 500) drawnCard.setPos(drawnCard.getX(), drawnCard.getY() + 25);
            else{
                animatePlayerDrawnCard = false;
                drawnCard.setVisibility(false);
                drawnCard.setPos(width - 200, height - 200);
            }
        }
        if(animateEnemyDrawnCard){
            ImageElement drawnCard = enemyDrawnCard;
            if(!drawnCard.visible()) drawnCard.setVisibility(true);
            if(drawnCard.getY() > -500) drawnCard.setPos(drawnCard.getX(), drawnCard.getY() - 25);
            else{
                animateEnemyDrawnCard = false;
                drawnCard.setVisibility(false);
                drawnCard.setPos(200, 200);
            }
        }
        
        if (animate) {
            // if(optionsShowing) hidePlayerOptions();
            if (timer > 0) {
                timer--;
            }
            else {
                timer = 30;
    
                if (playerHandIndex < playerHand.size()) {
                    int rand = cbai.random(0,3);
                    switch (rand) {
                        case 0:
                            filename = "cardPlace1.mp3";
                            break;
                        case 1:
                            filename = "cardPlace2.mp3";
                            break;
                        case 2:
                            filename = "cardPlace3.mp3";
                            break;
                        case 3:
                            filename = "cardSlide1.mp3";
                            break;
                    }
    
                    if (!playerHand.get(playerHandIndex).visible()) {
                        playerHand.get(playerHandIndex).setVisibility(true);
                        playerHandIndex++;
                        am.playSound(filename,1);
                    }
                    else {
                        playerHandIndex++;
                        if (enemyHandIndex < enemyHand.size()) {
                            if (!enemyHand.get(enemyHandIndex).visible()) {
                                enemyHand.get(enemyHandIndex).setVisibility(true);
                                enemyHandIndex++;
                                am.playSound(filename,1);
                                animEnemyCard();
                            }
                            else {
                                enemyHandIndex++;
                            }
                        }
                        else {
                            if (!playerTurn) {
                                animate = false;
                            }
                            if(!optionsShowing && !hitStayShowing && !continueShowing && animate){
                                showPlayerOptions();
                            }else if(!optionsShowing && !hitStayShowing && !continueShowing && !animate){
                                
                                continueShowing = true;
                                this.updateHealth();
                                wm.put(winner);
                                wm.put(reset);
                            }
                            enemyHandIndex = 0;
                            
                        }
                    }
                }
                else {
                    playerHandIndex = 0;
                }
            }
        }
        else {
            timer = 0;
            
        }
    }

    public void handle(Event e) {
        if (e.getType() == Events.CLICK) {
            switch ("" + e.getData()) {
                case "Stay":
                    playerTurn = false;
                    hideHitStay();
                    enemyTurn();
                    break;
                case "Hit":
                    int rand = cbai.random(0,3);
                        switch (rand) {
                            case 0:
                                filename = "cardPlace1.mp3";
                                break;
                            case 1:
                                filename = "cardPlace2.mp3";
                                break;
                            case 2:
                                filename = "cardPlace3.mp3";
                                break;
                            case 3:
                                filename = "cardSlide1.mp3";
                                break;
                        }
                    if (player.getHand().size() < 5) {
                        Card c = player.drawCard();
                        am.playSound(filename, 1);
                        animPlayerCard();
                        
                        if(c.isGilded()){
                            Effect eff = c.getEffect();
                            mostRecentGildedCard = c;
                            if(player.getHandTotal() > 21 && eff.getEffect() == Effects.BUST_PROOF) c.activate();
                            if(c.isGilded()){
                                if(eff.getEffect() == Effects.INSTANT_DAMAGE){
                                    c.activate();
                                    if(enemy.getHealth() > eff.getValue()) enemy.addHealth(-1 * (int) eff.getValue());
                                    updateHealth();
                                }else if(eff.getEffect() == Effects.HEAL){
                                    c.activate();
                                    player.addHealth((int) eff.getValue());
                                    updateHealth();
                                }
                                else  mostRecentGildedCard = c;
                            }
                        }
                    }
                    drawHands();
                    gameLogic();
                    
                    break;
                case "Attack":
                    playerAction = "Attack";
                    hidePlayerOptions();
                    showHitStay();
                    hitVisible = true;
                    break;
                case "Block":
                    playerAction = "Block";
                    hidePlayerOptions();
                    showHitStay();
                    hitVisible = true;
                    break;
                case "Nothing":
                    playerAction = "Nothing";
                    hidePlayerOptions();
                    showHitStay();
                    hitVisible = true;
                    break;
                case "reset":
                    if (player.getHealth() <= 0 || enemy.getHealth() <= 0) {
                        if (enemy.getHealth() <= 0) {
                            player.addChips((enemy.getMaxHealth() * 10) + (cbai.random(1,enemy.getAttack()) * 10));
                        }else if(player.getHealth() <= 0){
                            MapScene.playerDeath();
                            player.reset();

                            Event end = new Event(Events.SCENE_CHANGE,GameState.GAME_OVER);
                            em.push(end);
                            break;
                        }
                        Event end = new Event(Events.SCENE_CHANGE,GameState.MAP_SCENE);
                        em.push(end);
                    }
                    else {
                        wm.remove(reset);
                        continueShowing = false;
                        reset();
                    }
                    break;
            }
        }
    }

    public void reset() {
        wm.remove(winner);
        playerTurn = true;
        playerPositions = new int[]{0,0,0,0,0};
        enemyPositions = new int[]{0,0,0,0,0};

        timer = 0;
        playerHandIndex = 0;
        enemyHandIndex = 0;
        hitVisible = false;

        playerHealthText.setText("" + player.getHealth());
        enemyHealthText.setText("" + enemy.getHealth());

        player.setHand(new ArrayList<Card>());
        enemy.setHand(new ArrayList<Card>());
        
        mostRecentGildedCard = null;
        Card c = player.drawCard();
        animPlayerCard();
        Effect eff = c.getEffect();
        if(c.isGilded()){
            if(eff.getEffect() == Effects.INSTANT_DAMAGE){
                c.activate();
                if(enemy.getHealth() > eff.getValue()) enemy.addHealth(-1 * (int) eff.getValue());
                updateHealth();
            }else if(eff.getEffect() == Effects.HEAL){
                c.activate();
                player.addHealth((int) eff.getValue());
                updateHealth();
            }
            else  mostRecentGildedCard = c;
        }
        c = player.drawCard();
        animPlayerCard();

        eff = c.getEffect();
        if(c.isGilded()){
            if(eff.getEffect() == Effects.INSTANT_DAMAGE){
                c.activate();
                if(enemy.getHealth() > eff.getValue()) enemy.addHealth(-1 * (int) eff.getValue());
                updateHealth();
            }else if(eff.getEffect() == Effects.HEAL){
                c.activate();
                player.addHealth((int) eff.getValue());
                updateHealth();
            }
            else  mostRecentGildedCard = c;
        }
        enemy.drawCard();
        enemy.drawCard();

        enemy.getHand().get(0).flip();
        drawHands();
        playerHand.get(0).setVisibility(false);
        playerHand.get(1).setVisibility(false);
        enemyHand.get(0).setVisibility(false);
        enemyHand.get(1).setVisibility(false);

        // wm.put(attack);
        // wm.put(nothing);
        // wm.put(block);
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
        if (playerTurn) {
            timer = 0;
        }
        animate = true;
        // if(optionsShowing) hidePlayerOptions();
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
            if (i > 1) {
                //playerHandIndex = i;
            }
        }
        //if (playerHandIndex > 1 && playerTurn) {
        //    playerHand.get(playerHandIndex).setVisibility(false);
        //}
        if (playerHandIndex > 1 && playerTurn) {
            //playerHand.get(playerHandIndex).setVisibility(false);
            //playerHandIndex = 2;
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
            if (i > 1) {
                enemyHandIndex = i;
            }
        }
        if (enemyHandIndex > 1 && !playerTurn) {
            enemyHand.get(enemyHandIndex).setVisibility(false);
            enemyHandIndex = 2;
        }
    }

    public void enemyTurn() {
        bjai.setEnemyHand(enemy.getHand());
        bjai.setPlayerHand(player.getHand());
        if (bjai.hit()) {
            enemy.drawCard();
            drawHands();
            bjai.updateTotals();
            if (bjai.getEnemyTotal() > 21 || enemy.getHand().size() == 5) {
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
        cbai.setEnemyHand(enemy.getHand());
        cbai.setPlayerHand(player.getHand());
        bjai.updateTotals();
        cbai.updateTotals();

        if (player.getHand().size() == 5 && bjai.playerTotal <= 21) {
            this.doActions(playerAction,cbai.getAction(), player.getAttack(), mostRecentGildedCard);
            bjai.setEnemyHealth(enemy.getHealth());
            if (enemy.getHealth() < 0) {
                enemy.setHealth(0);
            }
            //enemyHealthText.setText("" + enemy.getHealth());
            winner = new TextElement("Player Wins!",64, 200, height/2);
            hideHitStay();
            //
            // continueShowing = true;
            // wm.put(winner);
            // wm.put(reset);
            enemy.getHand().get(0).flip();
            resetRound = true;
        }
        else if (enemy.getHand().size() == 5 && bjai.enemyTotal <= 21) {
            this.doActions(playerAction,cbai.getAction(), enemy.getAttack(), mostRecentGildedCard);
            bjai.setPlayerHealth(player.getHealth());
            if (player.getHealth() < 0) {
                player.setHealth(0);
            }
            //playerHealthText.setText("" + player.getHealth());
            winner = new TextElement("Enemy Wins!",64, 200, height/2);
            //
            // wm.put(winner);
            // continueShowing = true;
            // wm.put(reset);
            enemy.getHand().get(0).flip();
            resetRound = true;
        }
        else if (bjai.playerTotal <= 21 && !playerTurn) {
            if (bjai.enemyTotal > 21) {
                this.doActions(playerAction,cbai.getAction(), player.getAttack(), mostRecentGildedCard);
                bjai.setEnemyHealth(enemy.getHealth());
                if (enemy.getHealth() < 0) {
                    enemy.setHealth(0);
                }
                //enemyHealthText.setText("" + enemy.getHealth());
                winner = new TextElement("Player Wins!",64, 200, height/2);
                // 
                // wm.put(winner);
                // continueShowing = true;
                // wm.put(reset);
                enemy.getHand().get(0).flip();
                resetRound = true;
            }
            else {
                if (bjai.getEnemyTotal() > bjai.getPlayerTotal() && bjai.getEnemyTotal() <= 21) {
                    this.doActions(playerAction,cbai.getAction(), enemy.getAttack(), mostRecentGildedCard);
                    bjai.setPlayerHealth(player.getHealth());
                    if (player.getHealth() < 0) {
                        player.setHealth(0);
                    }
                    //playerHealthText.setText("" + player.getHealth());
                    winner = new TextElement("Enemy Wins!",64, 200, height/2);
                    //
                    // wm.put(winner);
                    // continueShowing = true;
                    // wm.put(reset);
                    enemy.getHand().get(0).flip();
                    resetRound = true;
                }
                else if (bjai.getEnemyTotal() < bjai.getPlayerTotal() && bjai.getPlayerTotal() <= 21) {
                    this.doActions(playerAction,cbai.getAction(), player.getAttack(), mostRecentGildedCard);
                    bjai.setEnemyHealth(enemy.getHealth());
                    if (enemy.getHealth() < 0) {
                        enemy.setHealth(0);
                    }
                    //enemyHealthText.setText("" + enemy.getHealth());
                    winner = new TextElement("Player Wins!",64, 200, height/2);
                    //
                    // wm.put(winner);
                    // continueShowing = true;
                    // wm.put(reset);
                    enemy.getHand().get(0).flip();
                    resetRound = true;
                }
                else {
                    winner = new TextElement("Draw!",64, 200, height/2);
                    //

                    enemy.getHand().get(0).flip();
                    resetRound = true;
                }
               
            }
        }
        else if (bjai.getPlayerTotal() > 21) {
            this.doActions(playerAction,cbai.getAction(), enemy.getAttack(), mostRecentGildedCard);
            bjai.setPlayerHealth(player.getHealth());
            if (player.getHealth() < 0) {
                player.setHealth(0);
            }
            //playerHealthText.setText("" + player.getHealth());
            winner = new TextElement("Enemy Wins!",64, 200, height/2);
            hideHitStay();
            //
            wm.put(winner);
            continueShowing = true;
            this.updateHealth();
            wm.put(reset);
            enemy.getHand().get(0).flip();
            resetRound = true;
        }
    }

    public void updateHealth(){
        playerHealthText.setText("" + player.getHealth());
        enemyHealthText.setText("" + enemy.getHealth());
    }

    public void doActions(String playerAction, String EnemyAction, int Attack, Card c) {

        double playerOffenseModifier = 1.0;
        double playerDefenseModifier = 1.0;
        double enemyOffenseModifier = 1.0;
        double enemyDefenseModifier = 1.0;

        int playerTotal = cbai.getPlayerTotal();
        int enemyTotal = cbai.getEnemyTotal();

        if (playerAction == "Attack") {
            playerOffenseModifier = 1.25;
            playerDefenseModifier = 1.25;
        }
        if (playerAction == "Block") {
            playerOffenseModifier = 0.75;
            playerDefenseModifier = 0.75;
        }

        if (EnemyAction == "Attack") {
            enemyOffenseModifier = 1.25;
            enemyDefenseModifier = 1.25;
        }
        if (EnemyAction == "Block") {
            enemyOffenseModifier = 0.75;
            enemyDefenseModifier = 0.75;
        }
        if(c != null){
            Effect e = c.getEffect();
            switch(e.getEffect()){
                case DAMAGE_MODIFIER:
                    playerOffenseModifier += e.getValue();
                    c.activate();
                    break;
                case HEAL:
                    break;
                case DEFENSE_BONUS:
                    playerDefenseModifier -= Math.max(0.25, e.getValue());
                    c.activate();
                    break;
                case INSTANT_DAMAGE:
                    break;
                default:
                    break;
                
            }
        }

        if ((playerTotal > enemyTotal  && playerTotal <= 21 || enemyTotal > 21) || (player.getHand().size() == 5 && playerTotal <= 21)) {
            int attack = Attack;

            attack = (int)(attack * playerOffenseModifier * enemyDefenseModifier);

            enemy.setHealth((enemy.getHealth() - (int)attack));
        }
        else if ((playerTotal < enemyTotal && enemyTotal <= 21 || playerTotal > 21) || (enemy.getHand().size() == 5 && enemyTotal <= 21)) {
            int attack = Attack;

            attack = (int)(attack * enemyOffenseModifier * playerDefenseModifier);

            player.setHealth(player.getHealth() - (int)attack);
        }
    } 



    private void hidePlayerOptions() {
        optionsShowing = false;
        wm.remove(attack);
        wm.remove(block);
        wm.remove(nothing);
    }

    private void showPlayerOptions() {
        optionsShowing = true;
        wm.put(attack);
        wm.put(block);
        wm.put(nothing);
    }

    private void showHitStay() {
        hitStayShowing = true;
        wm.put(hit);
        wm.put(stay);
    }

    private void hideHitStay() {
        hitStayShowing = false;
        wm.remove(hit);
        wm.remove(stay);
    }

    private void animPlayerCard(){
        if(animatePlayerDrawnCard){
            playerDrawnCard.setPos(playerDrawnCard.getX(), playerDrawnCard.getY() - 100);
        }else{
           animatePlayerDrawnCard = true;
        }
    }

    private void animEnemyCard(){
        if(animateEnemyDrawnCard){
            enemyDrawnCard.setPos(enemyDrawnCard.getX(), enemyDrawnCard.getY() + 100);
        }else{
            animateEnemyDrawnCard = true;
        }
    }

}


