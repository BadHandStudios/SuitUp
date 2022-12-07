package com.badhand.suitup.ui;

import java.util.*;

import processing.core.*;

import com.badhand.suitup.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.events.*;

import com.badhand.suitup.assets.*;

public class Window extends PApplet {

    private int width, height;

    private boolean ready = false;

    private ArrayList<GUI> guiBuffer = new ArrayList<GUI>();
    
    private ArrayList<HashSet<GUI>> differedRegistries = new ArrayList<HashSet<GUI>>();

    private PFont font;

    private Color bg = new Color(0, 0, 0);

    private GameManager gm = GameManager.getInstance();
    private static EventManager em = EventManager.getInstance();
    private static AssetManager am = AssetManager.getInstance();



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
            font = createFont(am.getFont("ArchitunMedium.ttf"), 256);
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
        LinkedList<GUI> differ = new LinkedList<GUI>();

        synchronized(this){
            for(GUI g : guiBuffer) {
                try{
                for(GUI e : g.enumerate()){
                    if(e instanceof Animation){
                        Animation a = (Animation) e;
                        a.update();
                    }


                    for(HashSet<GUI> registry : differedRegistries){
                        if(registry.contains(e) || registry.contains(g)){
                            differ.add(e);
                        }
                    }

                    place(e);
                }
                
                }catch(Exception exception){
                    // :(
                }
            }
        }
        for(GUI g : differ){
            place(g);
        }

        gm.unlock();

    }

    private void place(GUI e) {
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
                return;
            }else if(e instanceof LineElement){
                // Lines are a processing primitive
                push();
                LineElement le = (LineElement) e;
                stroke(le.getColor().toProcessingColor());
                strokeWeight(le.getWidth());
                line(le.getX(), le.getY(), le.getX2(), le.getY2());
                pop();
                return;
            }
            

            
            if(e instanceof Rotatable){
                pushMatrix();
                translate(e.getX(), e.getY());
                rotate(radians(((Rotatable) e).getRotation()));
                image(e.getTexture(), 0, 0);
                popMatrix();
            } else{
                image(e.getTexture(), e.getX(), e.getY());
            }
        }

    }

    public synchronized void registerDiffered(GUI g) {
        registerDiffered(g, 0);
    }

    public synchronized void registerDiffered(GUI g, int index) {
        while(differedRegistries.size() <= index){
            differedRegistries.add(new HashSet<GUI>());
        }
        differedRegistries.get(index).add(g);
    }

    public void mousePressed() {
        synchronized(this){
            try{
                GUI g;
                for(int i = 0 ; i < guiBuffer.size(); i++){
                    g = guiBuffer.get(i);
                    for(GUI e : g.enumerate()){
                        if(e.visible()) {
                            e.click(mouseX, mouseY);
                        }
                    }
                }
            }catch(Exception e){
                
            }
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
    
    public synchronized void put(GUI g) {
        // synchronized(guiBuffer){
            guiBuffer.add(g);
        // }
    }

    // public void remove(String name){ // Deprecated as per switch to LinkedList
    //     for(GUI g : guiBuffer) {
    //         if(g.getName().equals(name)) {
    //             guiBuffer.remove(g);
    //             break;
    //         }
    //     }
    // }

    public synchronized void remove(GUI g){
        guiBuffer.remove(g);
    }

    public synchronized void clear(){
        guiBuffer.clear();
    }


    public PImage importImage(String path){
        return loadImage(path);
    }

    public PGraphics newGraphic(int width, int height) {
        PGraphics g = createGraphics(width, height);
        return g;
    }


    public Object getLock(){
        return this;
    }
}
