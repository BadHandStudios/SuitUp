package com.badhand.suitup.ui.map;

import com.badhand.suitup.ui.*;
import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.events.Event;
import com.badhand.suitup.events.EventManager;
import com.badhand.suitup.events.Events;
import com.badhand.suitup.entities.*;
import com.badhand.suitup.game.ShuffleBag;

import processing.core.*;
import java.util.*;

public class Node implements GUI {
    private static AssetManager am = AssetManager.getInstance();
    private static WindowManager wm = WindowManager.getInstance();


    private static ShuffleBag<PImage> decoBag;

    private int x, y, i, j;
    private int width = 256;
    private int height = 128;

    private boolean filled = false;
    private boolean[] edges = new boolean[4];

    private boolean debug = false;

    private static PGraphics texture;
    private static PGraphics debugTexture;

    private GUI shadow;
    private LinkedList<GUI> enumeration;
    private GUI unfilledDecoration;
    private boolean canDisplayUnfilledDecoration = false;

    private static Random rand = new Random();

    private int offsetX, offsetY;
    

    private Entity entity;
    private Player player;

    private static EventManager em = EventManager.getInstance();

    public Node(int i, int j) {
        if(decoBag == null){
            decoBag = new ShuffleBag<PImage>();
            decoBag.add(am.getImage("chippile.png"), 2);
            decoBag.add(am.getImage("roulette.png"), 2);
            decoBag.add(am.getImage("slotmachine.png"), 2);
        }


        this.canDisplayUnfilledDecoration = rand.nextInt(100) < 25;
        if(canDisplayUnfilledDecoration) this.unfilledDecoration = new ImageElement(0, 0, 200, 200, decoBag.next());


        this.i = i;
        this.j = j;

        // Initialize edges
        for (int k = 0; k < edges.length; k++) {
            edges[k] = false;
        }


        // Initialize texture
        if(texture == null){
            texture = WindowManager.getInstance().newGraphic(width, height);
            texture.beginDraw();
            texture.stroke(255);
            texture.fill(26, 35, 74);
            texture.ellipse(texture.width/2, texture.height/2, texture.width - 2, texture.height - 2);
            texture.endDraw();
        }
        

        enumeration = new LinkedList<GUI>();
        shadow = new SpotShadow(0, 0, width, height, 100, 10);
        shadow.setVisibility(false);

        enumeration.add(shadow);
        if(this.canDisplayUnfilledDecoration){ 
            enumeration.add(unfilledDecoration);

        }



        offsetX = rand.nextInt(100) - 50;
        offsetY = rand.nextInt(100) - 50;

        if(rand.nextInt(100) < 30){
            Entity slotMachine = new SlotMachine();
            this.setEntity(slotMachine);
            setEntityPositions();   
        }
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
        if(this.canDisplayUnfilledDecoration) this.unfilledDecoration.setPos(x, y);

        setEntityPositions();
        
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
    public void clearEdges(){
        for (int i = 0; i < edges.length; i++) {
            edges[i] = false;
        }
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
        return debug ? debugTexture : texture;
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
        
        return filled || canDisplayUnfilledDecoration;
    }



    public void setVisibility(boolean visible){
        this.filled = visible;
        this.shadow.setVisibility(visible);
        if(this.entity != null) this.entity.setVisibility(visible);

        synchronized(this){
            if(this.filled = true){
                if(this.canDisplayUnfilledDecoration) this.unfilledDecoration.setVisibility(false);
                enumeration.add(this);
            }else{
                if(this.canDisplayUnfilledDecoration) this.unfilledDecoration.setVisibility(true);
                enumeration.remove(this);
            }
        }
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
        if(e instanceof Player){
            this.player = (Player)e;
            setEntityPositions();
            e.setVisibility(filled);
            return true;
        }
        if(this.entity != null) return false;
        this.entity = e;
        setEntityPositions();
        entity.setVisibility(filled);
        return true;
    }

    public Entity getEntity() {
        return entity;
    }

    public void removeEntity() {
        this.entity.setVisibility(false);
        entity = null;
        setEntityPositions();

    }
    public void removePlayer() {
        player = null;
        setEntityPositions();

    }
    public boolean isDebug(){
        return debug;
    }

    public boolean full(){
        return entity != null && player != null;
    }


    private void setEntityPositions(){
        if(player != null && entity != null){
            player.setPos(x - 75, y - 50);
            entity.setPos(x + 75, y - 50);
        }else if(player != null){
            player.setPos(x, y - 50);
        }else if(entity != null){
            entity.setPos(x, y - 50);
        }

    }

    public void debug(){
            if(debugTexture == null){
                debugTexture = WindowManager.getInstance().newGraphic(width, height);
                debugTexture.beginDraw();
                debugTexture.stroke(255);
                debugTexture.fill(200, 10, 10);
                debugTexture.ellipse(debugTexture.width/2, debugTexture.height/2, debugTexture.width - 2, debugTexture.height - 2);
                debugTexture.endDraw();
            }
            debug = true;
    }


    public void replaceEntities(){
        if(entity != null) wm.put(entity);
    }

}
