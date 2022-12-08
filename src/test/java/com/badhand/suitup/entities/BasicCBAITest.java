package com.badhand.suitup.entities;

import com.badhand.suitup.game.Effect;
import com.badhand.suitup.game.Effects;
import com.badhand.suitup.game.Suit;
import com.badhand.suitup.ui.Card;
import com.badhand.suitup.ui.Window;
import com.badhand.suitup.ui.WindowManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasicCBAITest {
    BasicCBAI testAI;
    @Test
    void getActionTest() {
        String msg = "Pass if action is a valid action";
        ArrayList<String> validActions = new ArrayList<>();
        validActions.add("Attack");
        validActions.add("Block");
        validActions.add("Nothing");

        for(int i = 0; i < validActions.size(); i++){
            String targetAction = validActions.get(i);
            String testAction = testAI.getAction();
            while(!targetAction.equals(testAction)){
                testAction = testAI.getAction();
            }
            assertTrue(validActions.contains(testAction), msg);
        }
    }

//    @Test
//    void doActionsTest() {
//        String msg = "Pass if player attack lowers enemy health";
//        ArrayList<String> validActions = new ArrayList<>();
//        validActions.add("Attack");
//        validActions.add("Block");
//        Card testCard;
//        //testCard.gild();
//        ArrayList<Card> winnerHand = new ArrayList<>();
//        testCard = new Card(Suit.CLUBS, 1, 0,0,250,350);
//        winnerHand.add(testCard);
//        testCard = new Card(Suit.CLUBS, 10,0, 0,250,350);
//        winnerHand.add(testCard);
//
//        ArrayList<Card> loserHand = new ArrayList<>();
//        testCard = new Card(Suit.CLUBS, 2, 0,0,250,350);
//        loserHand.add(testCard);
//        testCard = new Card(Suit.CLUBS, 10,0, 0,250,350);
//        loserHand.add(testCard);
//
//        testAI.setEnemyHand(loserHand);
//        testAI.setPlayerHand(winnerHand);
//
//        int initialEnemyHealth = testAI.getEnemyHealth();
//        for(int i = 0; i < validActions.size(); i++){
//            for (int j = 0; j < validActions.size(); j++){
//                testAI.doActions(validActions.get(i), validActions.get(j), 1, null);
//                assertTrue(initialEnemyHealth > testAI.enemyHealth, msg);
//            }
//        }
//
//        msg = "Pass if enemy attack lowers player health";
//        testAI.setEnemyHand(winnerHand);
//        testAI.setPlayerHand(loserHand);
//
//        int initialPlayerHealth = testAI.getPlayerHealth();
//        for(int i = 0; i < validActions.size(); i++){
//            for (int j = 0; j < validActions.size(); j++){
//                testAI.doActions(validActions.get(i), validActions.get(j), 1, null);
//                assertTrue(initialPlayerHealth > testAI.playerHealth, msg);
//            }
//        }
//
//        //There is no setEffect, so randomly gild a card until we get each effect.
//        testCard = new Card(Suit.CLUBS, 2, 0,0,250,350);
//        testCard.gild();
//        for(int i = 0; i < Effects.values().length; i++){
//            Effects effect = Effects.values()[i];
//            String testCardEffectName = testCard.getEffect().getEffect().name();
//            String searchEffectName = effect.name();
//            //System.out.println("rolling for " + searchEffectName);
//            while(!testCardEffectName.equals(effect.name())){
//                testCard = new Card(Suit.CLUBS, 2, 0,0,250,350);
//                testCard.gild();
//                testCardEffectName = testCard.getEffect().getEffect().name();
//                //System.out.println("got " + testCardEffectName);
//            }
//            testAI.doActions("Attack", "Attack", 1, testCard);
//            assertEquals(effect, testCard.getEffect().getEffect(), msg);
//        }
//    }


    @BeforeAll
    static void setUp(){
        WindowManager wm = WindowManager.getInstance();
        wm.createWindow(1920,1080);
        while(!wm.isReady()){}
    }
    @BeforeEach
    void beforeEach(){
        testAI = new BasicCBAI(){};

    }
    @AfterAll
    static void tearDown(){
//        WindowManager.getInstance().destroyWindow();
    }
}