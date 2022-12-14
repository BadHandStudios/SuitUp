package com.badhand.suitup.ui;

import java.util.*;

import processing.core.*;

import com.badhand.suitup.assets.*;

public class ImageElement implements GUI {
    private int x, y;
    private int width, height;
    private boolean visible = true;
    private String name;
    private PGraphics texture;

    private LinkedList<GUI> enumeration;

    private static AssetManager am = AssetManager.getInstance();


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
        
        enumeration = new LinkedList<GUI>();
        enumeration.add(this);

    }
    public ImageElement(int x, int y, int width, int height, PImage texture) {
        this("", x, y, width, height, texture);
    }
    public ImageElement(int x, int y, int width, int height, String fileName) {
        this("", x, y, width, height, am.getImage(fileName));
    }

    public void redraw() {
        this.texture = WindowManager.getInstance().newGraphic(width, height);
        this.texture.beginDraw();
        this.texture.image(texture, 0, 0, width, height);
        this.texture.endDraw();

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
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        //this.redraw();
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
    public void setTexture(PImage texture) {
        this.texture.beginDraw();
        this.texture.image(texture, 0, 0, width, height);
        this.texture.endDraw();
    }
    public void setTexture(PGraphics p){
        this.texture = p;
    }

    public boolean click(){
        return false;
    }

    public boolean click(int mouseX, int mouseY) {
        return false;
    }

    public List<GUI> enumerate() {
        return enumeration;
    }

}
