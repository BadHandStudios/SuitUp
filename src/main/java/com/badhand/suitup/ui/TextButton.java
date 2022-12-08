package com.badhand.suitup.ui;

import com.badhand.suitup.events.*;

import processing.core.*;

import java.util.*;

public class TextButton implements GUI {
    private String text;
    private int x, y;
    private int width, height;
    private boolean visible = true;
    private static int id = 0;
    private int size;
    private PGraphics texture;
    private Event e;
    private String name;

    private WindowManager wm = WindowManager.getInstance();
    private EventManager em = EventManager.getInstance();

    private LinkedList<GUI> enumeration;

    private TextElement buttonText;

    public TextButton(String text, int size, int x, int y, Event e) {
        this.text = text;
        this.size = size;
        this.x = x;
        this.y = y;
        this.width = (int)(text.length() * size * 0.6);
        this.height = (int)(size * 1.5);
        this.e = e;
        this.name = "TextButton_" + id++;

        this.texture = wm.newGraphic(width, height);
        texture.beginDraw();
        texture.stroke(255);
        texture.fill(127);
        texture.rect(0, 0, width - 1, height - 1, 5);
        texture.fill(0);
        // texture.textAlign(PConstants.CENTER, PConstants.CENTER);
        // texture.textSize(size);
        // texture.text(text, (width / 2) - 1, (height / 2 - 1));
        texture.endDraw();

        enumeration = new LinkedList<GUI>();
        enumeration.add(this);

        buttonText = new TextElement(text, size, x, y);
        enumeration.add(buttonText);


        
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size;
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

    public void setVisibility(boolean visible) {
        this.visible = visible;
        this.buttonText.setVisibility(visible);
    }
    public boolean visible(){
        return visible;
    }

    public PGraphics getTexture() {
        return texture;
    }

    public String getName(){
        return name;
    }

    public String getText() {
        return text;
    }

    public boolean click(int x, int y) {
        if(!(x > this.x - (width/2) && x < this.x + width/2 && y > this.y - height/2 && y < this.y + height/2)) return false;
        em.push(e);
        return true;
    }

    public List<GUI> enumerate() {
        return enumeration;
    }
}
