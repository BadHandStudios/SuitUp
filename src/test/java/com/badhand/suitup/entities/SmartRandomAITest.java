package com.badhand.suitup.entities;

import com.badhand.suitup.game.Suit;
import com.badhand.suitup.ui.Card;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SmartRandomAITest {
    static SmartRandomAI smartRandomAI;
    @Test
    void hitTest() {
        String msg = "Pass if AI does not hit";
        ArrayList<Card> testHand = new ArrayList<Card>();
        testHand.add(new Card(Suit.HEARTS, 10, 0, 0, 250, 350));
        testHand.add(new Card(Suit.HEARTS, 6, 0, 0, 250, 350));
        smartRandomAI.setEnemyHand(testHand);
        assertFalse(smartRandomAI.hit(), msg);
    }

    @BeforeAll
    static void setUp(){
        smartRandomAI = new SmartRandomAI();
    }
}