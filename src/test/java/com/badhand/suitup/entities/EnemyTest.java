package com.badhand.suitup.entities;

import com.badhand.suitup.game.EnemyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    static Enemy testEnemy;
    @Test
    void getNameTest() {
        String msg = "Pass if name is not null";
        assertNotNull(testEnemy.getName(),msg);
    }

    @BeforeAll
    static void setUp(){
        testEnemy = EnemyFactory.getInstance().getEnemy(1,1);
    }
}