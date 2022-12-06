package com.badhand.suitup.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
            else{
                //System.out.println("Card did not match: " + curr);
            }
            i++;
        }

        assertEquals(deckSize, matches, msg);
    }

    @Test
    void cardsLeftTest() {
        String msg = "Pass if drawing a card removes one from the deck.";
        int initialSize = _deck.cardsLeft();
        int expected = initialSize - 1;
        _deck.draw();
        assertEquals(expected, _deck.cardsLeft());
    }
    
    @Test
    void drawAllTest(){
        String msg = "Pass if drawing all cards leaves 0 cards in the deck";
        int initialSize = _deck.cardsLeft();
        //TODO: Draw all cards and check if there are none left in deck.
    }

    @Test
    void testDraw() {

    }

    @Test
    void testGetCard() {
        //TODO
    }

    @Test
    void testPeek() {
        //TODO
    }

    @Test
    void testShuffle() {
        //TODO
    }

    @BeforeAll
    public static void Setup() throws IOException
    {
        _cardNames = new ArrayList<String>();
        wm = WindowManager.getInstance();
        wm.createWindow(256,256);
        String filename = "validcards.txt";
        //TODO: Read in validcards.txt, compare each.
        BufferedReader br = new BufferedReader(new FileReader(filename));

        while(br.ready()){
            String cl = br.readLine();
            _cardNames.add(cl);
        }
    }

    @BeforeEach
    public void BeforeEach(){
        _deck = new Deck();
    }
}
