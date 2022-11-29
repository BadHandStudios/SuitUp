package com.badhand.suitup.ui;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.*;
import com.badhand.suitup.game.Suit;

@DisplayName("Card Tests")
public class CardTest
{  
    private static WindowManager wm;
    //private static AssetManager am;
    //private static GameManager gm;

    public static int cardValue = 11;
    public static Suit cardSuit = Suit.CLUBS;
    private static Card _testCard;

    @DisplayName("Get Value Test")
    @Test
    public void getValueTest() 
    {
        String msg = "Expected card value vs. card's getValue().";
        int expected = cardValue;
        if(expected == 1){
            expected = 11;
        }
        else if(expected > 10){
            expected = 10;
        }
        int actual = _testCard.getValue();
        assertEquals(msg, expected, actual);
    }

    @DisplayName("Suit Name Test")
    @Test
    public void suitNameTest()
    {
        String msg = "Expected card suit vs. card's suitName().";
        String expected = "ERROR";
        String actual = _testCard.suitName();
        switch(cardSuit){
            case CLUBS:
                expected = "Clubs";
                break;
            case DIAMONDS:
                expected = "Dmnds";
                break;
            case HEARTS:
                expected = "Hearts";
                break;
            case SPADES:
                expected = "Spades";
                break;
        }
        assertEquals(msg, expected, actual);
    }

    @BeforeAll
    public static void Setup()
    {
        wm = WindowManager.getInstance();
        wm.createWindow(250, 350);
        while(!wm.isReady()){};
        _testCard = new Card(cardSuit, cardValue, 0, 0, 250, 250);
    }

    @AfterAll
    public static void TearDown()
    {
        wm.destroyWindow();
    }
}
