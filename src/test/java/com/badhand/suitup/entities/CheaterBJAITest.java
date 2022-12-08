package com.badhand.suitup.entities;

import com.badhand.suitup.game.Deck;
import com.badhand.suitup.game.Suit;
import com.badhand.suitup.ui.Card;
import com.badhand.suitup.ui.WindowManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CheaterBJAITest {

    static CheaterBJAI cheaterBJAI;
    @Test
    void hitTest() {
        String msg = "Pass if AI hits when total <= 21";

        Deck testDeck = new Deck();
        ArrayList<Card> testHand = new ArrayList<>();
        testHand.add(new Card(Suit.CLUBS, 5, 0, 0, 250, 350));
        testHand.add(new Card(Suit.CLUBS, 6, 0, 0, 250, 350));
        cheaterBJAI.setEnemyDeck(testDeck);
        cheaterBJAI.setEnemyHand(testHand);
        ArrayList<Card> newHand = testHand;
        newHand.add(cheaterBJAI.enemyDeck.peek());

        if(cheaterBJAI.getEnemyTotal() <= 21){
            assertTrue(cheaterBJAI.hit(), msg);
        }
        else
        {
            assertFalse(cheaterBJAI.hit(), msg);
        }
    }

    @BeforeAll
    static void setUp(){
        WindowManager wm = WindowManager.getInstance();
        wm.createWindow(1920,1080);
        while(!wm.isReady()){}
        cheaterBJAI = new CheaterBJAI();
    }
}