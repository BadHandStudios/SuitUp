package com.badhand.suitup;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;

import processing.core.*;

public class SuitUp {

    private static WindowManager wm;
    private static GameManager gm;
    public static void main(String[] args) {

        // Initialize the WindowManager for the application
        wm = WindowManager.getInstance();
        gm = GameManager.getInstance();

        // Create the game window
        wm.createWindow(1920, 1080);

        // Wait for the window to be ready
        // TODO: Remove busy wait
        while(!wm.isReady());

        gm.changeScene(GameManager.state.MENU_MAIN);
        gm.update();
    }
}
