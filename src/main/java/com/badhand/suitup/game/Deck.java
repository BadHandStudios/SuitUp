package com.badhand.suitup.game;

import com.badhand.suitup.ui.*;

public class Deck {
    private ShuffleBag<Card> cards;
    private int cardsLeft = 52;

    public Deck(){
        cards = new ShuffleBag<Card>();
        for(int i = 0; i < 4; i++){
            for(int j = 1; j <= 13; j++){
                cards.add(new Card(Suit.values()[i], j, 0, 0, 200, 300));
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
        return cardsLeft == 0 ? null : cards.next();
    }

    public void shuffle(){
        cardsLeft = 52;
        cards.reshuffle();
    }

    public Card getCard(String name){
        return cards.search(name);
    }

}



