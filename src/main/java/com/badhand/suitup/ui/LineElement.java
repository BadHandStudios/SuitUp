package com.badhand.suitup.ui;

import java.util.List;

import processing.core.PGraphics;

public class LineElement implements GUI {
    int x1, y1, x2, y2;
    Color color;
    int weight;

    String name;


    public LineElement(int x1, int y1, int x2, int y2, int weight, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.weight = weight;
        this.color = color;
    }




    public int getWidth() {
        return weight;
    }

    public int getHeight() {
        return 0;
    }



    public Color getColor(){
        return color;
    }
    public int getX() {
        return x1;
    }
    public int getY() {
        return y1;
    }

    public int getX2(){
        return x2;
    }
    public int getY2(){
        return y2;
    }

    public void setPos(int x, int y) {
        return;
    }




    public PGraphics getTexture() {
        return null;
    }




    public boolean visible() {
        return true;
    }




    public void setVisibility(boolean visible) {
        return;
    }




    public boolean click(int mouseX, int mouseY) {
        return false;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        
        
    }


    public List<GUI> enumerate() {
        return null;
    }
    
}
