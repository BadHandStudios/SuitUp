package com.badhand.suitup.ui;

import java.util.HashMap;

import processing.core.*;

import com.badhand.suitup.*;

public class Window extends PApplet {

    private int width, height;

    private boolean ready = false;

    private HashMap<String, GUI> guiBuffer = new HashMap<String, GUI>();

    private PFont font;

    public Window(int width, int height) {
        this.width = width;
        this.height = height;
        
        runSketch(new String[]{"Window"}, this);
    }

    public boolean isReady() {
        return ready;
    }

    public void settings() {
        size(width, height);
        ready = true;

    }

    public void setup() {
        try{
            font = createFont(SuitUp.class.getResource("/fonts/ArchitunMedium.ttf").toURI().getPath(), 256);
        }catch(Exception e){
            System.out.println("Error loading font");
        }
        textFont(font);
        background(0);
    }

    public void draw() {
        for(GUI g : guiBuffer.values()) {
            if(g.visible()) {
                if(g instanceof TextElement){
                    
                    push();
                    TextElement te = (TextElement) g;
                    textSize(te.getSize());
                    fill(255);
                    stroke(255);
                    text(te.getText(), te.getX(), te.getY());
                    pop();
                    continue;
                }
                image(g.getTexture().get(), g.getX(), g.getY());
            }
        }
        
    }

    public PFont getFont(){
        return font;
    }

    public void put(GUI g) {
        guiBuffer.put(g.getName(), g);
    }

    public void remove(String name){
        guiBuffer.remove(name);
    }
    public void remove(GUI g){
        remove(g.getName());
    }
    public void clear(){
        guiBuffer.clear();
    }



    public PGraphics newGraphic(int width, int height) {
        PGraphics g = createGraphics(width, height);
        return g;
    }

}
