package com.badhand.suitup.ui;

import org.junit.BeforeClass;
//import org.apache.commons.lang3.ObjectUtils.Null;
import org.junit.jupiter.api.*;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;

//import com.badhand.suitup.*;
import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.game.GameManager;
import com.badhand.suitup.game.Suit;

import junit.framework.TestCase;

public class CardTest extends TestCase
{  
    private static WindowManager wm;
    //private static AssetManager am;
    //private static GameManager gm;

    public static int cardValue = 5;
    public static Suit cardSuit = Suit.SPADES;
    private static Card _testCard;

    @Test
    public void getValueTest() 
    {
        String msg = "Expected card value vs. card's getValue() result";
        int expected = cardValue;
        int actual = _testCard.getValue();
        assertEquals(msg, expected, actual);
    }

    @Test
    public void suitNameTest()
    {
        String msg = "Expected card suit vs. card's suitName() result";
        String expected = "ERROR";
        String actual = _testCard.suitName();
        switch(cardSuit){
            case CLUBS:
                expected = "Clubs";
            case DIAMONDS:
                expected = "Dmnds";
            case HEARTS:
                expected = "Hearts";
            case SPADES:
                expected =  "Spades";
        }
        assertEquals(msg, expected, actual);
    }

    @BeforeAll
    public static void Init()
    {
        //System.out.print("BeforeAll start\n");

        wm = WindowManager.getInstance();

        wm.createWindow(250, 350);

        //am = AssetManager.getInstance();

        while(!wm.isReady()){}

        //gm = GameManager.getInstance();

        _testCard = new Card(cardSuit, cardValue, 0, 0, 250, 250);
        
        // System.out.print("BeforeAll: "+ _testCard+"\n");
    }
}
