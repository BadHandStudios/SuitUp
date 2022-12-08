package com.badhand.suitup.game;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.entities.Enemy;
import com.badhand.suitup.ui.WindowManager;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyFactoryTest {

    static EnemyFactory enemyFactory;

    @Test
    void getBossTest() {
        String msg = "Pass if lvl. 1 boss is initialized with correct health";
        Enemy testBoss = enemyFactory.getBoss(1);
        int expectedHealth = 50;
        int expectedAttack = 8;
        assertEquals(expectedHealth, testBoss.getHealth(), msg);
        msg = "Pass if lvl. 1 boss is initialized with correct attack";
        assertEquals(expectedAttack, testBoss.getAttack(), msg);

        testBoss = enemyFactory.getBoss(2);
        expectedHealth += 20;
        expectedAttack += 2;
        msg = "Pass if lvl. 2 boss is initialized with correct health";
        assertEquals(expectedHealth, testBoss.getHealth(), msg);
        msg = "Pass if lvl. 2 boss is initialized with correct attack";
        assertEquals(expectedAttack, testBoss.getAttack(), msg);
    }

    @Test
    void getEnemyTest() {
        for(int level = 1; level < 4; level++){
            int expectedHealth = 0;
            int expectedAttack = 0;


            for(int episode = 1; episode <= 2; episode++){
                String msg = "Pass if lvl. " + level + " enemy is initialized correctly in ep. " + episode;

                if (episode == 2){
                    expectedHealth = 25 + 5 * level;
                    expectedAttack = 5 + level;
                }
                else{
                    expectedHealth = 10 + 5 * level;
                    expectedAttack = 3 + level;
                }
                Enemy testEnemy = enemyFactory.getEnemy(episode,level);
                assertNotNull(testEnemy, msg);
                assertEquals(expectedHealth, testEnemy.getHealth(), msg);
                assertEquals(expectedAttack, testEnemy.getAttack(), msg);
            }
        }
    }

    @BeforeAll
    static void setUp(){
        WindowManager wm = WindowManager.getInstance();
        wm.createWindow(1920,1080);
        while(!wm.isReady()){}
        enemyFactory = EnemyFactory.getInstance();
    }
}