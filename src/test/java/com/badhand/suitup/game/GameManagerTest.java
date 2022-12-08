package com.badhand.suitup.game;

import com.badhand.suitup.entities.Enemy;
import com.badhand.suitup.events.Event;
import com.badhand.suitup.events.EventManager;
import com.badhand.suitup.events.Events;
import com.badhand.suitup.ui.Window;
import com.badhand.suitup.ui.WindowManager;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    private static WindowManager windowManager;
    private static EventManager eventManager;
    private static GameManager gameManager;

    @Test
    void getInstance() {
    }

    @Test
    void update() {
        String msg = "Pass if update succeeds in scene change";
        Scene testScene = gameManager.getCurrentScene();
        eventManager.push(new Event(Events.SCENE_CHANGE, GameState.DEBUG));
        gameManager.update();
        assertNotEquals(testScene, gameManager.getCurrentScene(), msg);


        msg = "Pass if update succeeds in battle initiate";
        testScene = gameManager.getCurrentScene();
        EnemyFactory enemyFactory = EnemyFactory.getInstance();
        Enemy enemy = enemyFactory.getEnemy(1,1);
        eventManager.push(new Event(Events.BATTLE_INITIATE, enemy));
        gameManager.update();
        assertNotEquals(testScene, gameManager.getCurrentScene(), msg);

        msg = "Pass if update succeeds in boss fight initiate";
        testScene = gameManager.getCurrentScene();
        eventManager.push(new Event(Events.BOSS_FIGHT, 0));
        gameManager.update();
        assertNotEquals(testScene, gameManager.getCurrentScene(), msg);

        msg = "Pass if update succeeds in ending the game.";
        testScene = gameManager.getCurrentScene();
        eventManager.push(new Event(Events.END_GAME, 1));
        gameManager.update();
        assertNotEquals(testScene, gameManager.getCurrentScene(), msg);
    }

    @Test
    void getEpisode() {
    }

    @Test
    void setEpisode() {
    }

    @Test
    void unlock() {
    }

    @Test
    void initiateBattle() {
    }

    @Test
    void changeScene() {
    }

    @Test
    void getCurrentScene() {
    }

    @BeforeAll
    static void setup(){
        windowManager = WindowManager.getInstance();
        eventManager = EventManager.getInstance();
        gameManager = GameManager.getInstance();
        windowManager.createWindow(1920,1080);
    }

    @AfterAll
    static void afterAll(){
        String msg = "Pass if update succeeds in destroying the window";
        eventManager.push(new Event(Events.QUIT_GAME, null)); //Untestable? Exits the process.
        gameManager.update();
        assertNull(windowManager.getWindow());
    }
}