package com.badhand.suitup.entities;

import com.badhand.suitup.game.Suit;
import com.badhand.suitup.ui.Card;
import com.badhand.suitup.ui.WindowManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RandomBJAITest {
    static RandomBJAI randomBJAI;
    @Test
    void hitTest() {
        String msg = "Pass if stay on a 21";
        ArrayList<Card> testHand = new ArrayList<Card>();
        randomBJAI.setEnemyHand(testHand);
        testHand.add(new Card(Suit.CLUBS, 1, 0,0,250,350));
        testHand.add(new Card(Suit.CLUBS, 10, 0,0,250,350));
        assertFalse(randomBJAI.hit(),msg);

        msg = "Always pass. Either result is valid.";
        testHand.clear();
        boolean hit = randomBJAI.hit();
        assertTrue(hit == true || hit == false , msg);
    }

    @BeforeAll
    static void setUp(){
        WindowManager wm = WindowManager.getInstance();
        wm.createWindow(1920,1080);
        while(!wm.isReady()){}
        randomBJAI = new RandomBJAI();
    }
    @AfterAll
    static void tearDown(){
//        WindowManager.getInstance().destroyWindow();
    }
}