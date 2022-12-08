package com.badhand.suitup.entities;

import com.badhand.suitup.game.Deck;
import com.badhand.suitup.game.Suit;
import com.badhand.suitup.ui.Card;
import com.badhand.suitup.ui.WindowManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    static Entity testEntity;
    @Test
    void drawCardTest(){
        String msg = "Pass if drawCard returns a card";
        Deck testDeck = new Deck();
        Card testCard = testDeck.peek();
        testEntity.setDeck(testDeck);
        testEntity.setHand(new ArrayList<Card>());
        Card actualCard = testEntity.drawCard();
        assertEquals(testCard, actualCard, msg);

        msg = "Pass if drawn card is null";
        actualCard = testEntity.drawCard();
        assertNull(actualCard, msg);
    }
    @Test
    void addMaxHealthTest(){
        String msg = "Pass if addMaxHealth successfully adds max health";
        int initialMax = testEntity.getMaxHealth();
        int additionalMax = 10;
        int expectedMax = initialMax + additionalMax;
        testEntity.addMaxHealth(additionalMax);
        assertEquals(expectedMax,testEntity.getMaxHealth(),msg);
    }
    @Test
    void setMaxHealthTest(){
        String msg = "Pass if setMaxHealth successfully sets max health";
        int initialMax = testEntity.getMaxHealth();
        int additionalMax = 10;
        int expectedMax = initialMax + additionalMax;
        testEntity.setMaxHealth(testEntity.getMaxHealth() + additionalMax);
        assertEquals(expectedMax,testEntity.getMaxHealth(),msg);
    }
    @Test
    void addHealthTest(){
        String msg = "Pass if addHealth successfully adds health";
        int initialHealth = 3;
        testEntity.setHealth(initialHealth);
        int additionalHealth = 10;
        int expected = initialHealth + additionalHealth;
        testEntity.addHealth(additionalHealth);
        assertEquals(expected, testEntity.getHealth(), msg);
    }
    @Test
    void positionTest(){
        String msg = "Pass if setPos correctly sets x and y";
        int expectedX = 3;
        int expectedY = 2;

        testEntity.setPos(3,2);

        assertEquals(expectedX,testEntity.getX(),msg);
        assertEquals(expectedY,testEntity.getY(),msg);
    }
    @Test
    void getHandTotalTest() {
        int expected = 7;
        ArrayList<Card> testHand = new ArrayList<Card>();
        testEntity.setHand(testHand);
        testHand.add(new Card(Suit.SPADES, 3, 0,0,250,350));
        testHand.add(new Card(Suit.SPADES, 4, 0,0,250,350));
        assertEquals(expected,testEntity.getHandTotal());

        expected = 22;
        testHand.add(new Card(Suit.SPADES, 7, 0,0,250,350));
        testHand.add(new Card(Suit.SPADES, 8, 0,0,250,350));

        assertEquals(expected, testEntity.getHandTotal());

        expected = 23;
        testHand.add(new Card(Suit.SPADES, 1, 0,0,250,350));
        assertEquals(expected, testEntity.getHandTotal());
    }

    @BeforeAll
    static void setUp(){
        WindowManager wm = WindowManager.getInstance();
        wm.createWindow(1920,1080);
        while (!wm.isReady()){}
        testEntity = Player.getInstance();
    }
    @AfterAll
    static void tearDown(){
//        WindowManager.getInstance().destroyWindow();
    }
}