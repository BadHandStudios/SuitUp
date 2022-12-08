package com.badhand.suitup.game;

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
        for(int i = 0; i < 4; i++){
            String msg = "Pass if lvl. " + i + " enemy is initialized correctly";
            int expectedHealth = 15 + 5 * i;
            int expectedAttack = 4 + i;
            for(int j = 0; j < 2; j++){
                Enemy testEnemy = enemyFactory.getEnemy(i,j);
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