package com.badhand.suitup.ui;

import java.util.*;

import processing.core.*;

public class Glow implements GUI {
    private int x, y;
    private int width, height;
    private boolean visible = true;
    private PGraphics texture;
    private LinkedList<GUI> enumeration;

    private static WindowManager wm = WindowManager.getInstance();



    public Glow(int x, int y, int width, int height,  int intensity, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;


        this.texture = wm.newGraphic(width, height);
        this.texture.beginDraw();
        this.texture.noStroke();
        this.texture.fill(color.toProcessingColor());
        this.texture.ellipse(width/2, height/2, (width/2) * 0.9f, (height/2) * 0.9f);
        this.texture.filter(PConstants.BLUR, intensity);
        this.texture.endDraw();

        this.enumeration = new LinkedList<GUI>();
        this.enumeration.add(this);

    }

    public PGraphics getTexture() {
        return texture;
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


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean visible() {
        return visible;
    }

    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    public boolean click(int x, int y) {
        return false;
    }

    public LinkedList<GUI> enumerate() {
        return enumeration;
    }





}
