package com.badhand.suitup;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.Scenes.*;

import processing.core.*;

public class SuitUp {

    private static WindowManager wm;
    private static MenuMain mm;
    public static void main(String[] args) {

        // Initialize the WindowManager for the application
        wm = WindowManager.getInstance();
        mm = MenuMain.getInstance();

        // Create the game window
        wm.createWindow(1920, 1080);

        // Wait for the window to be ready
        // TODO: Remove busy wait
        while(!wm.isReady());

        mm.createMenu(wm,1920,1080);
    }
}
