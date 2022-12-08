package com.badhand.suitup.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badhand.suitup.ui.Card;
import com.badhand.suitup.ui.WindowManager;

public class DeckTest {

    private static WindowManager wm;
    private static ArrayList<String> _cardNames;
    private static Deck _deck;

    @Test
    void createDeckTest(){
        String msg = "Pass if deck is correct no. of valid cards";

        int matches = 0;
        int deckSize = _deck.cardsLeft();
        int i = 0;
        while(i < deckSize){
            Card curr = _deck.draw();
            //System.out.println("Drew card no. " + i + ": " + curr);
            if (curr == null){
                // System.out.println("curr is null at iteration number " + i);
            }

            if(_cardNames.contains(curr.toString())){
                matches++;
            }
            else {
            }
            i++;
        }

        assertEquals(deckSize, matches, msg);
    }

    @Test
    void cardsLeftTest() {

    }
    
    @Test
    void drawAllTest(){
        String msg = "Pass if drawing all cards refills the deck";
        int initialSize = _deck.cardsLeft();
        int expected = initialSize;
        for(int i = 0; i < initialSize; i++){
            _deck.draw();
        }
        int actual = _deck.cardsLeft();
        assertEquals(expected, actual, msg);
    }

    @Test
    void drawTest() {
        String msg = "Pass if drawing a card removes one from the deck.";
        int initialSize = _deck.cardsLeft();
        int expected = initialSize - 1;
        _deck.draw();
        assertEquals(expected, _deck.cardsLeft());
    }

    @Test
    void getCardTest() {
        String msg = "Pass if cards exist";
        int initialSize = _deck.cardsLeft();
        Card curr;
        for(int i = 0; i < initialSize; i++)
        {
            curr = _deck.getCard(_cardNames.get(i));
            assertNotNull(curr, msg);
        }
    }

    @Test
    void peekTest() {
    	String msg = "Pass if all cards correctly peek";
    	int initialSize = _deck.cardsLeft();
    	for(int i = 0; i < initialSize; i++) {
    		Card curr = _deck.peek();
    		_deck.draw();
    		assertNotNull(curr);
    	}
    }

    @Test
    void numGildedTest(){
        String msg = "Pass if registerGilded succeeds in incrementing numGilded";
        int initialSize = _deck.cardsLeft();
        int initialGilded = _deck.numGilded();
        int totalGilded = 0;
        for(int i = 0; i < initialSize; i++){
            Card curr = _deck.draw();
            _deck.registerGilded(curr);
            totalGilded++;
            assertEquals(_deck.numGilded(), totalGilded, msg);
        }
        msg = "Pass if all cards were gilded successfully";
        assertEquals(_deck.numGilded(), initialSize, msg);
    }

    @BeforeAll
    public static void Setup() throws IOException
    {
        _cardNames = new ArrayList<String>();
        wm = WindowManager.getInstance();
        wm.createWindow(256,256);
        String filename = "validcards.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));

        while(br.ready()){
            String cl = br.readLine();
            _cardNames.add(cl);
        }
        
        br.close();
    }

    @BeforeEach
    public void BeforeEach(){
        _deck = new Deck();
    }
}
