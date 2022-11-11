package com.badhand.suitup.ui;

import java.util.*;
import processing.core.*;

public class AnimatedImage extends ImageElement implements Animation {
    private int speed = 60;
    private int frame = 0;
    private int animFrame = 0;

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

    @Override
    public void update() {
        if(frame++ >= speed && !paused) {
            frame = 0;
            animFrame++;
            this.setTexture(frames.get(animFrame % frames.size()));
        }
        
    }
    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
        
    }
    @Override
    public void pause() {
        paused = true;
    }
    @Override
    public void resume() {
        paused = false;
        
    }
    @Override
    public void stop() {
        pause();
        frame = 0;
        
    }

    


    
}
