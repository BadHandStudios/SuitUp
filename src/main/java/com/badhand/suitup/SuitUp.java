package com.badhand.suitup;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.assets.*;

public class SuitUp {

    private static WindowManager wm;
    private static GameManager gm;
    private static AssetManager am;
    public static void main(String[] args) {

        // Initialize the WindowManager for the application
        wm = WindowManager.getInstance();
        
        // Create the game window
        wm.createWindow(1920, 1080);
        
        // Initialize the AssetManger for the application
        am = AssetManager.getInstance();
        // Wait for the window to be ready
        // TODO: Remove busy wait
        while(!wm.isReady());
        
        gm = GameManager.getInstance();


        while(true){
            gm.update();
        }
    }
}
