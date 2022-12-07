package com.badhand.suitup.game;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.entities.*;
import com.badhand.suitup.ui.WindowManager;

import processing.core.PImage;

public class EnemyFactory {
    private static EnemyFactory instance;

    private static WindowManager wm = WindowManager.getInstance();
    
    private int level = 1;

    private final int ENEMY_TEXTURE_FILES = 5;
    
    private static ShuffleBag<PImage> enemyTextures = new ShuffleBag<PImage>();
    private static ShuffleBag<BlackJackAI> enemyAI = new ShuffleBag<BlackJackAI>();
    private static ShuffleBag<String> enemyNames = new ShuffleBag<String>();
    
    private EnemyFactory(){
        
        loadAI(1);
        loadNames();
    }

    private AssetManager am = AssetManager.getInstance();

    public static EnemyFactory getInstance(){
        if(instance == null) instance = new EnemyFactory();
        return instance;
    }

    public Enemy getEnemy(int episode, int level){
        if(enemyTextures.empty()) loadTextures();
        if(this.level != level){
            loadAI(level);  
        } 


       
        BlackJackAI ai = enemyAI.next();
        PImage texture = enemyTextures.next();
        String name = enemyNames.next();
        int health = 15 + 5 * level;
        int attack = 4 + level;

        // switch(episode){
        //     case 2:
        //         health = 25 + 5 * level;
        //         attack = 5 + level;
        //         break;
        //     default:
        // }

        Enemy e = new Enemy(texture, name, health, attack, 0, 0, ai, new BasicCBAI());
        wm.registerDiffered(e);
        return e;
    }

    private void loadTextures(){
        for(int i = 1; i <= ENEMY_TEXTURE_FILES; i++){
            enemyTextures.add(am.getImage("enemy" + i + ".png"));
        }
    }

    private void loadAI(int level){
        this.level = level;
        enemyAI.clear();
        switch(level){
            case 1:
                enemyAI.add(new RandomBJAI());
                enemyAI.add(new SmartRandomAI(), 2);
                enemyAI.add(new BasicBJAI());
                enemyAI.add(new ByTheBooksAI());
                enemyAI.add(new SmartBJAI());
                break;
            case 2:
                enemyAI.add(new RandomBJAI());
                enemyAI.add(new SmartRandomAI(), 2);
                enemyAI.add(new BasicBJAI(), 3);
                enemyAI.add(new ByTheBooksAI());
                enemyAI.add(new SmartBJAI());
                break;
            case 3:
                enemyAI.add(new SmartRandomAI());
                enemyAI.add(new BasicBJAI(),2);
                enemyAI.add(new ByTheBooksAI(), 4);
                enemyAI.add(new SmartBJAI(), 2);
                break;
            default:

        }
        
    }

    private void loadNames(){
        final String[] names = {
            "Cardamom",
            "Jack Jr",
            "Clarence",
            "The Duke",
            "Magician's Nephew's Son",
            "Father Phil",
            "Flipper",
            "Chipper",
            "Buster"
        };

        for(String n : names) enemyNames.add(n);
    }

}
