package com.badhand.suitup.entities;


import com.badhand.suitup.ui.*;


import java.util.*;


import processing.core.*;

public class SlotMachine extends Entity {
    private LinkedList<GUI> enumeration;
    private ImageElement texture;

    private static WindowManager wm = WindowManager.getInstance();

    int x,y;

    public SlotMachine() {
        texture = new ImageElement(0, 0, 100, 120, "slotmachine_big.png");
        enumeration = new LinkedList<GUI>();
        enumeration.add(texture);
        wm.registerDiffered(texture);
        wm.put(this);
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
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
        this.texture.setPos(x,y);
    }

    public PGraphics getTexture() {
        return null;
    }

    public boolean visible() {
        return false;
    }

    public void setVisibility(boolean visible) {
        this.texture.setVisibility(visible);
        
    }

    public boolean click(int mouseX, int mouseY) {
        // TODO Auto-generated method stub
        return false;
    }

    public List<GUI> enumerate() {
        // TODO Auto-generated method stub
        return this.enumeration;
    }
    
}
