package com.badhand.suitup.ui;


import java.util.*;
import processing.core.*;

public class CaptionedImage implements GUI {
    private ImageElement image;
    private TextElement text;
    private LinkedList<GUI> enumeration;
    private int x, y;

    private int size;

    private boolean vis = true;

    public CaptionedImage(PImage image, String caption, int x, int y, int size) {
        this.size = size;
        this.image = new ImageElement(x, y, size, size, image);
        this.text = new TextElement(caption, size, x, y);
        text.setPos(x + text.getWidth() / 2 + size, y);
        this.x = x;
        this.y = y;

        enumeration = new LinkedList<GUI>();
        enumeration.add(this.image);
        enumeration.add(this.text);
    }

    public PGraphics getTexture(){
        return null;
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
        this.image.setPos(x, y);
        this.text.setPos(x + text.getWidth() / 2 + size, y);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public LinkedList<GUI> enumerate(){
        return this.enumeration;
    }

    public void setCaption(String caption){
        this.text.setText(caption);
        this.setPos(x, y);
    }

    public boolean click(int x, int y){
        return false;
    }

    public boolean visible(){
        return this.vis;
    }
    public void setVisibility(boolean visibility){
        this.vis = visibility;
        this.image.setVisibility(visibility);
        this.text.setVisibility(visibility);
    }

    public int getWidth(){
        return this.image.getWidth();
    }
    public int getHeight(){
        return this.image.getHeight();
    }

    
}
