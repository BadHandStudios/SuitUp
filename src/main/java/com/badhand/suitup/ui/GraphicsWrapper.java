package com.badhand.suitup.ui;

import java.util.LinkedList;
import java.util.List;

import processing.core.PGraphics;

public class GraphicsWrapper implements GUI {
    PGraphics g;
    int x, y;
    int width, height;

    boolean visible = true;

    private LinkedList<GUI> enumeration;

    public GraphicsWrapper(PGraphics g, int x, int y) {
        this.g = g;
        this.x = x;
        this.y = y;
        this.width = g.width;
        this.height = g.height;

        enumeration = new LinkedList<GUI>();
        enumeration.add(this);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean visible() {
        return visible;
    }

    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    public PGraphics getTexture() {
        return g;
    }

    public boolean click(int mouseX, int mouseY) {
        return false;
    }

    public String getName() {
        return "GraphicsWrapper";
    }

    public List<GUI> enumerate() {
        return this.enumeration;
    }

    
}
