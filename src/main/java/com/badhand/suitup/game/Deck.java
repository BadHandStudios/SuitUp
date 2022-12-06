package com.badhand.suitup.game;

import com.badhand.suitup.ui.*;

import java.util.*;

public class Deck {
    private ShuffleBag<Card> cards;
    private ArrayList<Card> gildedCards;
    private int cardsLeft = 52;

    public Deck(){
        gildedCards = new ArrayList<Card>();
        cards = new ShuffleBag<Card>();
        for(int i = 0; i < 4; i++){
            for(int j = 1; j <= 13; j++){
                Card c = new Card(Suit.values()[i], j, 0, 0, 200, 300);
                c.registerDeck(this);
                cards.add(c);
            }
        }
    }

    public int cardsLeft(){
        return cardsLeft;
    }

    public Card peek(){
        return cards.peek();
    }

    public Card draw(){
        cardsLeft--;
        if(cardsLeft == 0) shuffle();
        Card c = cards.next();
        c.deactivate();
        return c;
    }

    public void shuffle(){
        cardsLeft = 52;
        cards.reshuffle();
    }

    public Card getCard(String name){
        return cards.search(name);
    }

    public int numGilded(){
        return gildedCards.size();
    }

    public void registerGilded(Card c){
        this.gildedCards.add(c);
    }

}



