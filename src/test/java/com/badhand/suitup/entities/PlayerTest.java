package com.badhand.suitup.entities;

import com.badhand.suitup.ui.WindowManager;
import com.badhand.suitup.ui.map.Node;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    static Player player;
    @Test
    void setChipsTest() {
        String msg = "Pass if set chips succeeds";
        int expected = 5;
        player.setChips(expected);
        assertEquals(expected, player.getChips(), msg);
    }

    @Test
    void addChipsTest() {
        String msg = "Pass if add chips succeeds";
        int expected = 5;
        player.setChips(0);
        player.addChips(3);
        player.addChips(2);
        assertEquals(expected, player.getChips(), msg);
    }

    @Test
    void removeChipsTest() {
        String msg = "Pass if remove chips succeeds";
        int expected = 5;
        player.setChips(10);
        player.removeChips(5);
        assertEquals(expected, player.getChips(), msg);
    }

    @Test
    void clearChipsTest() {
        String msg = "Pass if clear chips succeeds";
        int expected = 0;
        player.setChips(10);
        player.clearChips(0); //Unnecessary param
        assertEquals(expected, player.getChips(), msg);
    }

    @Test
    void getChipsTest() {
        String msg = "Pass if get chips succeeds";
        int expected = 5;
        player.setChips(5);
        assertEquals(expected, player.getChips(), msg);
    }

    @Test
    void getCurrentNodeTest() {
        String msg = "Pass if get current node succeeds";
        Node testNode = new Node(1, 1);
        player.move(testNode);
        assertEquals(testNode, player.getCurrentNode(), msg);
    }

    @Test
    void getPreviousNodeTest() {
        String msg = "Pass if get previous node succeeds";
        Node testNode = new Node(1,1);
        Node otherNode = new Node(2,2);
        player.move(testNode);
        player.move(otherNode);
        assertEquals(testNode, player.getPreviousNode(), msg);
    }

    @Test
    void resetTest() {
        String msg = "Pass if max health is reset to correct value";
        int expectedMaxHealth = 20;
        int expectedAttack = 5;
        int expectedChips = 0;
        player.reset();
        assertEquals(expectedMaxHealth, player.getMaxHealth(), msg);
        msg = "Pass if attack is reset to correct value";
        assertEquals(expectedAttack, player.getAttack(), msg);
        msg = "Pass if chips is reset to correct value";
        assertEquals(expectedChips, player.getChips(), msg);


    }

    @BeforeAll
    static void setUp() {
        WindowManager wm = WindowManager.getInstance();
        wm.createWindow(1920, 1080);
        while (!wm.isReady()) {}
        player = Player.getInstance();
    }
    @AfterAll
    static void tearDown(){
//        WindowManager.getInstance().destroyWindow();
    }
}