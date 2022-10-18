package com.badhand.suitup.ui;

import processing.core.*;

public class Window extends PApplet {

    private int width, height;

    private boolean ready = false;

    private PGraphics canvas;

    public Window(int width, int height) {
        this.width = width;
        this.height = height;
        
        runSketch(new String[]{"Window"}, this);
    }

    public boolean isReady() {
        return ready;
    }

    public void settings() {
        size(width, height);
        
    }

    public void setup() {
        canvas = createGraphics(width, height);
        ready = true;
    }

    public void draw() {
        background(0);
        image(canvas, 0, 0);
    }

    public void put(PGraphics g, int x, int y) {
        canvas.beginDraw();
        canvas.image(g, x, y);
        canvas.endDraw();
    }

    public PGraphics newGraphic(int width, int height) {
        PGraphics g = createGraphics(width, height);
        return g;
    }

    public void put(GUI g){
        // TODO: Implement on completion of GUI interface
    }

}
