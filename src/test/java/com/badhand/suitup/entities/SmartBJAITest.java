package com.badhand.suitup.entities;

import com.badhand.suitup.game.Suit;
import com.badhand.suitup.ui.Card;
import com.badhand.suitup.ui.Window;
import com.badhand.suitup.ui.WindowManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SmartBJAITest {
    static SmartBJAI smartBJAI;
    @Test
    void hit() {
        String msg = "Pass if hits when enemy total < player total";
        ArrayList<Card> lowHand = new ArrayList<Card>();
        lowHand.add(new Card(Suit.DIAMONDS, 2, 0, 0, 250, 350));
        ArrayList<Card> highHand = new ArrayList<Card>();
        highHand.add(new Card(Suit.DIAMONDS, 10, 0, 0, 250, 350));
        smartBJAI.setEnemyHand(lowHand);
        smartBJAI.setPlayerHand(highHand);
        assertTrue(smartBJAI.hit(),msg);

    }

    @BeforeAll
    static void setUp(){
        smartBJAI = new SmartBJAI();
        WindowManager wm = WindowManager.getInstance();
        wm.createWindow(1920,1080);
        while(!wm.isReady()){}
    }
}