package com.badhand.suitup.ui.map;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.events.Event;
import com.badhand.suitup.events.EventManager;
import com.badhand.suitup.events.Events;
import com.badhand.suitup.game.entities.*;

import processing.core.*;
import java.util.*;

public class Node implements GUI {


    private int x, y, i, j;
    private int width = 256;
    private int height = 128;

    private boolean filled = false;
    private boolean[] edges = new boolean[4];

    private PGraphics texture;
    private GUI shadow;
    private LinkedList<GUI> enumeration;

    private static Random rand = new Random();

    private int offsetX, offsetY;
    

    private Entity entity;

    private static EventManager em = EventManager.getInstance();

    public Node(int i, int j) {
        this.i = i;
        this.j = j;

        // Initialize edges
        for (int k = 0; k < edges.length; k++) {
            edges[k] = false;
        }


        // Initialize texture
        texture = WindowManager.getInstance().newGraphic(width, height);
        texture.beginDraw();
        texture.stroke(255);
        texture.fill(26, 35, 74);
        texture.ellipse(texture.width/2, texture.height/2, texture.width - 2, texture.height - 2);
        texture.endDraw();

        enumeration = new LinkedList<GUI>();
        shadow = new SpotShadow(0, 0, width, height, 100, 10);
        shadow.setVisibility(false);
        enumeration.add(shadow);
        enumeration.add(this);


        offsetX = rand.nextInt(100) - 50;
        offsetY = rand.nextInt(100) - 50;
    }   

    public int getMapRow() {
        return i;
    }
    public int getMapColumn() {
        return j;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setPos(int x, int y) {
        this.x = x + offsetX;
        this.y = y + offsetY;
        shadow.setPos(this.x, this.y + (int)(height * 0.25));

        if(!(entity == null)) {
            entity.setPos(this.x, this.y - 50);
        }
    }

    public boolean isFilled() {
        return filled;
    }
    public void setFilled(boolean filled) {
       setVisibility(filled);
    }

    public boolean getEdge(int i) {
        return edges[i];
    }
    public void setEdge(int i, boolean value) {
        edges[i] = value;
    }
    public int connectingEdges(){
        int count = 0;
        for (int i = 0; i < edges.length - 1; i++) {
            if (edges[i]) {
                count++;
            }
        }
        return count;
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
        this.filled = visible;
        this.shadow.setVisibility(visible);
        if(this.entity != null) this.entity.setVisibility(visible);
    }

    public String getName() {
        return "Node_" + i + "_" + j;
    }

    public boolean click(int x, int y) {
        boolean clicked = x > this.x- width/2 && x < this.x+ width/2 && y > this.y - height/2 && y < this.y  + height/2;
        if(clicked) em.push(new Event(Events.SCENE_EVENT, this));
        return clicked;
    }

    
    
    public boolean setEntity(Entity e){
        if(this.entity != null) return false;
        this.entity = e;
        entity.setPos(x, y - 50);
        entity.setVisibility(filled);
        return true;
    }

    public Entity getEntity() {
        return entity;
    }

    public void removeEntity() {
        // enumeration.removeAll(entity.enumerate());
        entity = null;

    }


}
