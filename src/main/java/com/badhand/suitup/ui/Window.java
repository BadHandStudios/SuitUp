package com.badhand.suitup.ui;

import java.util.*;

import processing.core.*;

import com.badhand.suitup.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;

public class Window extends PApplet {

    private int width, height;

    private boolean ready = false;

    private LinkedList<GUI> guiBuffer = new LinkedList<GUI>();

    private PFont font;

    private Color bg = new Color(0, 0, 0);

    private GameManager gm = GameManager.getInstance();
    private static EventManager em = EventManager.getInstance();

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
        frameRate(60);
        try{
            font = createFont(SuitUp.class.getResource("/fonts/ArchitunMedium.ttf").toURI().getPath(), 256);
        }catch(Exception e){
            System.out.println("Error loading font");
        }
        textFont(font);
        shapeMode(PConstants.CENTER);
        textAlign(PConstants.CENTER, PConstants.CENTER);
        imageMode(PConstants.CENTER);
        rectMode(PConstants.CENTER);

    }

    public void draw() {
        background(bg.toProcessingColor());
        for(GUI g : guiBuffer) {
            for(GUI e : g.enumerate()){
                if(e.visible()) {
                    if(e instanceof TextElement){ 
                        // Text requires special handling due to the how processing handles fonts
                        push();
                        TextElement te = (TextElement) e;
                        textSize(te.getSize());
                        fill(te.getColor().toProcessingColor());
                        stroke(te.getColor().toProcessingColor());
                        text(te.getText(), te.getX(), te.getY());
                        pop();
                        continue;
                    }

                    image(e.getTexture().get(), e.getX(), e.getY());
                }

                // Update the animations
                if(e instanceof Animation){
                    Animation a = (Animation) e;
                    a.update();
                }
            }
        }

        gm.update();
        
    }

    public void mousePressed() {
        for(GUI g : guiBuffer) {
            g.click(mouseX, mouseY);
        }
    }

    public void keyPressed() {
        em.push(new Event(Events.KEY_PRESS, keyCode));
    }

    public PFont getFont(){
        return font;
    }

    public void setBackground(Color c){
        bg = c;
    }
    
    public void put(GUI g) {
        guiBuffer.add(g);
    }

    public void remove(String name){ // Deprecated as per switch to LinkedList
        for(GUI g : guiBuffer) {
            if(g.getName().equals(name)) {
                guiBuffer.remove(g);
                break;
            }
        }
    }

    public void remove(GUI g){
        remove(g.getName());
    }

    public void clear(){
        guiBuffer.clear();
    }


    public PImage importImage(String path){
        return loadImage(path);
    }

    public PGraphics newGraphic(int width, int height) {
        PGraphics g = createGraphics(width, height);
        return g;
    }

}
