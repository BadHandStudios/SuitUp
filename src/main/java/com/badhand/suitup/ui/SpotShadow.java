package com.badhand.suitup.ui;

import java.util.*;
import processing.core.*;

public class SpotShadow implements GUI {
    private int x, y;
    private int width, height;
    private boolean visible = true;

    private PGraphics texture;
    private LinkedList<GUI> enumeration;

    private static WindowManager wm = WindowManager.getInstance();

    public SpotShadow(int x, int y, int width, int height, int darkness,int blur) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        texture = wm.newGraphic(width * 2, height * 2);
        texture.beginDraw();
        texture.noStroke();
        texture.fill(0, 0, 0, darkness);
        texture.ellipse(width, height, width, height);
        texture.filter(PConstants.BLUR, blur);
        texture.endDraw();

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
        return texture;
    }

    public boolean click(int mouseX, int mouseY) {
        return false;
    }

    public List<GUI> enumerate() {
        return enumeration;
    }

    public String getName() {
        return "SpotShadow";
    }

    
    
}
