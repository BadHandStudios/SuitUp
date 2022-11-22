package com.badhand.suitup.ui.map;

import com.badhand.suitup.ui.*;

import processing.core.*;
import java.util.*;

public class Node implements GUI {


    private int x, y, i, j;
    private boolean filled = false;
    private boolean[] edges = new boolean[3];

    private PGraphics texture;
    private LinkedList<GUI> enumeration;

    

    // private Entity entity;

    public Node(int i, int j) {
        this.i = i;
        this.j = j;

        // Initialize edges
        for (int k = 0; k < edges.length; k++) {
            edges[k] = false;
        }


        // Initialize texture
        texture = WindowManager.getInstance().newGraphic(64, 40);
        texture.beginDraw();
        texture.stroke(255);
        texture.fill(127);
        texture.ellipse(64/2, 40/2, 64, 40);
        texture.endDraw();

        enumeration = new LinkedList<GUI>();
        enumeration.add(this);



    }

    public int getMapX() {
        return i;
    }
    public int getMapY() {
        return j;
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

    public boolean isFilled() {
        return filled;
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean getEdge(int i) {
        return edges[i];
    }
    public void setEdge(int i, boolean value) {
        edges[i] = value;
    }

    public PGraphics getTexture() {
        return texture;
    }

    public int getWidth() {
        return texture.width;
    }

    public int getHeight() {
        return texture.height;
    }

    public LinkedList<GUI> enumerate(){
        return enumeration;
    }

    public boolean visible(){
        return filled;
    }

    public void setVisibility(boolean visible){
        filled = visible;
    }

    public String getName() {
        return "Node_" + i + "_" + j;
    }

    public boolean click(int x, int y) {
        return x >= this.x  - (texture.width / 2) && x <= this.x + (texture.width / 2) && y >= this.y - (texture.height / 2) && y <= this.y + (texture.height / 2);
    }

    



}
