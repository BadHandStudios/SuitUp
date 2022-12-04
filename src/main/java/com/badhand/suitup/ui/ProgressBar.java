package com.badhand.suitup.ui;

import java.util.LinkedList;

import processing.core.PGraphics;

public class ProgressBar implements GUI {
    private int x, y, width, height;
    private int value, maxValue;
    private Color color;
    private boolean visible = true;
    private PGraphics texture;
    private LinkedList<GUI> enumeration = new LinkedList<GUI>();

    private static WindowManager wm = WindowManager.getInstance();


    public ProgressBar(int x, int y, int width, int height, int max, int value){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxValue = max;
        this.value = value;
        this.color = new Color(0, 255, 0);

        this.texture = wm.newGraphic(width+4, height+4);
        this.texture.beginDraw();
        this.texture.stroke(255);
        this.texture.strokeWeight(2);
        this.texture.fill(0);
        this.texture.rect(2, 2, width, height, 5);
        this.texture.noStroke();
        this.texture.fill(this.color.toProcessingColor());
        this.texture.rect(2, 2, (value / maxValue) * width , height);
        this.texture.endDraw();

        this.enumeration.add(this);
    }
    
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }


    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

    public LinkedList<GUI> enumerate(){
        return enumeration;
    }
    public PGraphics getTexture(){
        return this.texture;
    }

    public void setVisibility(boolean visible){
        this.visible = visible;
    } 
    public boolean visible(){
        return visible;
    }

    public boolean click(int x, int y){
        return false;
    }


    // Progress bar specific methods

    public void setValue(int value){
        if(value < 0) return;
        this.value = value;
        this.texture.beginDraw();
        this.texture.fill(0);
        this.texture.rect(2, 2, width, height);
        this.texture.noStroke();
        this.texture.fill(this.color.toProcessingColor());
        this.texture.rect(2, 2, ((float)value / (float)maxValue) * width , height);
        this.texture.endDraw();

    }
    public int getValue(){
        return value;
    }

    public void setMaxValue(int max){
        this.maxValue = max;
    }
    public int getMaxValue(){
        return maxValue;
    }

    public void setColor(Color color){
        this.color = color;
        this.setValue(this.value);
    }






    

}
