package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;
import com.badhand.suitup.events.*;
import com.badhand.suitup.assets.*;

import processing.core.PGraphics;


public class MenuLevelSelect implements Scene {

    private static WindowManager wm = WindowManager.getInstance();
    private static GameManager gm = GameManager.getInstance();
    private static DataManager dm = DataManager.getInstance();

    private GraphicsWrapper bg;
    private TextButton back;
    private TextElement title, episodeTitle, desc;
    private ImageElement award;
    
    private String[] episodes = {
        "The Base Experience, fight your way through each level to reach the boss",
        "\nThe Gilded Age approaches! All cards are pre-guilded, \nbut enemies are stronger!",
    };

    private boolean[] beaten = new boolean[episodes.length];

    private int selected = 0 ;

    public void initialize() {
        wm.clear();

        PGraphics levelSelect = wm.newGraphic(800, 500);
        levelSelect.beginDraw();
        levelSelect.fill(243);
        levelSelect.stroke(0);
        levelSelect.rect(0, 0, 800, 500, 10);
        levelSelect.endDraw();
        
        // Create a background to display level information
        bg = new GraphicsWrapper(levelSelect, 1920/2, 1080/2);
        wm.put(bg);

        // Create a back button in the top left corner
        back = new TextButton("Back", 50, 100, 100, new Event(Events.SCENE_CHANGE, GameState.MENU_MAIN));
        wm.put(back);

        // Create a title
        title = new TextElement("Episodes", 100, 1920/2, 100);
        wm.put(title);

        // Create a title for the episode
        episodeTitle = new TextElement("Episode I", 50, 1920/2, 500);
        episodeTitle.setColor(new Color(0));
        wm.put(episodeTitle);

        // Create a description for the episode
        desc = new TextElement(episodes[0], 30, 1920/2, 550);
        desc.setColor(new Color(0));
        wm.put(desc);
        

        // Create a next button
        TextButton next = new TextButton(" > ", 64, 1920 - 200, 1080/2 , new Event(Events.SCENE_EVENT, "next"));
        wm.put(next);

        // Create a previous button
        TextButton prev = new TextButton(" < ", 64, 200, 1080/2 , new Event(Events.SCENE_EVENT, "prev"));
        wm.put(prev);

        // Create a play button
        TextButton play = new TextButton("Play", 64, 1920/2, 1080 - 200, new Event(Events.SCENE_CHANGE, GameState.MAP_SCENE));
        wm.put(play);

        // Create an award icon for beating the episode
        award = new ImageElement(1920/2, 1080 - 300, 64, 64, "award.png");
        wm.put(award);

        for(int i = 0; i < beaten.length; i++){
            int beatenEpisode = dm.getInt("beat_episode_" + (i+1));
            if(beatenEpisode == 1){
                beaten[i] = true;
            }
        }

        if(beaten[selected]){
            award.setVisibility(true);
        } else {
            award.setVisibility(false);
        }

        
    }

    public void update() {
        
    }

    public void handle(Event e) {
        switch(e.getType()){
            case SCENE_EVENT:
                switch((String)e.getData()){
                    case "next":
                        selected++;
                        if(selected >= episodes.length) selected--;
                        break;
                    case "prev":
                        selected--;
                        if(selected < 0) selected = episodes.length-1;
                        break;
                }
                desc.setText(episodes[selected]);
                switch(selected){
                    case 0:
                        gm.setEpisode(1);
                        episodeTitle.setText("Episode I");
                        break;
                    case 1:
                        gm.setEpisode(2);
                        episodeTitle.setText("Episode II");
                        break;
                }
                if(beaten[selected]){
                    award.setVisibility(true);
                } else {
                    award.setVisibility(false);
                }
                break;
        }
    }
}
