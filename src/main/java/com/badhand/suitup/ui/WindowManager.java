package com.badhand.suitup.ui;

import processing.core.*;

public class WindowManager {
    private Window w;

    private static WindowManager instance = null;

    private WindowManager(){}; // Singleton


    public static WindowManager getInstance(){
        if(instance == null) instance = new WindowManager();
        return instance;
    }
    
    public void createWindow(int width, int height){
        w = new Window(width, height);
    }

    public boolean isReady() {
        return w.isReady();
    }

    public void destroyWindow(){
        w = null;
    }

    public PGraphics newGraphic(int width, int height){
        return w.newGraphic(width, height);
    }

    public boolean put(PGraphics g, int x, int y){ // Returns false if window is null   
        if(w == null) return false;
        w.put(g, x, y);
        return true;
    }

}
    
