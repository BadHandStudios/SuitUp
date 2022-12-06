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

}



