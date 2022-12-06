package com.badhand.suitup.game;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.entities.BasicBJAI;
import com.badhand.suitup.entities.BasicCBAI;
import com.badhand.suitup.entities.BlackJackAI;
import com.badhand.suitup.entities.Enemy;
import com.badhand.suitup.entities.RandomBJAI;
import com.badhand.suitup.entities.SmartBJAI;
import com.badhand.suitup.ui.WindowManager;

import processing.core.PImage;

public class EnemyFactory {
    private static EnemyFactory instance;

    private static WindowManager wm = WindowManager.getInstance();
    
    private final int ENEMY_TEXTURE_FILES = 5;
    
    private static ShuffleBag<PImage> enemyTextures = new ShuffleBag<PImage>();
    private static ShuffleBag<BlackJackAI> enemyAI = new ShuffleBag<BlackJackAI>();
    private static ShuffleBag<String> enemyNames = new ShuffleBag<String>();
    
    private EnemyFactory(){
        loadTextures();
        loadAI();
        loadNames();
    }

    private AssetManager am = AssetManager.getInstance();

    public static EnemyFactory getInstance(){
        if(instance == null) instance = new EnemyFactory();
        return instance;
    }

    public Enemy getEnemy(int episode, int level){
        BlackJackAI ai = enemyAI.next();
        PImage texture = enemyTextures.next();
        String name = enemyNames.next();
        int health = 15 + 5 * level;
        int attack = 5 + level;
        Enemy e = new Enemy(texture, name, health, attack, 0, 0, ai, new BasicCBAI());
        wm.registerDiffered(e);
        return e;
    }

    private void loadTextures(){
        for(int i = 1; i <= ENEMY_TEXTURE_FILES; i++){
            enemyTextures.add(am.getImage("enemy" + i + ".png"));
        }
    }

    private void loadAI(){
        enemyAI.add(new RandomBJAI(), 3);
        enemyAI.add(new BasicBJAI(), 2);
        enemyAI.add(new SmartBJAI());
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
