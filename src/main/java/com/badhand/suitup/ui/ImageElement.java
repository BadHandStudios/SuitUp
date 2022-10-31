package com.badhand.suitup.ui;

import processing.core.*;

public class ImageElement implements GUI {
    private int x, y;
    private int width, height;
    private boolean visible = true;
    private String name;
    private PGraphics texture;


    public ImageElement(String name, int x, int y, int width, int height, PImage texture) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.texture = WindowManager.getInstance().newGraphic(width, height);
        this.texture.beginDraw();
        this.texture.image(texture, 0, 0, width, height);
        this.texture.endDraw();
        


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

    public String getName() {
        return name;
    }


    public PGraphics getTexture() {
        return texture;
    }

    public boolean click(){
        return false;
    }

    @Override
    public boolean click(int mouseX, int mouseY) {
        // TODO Auto-generated method stub
        return false;
    }



}
