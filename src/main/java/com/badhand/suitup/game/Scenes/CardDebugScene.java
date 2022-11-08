package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

import java.util.*;

public class CardDebugScene implements Scene {

    private static WindowManager wm = WindowManager.getInstance();
    private static EventManager em = EventManager.getInstance();

    public CardDebugScene(){}

    public void initialize(){
        wm.clear();

        // Create a back button in the top left corner
        TextButton back = new TextButton("Back", 50, 100, 100, new Event(Events.SCENE_CHANGE, GameState.MENU_MAIN));
        wm.put(back);

        Card c = new Card(Suit.CLUBS, 5, 512, 512);
        wm.put(c);
        Card d = new Card(Suit.DIAMONDS, 10, 512+251, 512);
        wm.put(d);
    }

    public void update(){

    }

    public void handle(Event e){

    }

}