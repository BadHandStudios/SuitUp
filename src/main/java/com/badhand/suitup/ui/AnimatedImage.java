package com.badhand.suitup.ui;

import java.util.*;
import processing.core.*;

public class AnimatedImage extends ImageElement implements Animation, Rotatable {
    private int speed = 60;
    private int frame = 0;
    private int animFrame = 0;
    private int rotation;

    boolean paused = false;


    ArrayList<PGraphics> frames;

    public AnimatedImage(int x, int y, int width, int height, ArrayList<PImage> frames) {
        super(x, y, width, height, frames.get(0));
        this.frames = new ArrayList<PGraphics>();
        for (PImage frame : frames) {
            PGraphics texture = WindowManager.getInstance().newGraphic(width, height);
            texture.beginDraw();
            texture.image(frame, 0, 0, width, height);
            texture.endDraw();
            this.frames.add(texture);
        }

    }
    public AnimatedImage(int x, int y, int width, int height, PImage... frames) {
        super(x, y, width, height, frames[0]);
        this.frames = new ArrayList<PGraphics>();
        for (PImage frame : frames) {
            PGraphics texture = WindowManager.getInstance().newGraphic(width, height);
            texture.beginDraw();
            texture.image(frame, 0, 0, width, height);
            texture.endDraw();
            this.frames.add(texture);
        }


    }

    
    public void update() {
        if(frame++ >= speed && !paused) {
            frame = 0;
            animFrame++;
            this.setTexture(frames.get(animFrame % frames.size()));
        }
        
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
        
    }
    
    public void pause() {
        paused = true;
    }
    
    public void resume() {
        paused = false;
        
    }

    public void stop() {
        pause();
        frame = 0;
        
    }

    public int getRotation(){
        return rotation;
    }

    public void setRotation(int rotation){
        this.rotation = rotation;
    }
    

    


    
}  
