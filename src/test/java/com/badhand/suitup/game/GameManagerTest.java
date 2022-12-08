package com.badhand.suitup.game;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.entities.BasicBJAI;
import com.badhand.suitup.entities.BasicCBAI;
import com.badhand.suitup.entities.Enemy;
import com.badhand.suitup.events.Event;
import com.badhand.suitup.events.EventManager;
import com.badhand.suitup.events.Events;
import com.badhand.suitup.game.Scenes.*;
import com.badhand.suitup.ui.Window;
import com.badhand.suitup.ui.WindowManager;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    private static WindowManager windowManager;
    private static EventManager eventManager;
    private static GameManager gameManager;

    @Test
    void getInstanceTest() {
    }

    @Test
    void updateSceneChangeTest() {
        String msg = "Pass if update succeeds in scene change";
        Scene testScene = gameManager.getCurrentScene();
        eventManager.push(new Event(Events.SCENE_CHANGE, GameState.DEBUG));
        gameManager.update();
        assertNotEquals(testScene, gameManager.getCurrentScene(), msg);
    }

    @Test
    void updateBattleInitTest(){
        String msg = "Pass if update succeeds in battle initiate";
        Scene testScene = gameManager.getCurrentScene();
        EnemyFactory enemyFactory = EnemyFactory.getInstance();
        Enemy enemy = enemyFactory.getEnemy(1,1);
        eventManager.push(new Event(Events.BATTLE_INITIATE, enemy));
        gameManager.update();
        assertNotEquals(testScene, gameManager.getCurrentScene(), msg);
    }

    @Disabled
    @Test
    void quitGameTest(){
        String msg = "Pass if update succeeds in destroying the window";
        eventManager.push(new Event(Events.QUIT_GAME, null)); //Untestable? Exits the process.
        gameManager.update();
        assertNull(windowManager.getWindow(), msg);
    }

    @Disabled
    @Test
    void updateBossFightTest(){
        String msg = "Pass if update succeeds in boss fight initiate";
        Scene testScene = gameManager.getCurrentScene();
        eventManager.push(new Event(Events.BOSS_FIGHT, 2));
        gameManager.update();
        assertNotEquals(testScene, gameManager.getCurrentScene(), msg);
    }

    @Test
    void updateEndGameTest(){
        String msg = "Pass if update succeeds in ending the game.";
        Scene testScene = gameManager.getCurrentScene();
        eventManager.push(new Event(Events.END_GAME, 1));
        gameManager.update();
        assertNotEquals(testScene, gameManager.getCurrentScene(), msg);
    }

    @Test
    void getEpisodeTest() {
        String msg = "Pass if valid ep. #";
        assertTrue(gameManager.getEpisode() > 0, msg);
    }

    @Test
    void setEpisodeTest() {
        String msg = "Pass if setEpisode succeeds in setting the ep. #";
        int expected = 2;
        gameManager.setEpisode(expected);
        assertEquals(expected,gameManager.getEpisode());
    }

    @Disabled
    @Test
    void changeSceneNullTest(){
        String msg = "Pass if scene class is correct for new scene";
        Scene testScene = new SlotScene();
        gameManager.changeScene(null);
    }

    @Test
    void changeSceneSplashTest() {
        String msg = "Pass if scene class is correct for new scene";
        Scene testScene = new Splash();
        gameManager.changeScene(GameState.SPLASH);
        assertEquals(testScene.getClass(), gameManager.getCurrentScene().getClass(), msg);
    }

    @Test
    void changeSceneMenuMainTest(){
        String msg = "Pass if scene class is correct for new scene";
        Scene testScene = new MenuMain();
        gameManager.changeScene(GameState.MENU_MAIN);
        assertEquals(testScene.getClass(), gameManager.getCurrentScene().getClass(), msg);
    }

    @Test
    void changeSceneBattleTest(){
        String msg = "Pass if scene class is correct for new scene";
        AssetManager am = AssetManager.getInstance();
        Scene testScene = new SceneBattle(new Enemy(am.getImage("Enemy.png"), "Mike", 20, 5, windowManager.getWidth() - 150, 200, new BasicBJAI(), new BasicCBAI()));
        gameManager.changeScene(GameState.SCENE_BATTLE);
        assertEquals(testScene.getClass(), gameManager.getCurrentScene().getClass(), msg);
    }

    @Test
    void changeSceneShopTest(){
        String msg = "Pass if scene class is correct for new scene";
        Scene testScene = new ShopScene();
        gameManager.changeScene(GameState.MENU_SHOP);
        assertEquals(testScene.getClass(), gameManager.getCurrentScene().getClass(), msg);
    }

    @Test
    void changeSceneDebugTest(){
        String msg = "Pass if scene class is correct for new scene";
        Scene testScene = new Debug();
        gameManager.changeScene(GameState.DEBUG);
        assertEquals(testScene.getClass(), gameManager.getCurrentScene().getClass(), msg);
    }

    @Test
    void changeSceneLevelSelectTest(){
        String msg = "Pass if scene class is correct for new scene";
        Scene testScene = new MenuLevelSelect();
        gameManager.changeScene(GameState.MENU_LEVEL_SELECT);
        assertEquals(testScene.getClass(), gameManager.getCurrentScene().getClass(), msg);
    }

    @Test
    void changeSceneMapTest(){
        String msg = "Pass if scene class is correct for new scene";
        Scene testScene = new MapScene();
        gameManager.changeScene(GameState.MAP_SCENE);
        assertEquals(testScene.getClass(), gameManager.getCurrentScene().getClass(), msg);
    }

    @Test
    void changeSceneSlotTest(){
        String msg = "Pass if scene class is correct for new scene";
        Scene testScene = new SlotScene();
        gameManager.changeScene(GameState.SLOT_SCENE);
        assertEquals(testScene.getClass(), gameManager.getCurrentScene().getClass(), msg);
    }
    @Test
    void getCurrentSceneTest() {
    }

    @BeforeAll
    static void setUp(){
        windowManager = WindowManager.getInstance();
        eventManager = EventManager.getInstance();
        gameManager = GameManager.getInstance();
        windowManager.createWindow(1920,1080);
        while(!windowManager.isReady()){}
    }

    @AfterAll
    static void tearDown(){
        windowManager.destroyWindow();
    }

}