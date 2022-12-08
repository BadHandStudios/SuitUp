package com.badhand.suitup.entities;

import com.badhand.suitup.game.Suit;
import com.badhand.suitup.ui.Card;
import com.badhand.suitup.ui.WindowManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BasicBJAITest {
    static BasicBJAI bjai = new BasicBJAI();
    @Test
    void hitTest() {
        String msg = "Pass if enemy stays on a 17 or greater";
        ArrayList<Card> testHand = new ArrayList<>();
        testHand.add(new Card(Suit.CLUBS, 10, 0,0,250,350));
        testHand.add(new Card(Suit.CLUBS, 6, 0,0,250,350));
        bjai.setEnemyHand(testHand);

        assertTrue(bjai.hit(), msg);

        testHand.clear();
        testHand.add(new Card(Suit.CLUBS, 10, 0,0,250,350));
        testHand.add(new Card(Suit.CLUBS, 7, 0,0,250,350));

        assertFalse(bjai.hit(), msg);
    }
    @BeforeAll
    static void setUp(){
        WindowManager wm = WindowManager.getInstance();
        wm.createWindow(1920,1080);
        while(!wm.isReady()){}
    }

    @AfterAll
    static void tearDown(){

//        WindowManager.getInstance().destroyWindow();
    }
}