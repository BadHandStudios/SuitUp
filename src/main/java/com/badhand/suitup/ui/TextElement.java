package com.badhand.suitup.ui;


import processing.core.PGraphics;

public class TextElement implements GUI {
    private String text;

    private int x, y;
    private int width, height;

    PGraphics texture;

    boolean visible = true;

    private static int id = 0;

    private int size;

    public TextElement(String text, int size, int x, int y) {
        this.size = size;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = text.length() * 12;
        this.height = 12;

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

    
    public PGraphics getTexture() {
        return null;
    }

    public int getSize() {
        return size;
    }

    public String getText() {
        return text;
    }
    
    public boolean visible() {
        return visible;
    }

    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    public boolean click(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public String getName() {
        return "TextElement_" + id;
    }
    
}
