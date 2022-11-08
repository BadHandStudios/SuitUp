package com.badhand.suitup.ui;

import processing.core.*;

public class WindowManager {
    private Window w;

    private int width, height;

    private static WindowManager instance = null;

    private WindowManager(){}; // Singleton


    public static WindowManager getInstance(){
        if(instance == null) instance = new WindowManager();
        return instance;
    }
    
    public void createWindow(int width, int height){
        w = new Window(width, height);
        this.width = width;
        this.height = height;
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

    public PFont getFont(){
        return w.getFont();
    }
    
    public void setBackground(Color c){
        w.setBackground(c);
    }

    public boolean put(GUI g) { // Returns false if window is null   
        if(w == null) return false;
        w.put(g);
        return true;
    }

    
    public boolean remove(String name) { // Returns false if window is null
        if(w == null) return false;
        w.remove(name);
        return true;
    }

    public boolean remove(GUI g) { // Returns false if window is null
        if(w == null) return false;
        w.remove(g);
        return true;
    }

    public PImage importImage(String path) {
        return w.importImage(path);
    }

    public boolean clear() { // Returns false if window is null
        if(w == null) return false;
        w.clear();
        return true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
    
