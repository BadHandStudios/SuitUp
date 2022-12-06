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
import org.junit.jupiter.api.Test;

import com.badhand.suitup.ui.Card;
import com.badhand.suitup.ui.WindowManager;

public class DeckTest {

    private static WindowManager wm;
    private static ArrayList<String> _cardNames;
    private static Deck _deck;

    @Test
    void CreateDeckTest(){
        String msg = "Pass if deck is correct no. of valid cards";

        int matches = 0;
        int deckSize = _deck.cardsLeft();
        int i = 0;
        while(_deck.cardsLeft() > 0){
            Card curr = _deck.draw();
            System.out.println("drew card: " + curr);
            if (curr == null){
                // System.out.println("curr is null at iteration number " + i);
            }

            if(_cardNames.contains(curr.toString())){
                matches++;
            }
            else{
                System.out.println("Card did not match: " + curr);
            }
            i++;
        }

        assertEquals(deckSize, matches, msg);
    }

    @Test
    void testCardsLeft() {
        //??
    }

    @Test
    void testDraw() {
        //??
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
        _deck = new Deck();
        String filename = "validcards.txt";
        //TODO: Read in validcards.txt, compare each.
        BufferedReader br = new BufferedReader(new FileReader(filename));

        while(br.ready()){
            String cl = br.readLine();
            _cardNames.add(cl);
        }
    }
}
