package com.badhand.suitup.entities;

import com.badhand.suitup.game.Deck;
import com.badhand.suitup.game.Suit;
import com.badhand.suitup.ui.Card;
import com.badhand.suitup.ui.WindowManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AITest {
    static AI testAI;
    static WindowManager windowManager;
    @Test
    void setEnemyHandTest() {
        String msg = "Pass if successfully set enemy hand";
        testAI.setEnemyHand(new ArrayList<Card>());
        assertNotNull(testAI.enemyHand, msg);
    }

    @Test
    void setPlayerHandTest() {
        String msg = "Pass if successfully set player hand";
        testAI.setPlayerHand(new ArrayList<Card>());
        assertNotNull(testAI.playerHand, msg);
    }

    @Test
    void getEnemyHandTest() {
        String msg = "Pass if successfully got enemy hand";
        testAI.setEnemyHand(new ArrayList<Card>());
        assertNotNull(testAI.getEnemyHand(), msg);
    }

    @Test
    void getNullEnemyHandTest() {
        String msg = "Pass if null enemy hand";
        assertNull(testAI.getEnemyHand(), msg);
    }

    @Test
    void getPlayerHandTest() {
        String msg = "Pass if successfully got player hand";
        testAI.setPlayerHand(new ArrayList<Card>());
        assertNotNull(testAI.getPlayerHand(), msg);
    }

    @Test
    void getNullPlayerHandTest(){
        String msg = "Pass if null player hand";
        assertNull(testAI.getPlayerHand(), msg);
    }

    @Test
    void setEnemyDeckTest() {
        String msg = "Pass if successfully set enemy deck";
        testAI.setEnemyDeck(new Deck());
        assertNotNull(testAI.enemyDeck, msg);
    }

    @Test
    void getEnemyDeckTest() {
        String msg = "Pass if successfully got enemy deck";
        Deck testDeck = new Deck();
        testAI.setEnemyDeck(testDeck);
        assertEquals(testDeck, testAI.getEnemyDeck(testDeck), msg); //getEnemyDeck should not take argument
    }

    @Test
    void setPlayerDeckTest() {
        String msg = "Pass if successfully set player deck";
        testAI.setPlayerDeck(new Deck());
        assertNotNull(testAI.playerDeck, msg);
    }

    @Test
    void getPlayerDeckTest() {
        String msg = "Pass if successfully got player deck";
        Deck testDeck = new Deck();
        testAI.setPlayerDeck(testDeck);
        assertEquals(testDeck, testAI.getPlayerDeck(testDeck), msg);
    }

    @Test
    void setEnemyHealthTest() {
        String msg = "Pass if successfully set enemy health";
        int expected = 3;
        testAI.setEnemyHealth(expected);
        assertEquals(expected, testAI.enemyHealth, msg);

        expected = 0;
        testAI.setEnemyHealth(expected);
        assertEquals(expected, testAI.enemyHealth, msg);

        expected = 999;
        testAI.setEnemyHealth(expected);
        assertEquals(expected, testAI.enemyHealth, msg);
    }

    @Test
    void getEnemyHealthTest() {
        String msg = "Pass if successfully got enemy health";
        int expected = 3;
        testAI.enemyHealth = expected;
        assertEquals(expected, testAI.getEnemyHealth(), msg);

        expected = 0;
        testAI.enemyHealth = expected;
        assertEquals(expected, testAI.getEnemyHealth(), msg);

        expected = 999;
        testAI.enemyHealth = expected;
        assertEquals(expected, testAI.getEnemyHealth(), msg);
    }

    @Test
    void setPlayerHealthTest() {
        String msg = "Pass if successfully set player health";
        int expected = 3;
        testAI.setPlayerHealth(expected);
        assertEquals(expected, testAI.playerHealth, msg);

        expected = 0;
        testAI.setPlayerHealth(expected);
        assertEquals(expected, testAI.playerHealth, msg);

        expected = 999;
        testAI.setPlayerHealth(expected);
        assertEquals(expected, testAI.playerHealth, msg);
    }

    @Test
    void getPlayerHealthTest() {
        String msg = "Pass if successfully got player health";
        int expected = 3;
        testAI.playerHealth = expected;
        assertEquals(expected, testAI.getPlayerHealth(), msg);

        expected = 0;
        testAI.playerHealth = expected;
        assertEquals(expected, testAI.getPlayerHealth(), msg);

        expected = 999;
        testAI.playerHealth = expected;
        assertEquals(expected, testAI.getPlayerHealth(), msg);
    }

    @Test
    void getEnemyTotalTest() {
        String msg = "Pass if successfully got enemy total";
        int expected = 3;
        ArrayList<Card> testHand = new ArrayList<Card>();
        testHand.add(new Card(Suit.CLUBS, 3, 0, 0, 250, 350));
        testAI.setEnemyHand(testHand);
        assertEquals(expected, testAI.getEnemyTotal(), msg);

        testHand.clear();
        expected = 11;
        testHand.add(new Card(Suit.CLUBS, 1, 0, 0, 250, 350));
        assertEquals(expected, testAI.getEnemyTotal(), msg);

        testHand.clear();
        expected = 10;
        testHand.add(new Card(Suit.CLUBS, 12, 0, 0, 250, 350));
        assertEquals(expected, testAI.getEnemyTotal(), msg);
    }

    @Test
    void getPlayerTotalTest() {
        String msg = "Pass if successfully got player total";
        int expected = 3;
        ArrayList<Card> testHand = new ArrayList<Card>();
        testHand.add(new Card(Suit.CLUBS, 3, 0, 0, 250, 350));
        testAI.setPlayerHand(testHand);
        assertEquals(expected, testAI.getPlayerTotal(), msg);

        testHand.clear();
        expected = 11;
        testHand.add(new Card(Suit.CLUBS, 1, 0, 0, 250, 350));
        assertEquals(expected, testAI.getPlayerTotal(), msg);

        testHand.clear();
        expected = 10;
        testHand.add(new Card(Suit.CLUBS, 12, 0, 0, 250, 350));
        assertEquals(expected, testAI.getPlayerTotal(), msg);
    }

    @Test
    void updateTotalsTest() {
        String msg = "Pass if successfully updates player and enemy totals";
        int playerExpected = 3;
        int enemyExpected = 3;

        ArrayList<Card> testHand = new ArrayList<Card>();
        testHand.add(new Card(Suit.CLUBS, 3, 0, 0, 250, 350));

        testAI.setPlayerHand(testHand);
        testAI.setEnemyHand(testHand);

        testAI.updateTotals();

        assertEquals(playerExpected, testAI.playerTotal, msg);
        assertEquals(enemyExpected, testAI.enemyTotal, msg);

        playerExpected = 22;
        enemyExpected = 22;

        testHand.clear();

        testHand.add(new Card(Suit.CLUBS, 10, 0, 0, 250, 350));
        testHand.add(new Card(Suit.CLUBS, 10, 0, 0, 250, 350));
        testHand.add(new Card(Suit.CLUBS, 2, 0, 0, 250, 350));

        testAI.updateTotals();

        assertEquals(playerExpected, testAI.playerTotal, msg);
        assertEquals(enemyExpected, testAI.enemyTotal, msg);

        playerExpected = 13;
        enemyExpected = 13;

        testHand.clear();

        testHand.add(new Card(Suit.CLUBS, 10, 0, 0, 250, 350));
        testHand.add(new Card(Suit.CLUBS, 1, 0, 0, 250, 350));
        testHand.add(new Card(Suit.CLUBS, 2, 0, 0, 250, 350));

        testAI.updateTotals();

        assertEquals(playerExpected, testAI.playerTotal, msg);
        assertEquals(enemyExpected, testAI.enemyTotal, msg);
    }

    @Test
    void randomTest() {
        String msg = "Pass if random value is within range";
        int min = 1;
        int max = 2;
        int rand = testAI.random(min, max);
        assertTrue(rand >= min && rand <= max, msg);
    }

    @BeforeAll
    static void setUp(){
        windowManager = WindowManager.getInstance();
        windowManager.createWindow(1920,1080);
        while(!windowManager.isReady()){}
    }
    @BeforeEach
    void beforeEach(){
        testAI = new BlackJackAI() {
            @Override
            public boolean hit() {
                return false;
            }
        };
    }
    @AfterAll
    static void tearDown(){
//        WindowManager.getInstance().destroyWindow();
    }
}