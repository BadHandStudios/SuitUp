package com.badhand.suitup;

import com.badhand.suitup.ui.*;

import processing.core.*;

public class SuitUp {

    private static WindowManager wm;
    public static void main(String[] args) {

        // Initialize the WindowManager for the application
        wm = WindowManager.getInstance();

        // Create the game window
        wm.createWindow(1920, 1080);

        // Wait for the window to be ready
        // TODO: Remove busy wait
        while(!wm.isReady());

        TextButton b = new TextButton("Hello World", 32, 100, 100, null);
        wm.put(b);

    }
}
