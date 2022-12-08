package com.badhand.suitup.entities;

import com.badhand.suitup.game.Suit;
import com.badhand.suitup.ui.Card;
import com.badhand.suitup.ui.WindowManager;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ByTheBooksAITest {
    static ByTheBooksAI byTheBooksAI;
    @Test
    void hit() {
        String msg = "Pass if the AI hits because its total is < 11";
        ArrayList<Card> enemyHand = new ArrayList<Card>();
        ArrayList<Card> playerHand = new ArrayList<Card>();
        enemyHand.add(new Card(Suit.SPADES, 10, 0,0,250,350));
        playerHand.add(new Card(Suit.SPADES, 2, 0,0,250,350));
        playerHand.add(new Card(Suit.SPADES, 4, 0,0,250,350));
        byTheBooksAI.setEnemyHand(enemyHand);
        byTheBooksAI.setPlayerHand(playerHand);

        assertTrue(byTheBooksAI.hit(),msg);

        enemyHand.add(new Card(Suit.SPADES, 2, 0,0,250,350));
        msg = "Pass if the AI stays because its total is > 12 and the player total is <= 6";
        assertFalse(byTheBooksAI.hit(),msg);

        playerHand.add(new Card(Suit.SPADES, 2, 0,0,250,350));
        msg = "Pass if the AI hits because its total is < 17 and the player total is > 6";
        assertTrue(byTheBooksAI.hit(),msg);
    }

    @BeforeAll
    static void setUp(){
        byTheBooksAI = new ByTheBooksAI();
        WindowManager wm = WindowManager.getInstance();
        wm.createWindow(1920,1080);
        while(!wm.isReady()){}
    }
}