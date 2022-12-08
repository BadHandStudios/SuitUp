package com.badhand.suitup;
import org.junit.jupiter.api.*;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.events.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;

/*
 * This class is responsible for testing gameplay conditions.
 */


@DisplayName("Gameplay Tests")
public class GameTest {

    private static WindowManager wm;
    private static GameManager gm;
    private static AssetManager am;
    private static EventManager em;

    private static Scene battleScene;

    @Test
    public void gameplayTest2(){
        //System.out.println("Gametest test 2 happen");
        assert(true);
    }

    @Test
    public void gameplayTest(){
        //System.out.println("Gametest test happen");
        assert(true);
    }

    @BeforeAll
    public static void Setup()
    {
        //System.out.println("Gametest setup happen");
        wm = WindowManager.getInstance();
        wm.createWindow(1920, 1080);

        am = AssetManager.getInstance();

        while(!wm.isReady()){};
        wm.setBackground(new Color(0,0,0));

        gm = GameManager.getInstance();
        em = EventManager.getInstance();
        em.push(new Event(Events.SCENE_CHANGE, GameState.SCENE_BATTLE));
        battleScene = gm.getCurrentScene();
    }
    
    @AfterAll
    public static void TearDown()
    {
        wm.destroyWindow();
    }

    @BeforeEach
    public void BeforeEach()
    {
    }
}
