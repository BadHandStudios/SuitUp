package com.badhand.suitup.ui.map;

import com.badhand.suitup.ui.*;

import java.util.*;
import processing.core.*;


public class Map implements GUI {

    private ArrayList<Node[]> columns = new ArrayList<Node[]>();

    private final int INITIAL_SIZE = 4;

    private int viewX = 0;

    private LinkedList<GUI> enumeration;

    private int x, y, width, height;



    public Map() {

        width = 1920;
        height = 900;
        x = 0;
        y = 1080 - height;

        // Populate the map with empty nodes
        for (int i = 0; i < INITIAL_SIZE; i++) {
            Node[] c = new Node[3];
            for (int j = 0; j < c.length; j++) {
                c[j] = new Node(i, j);
                c[j].setVisibility(true);
            }
            columns.add(c);
        }

        // Initialize enumeration
        enumeration = new LinkedList<GUI>();

        // Add nodes to enumeration
        for (Node[] c : columns) {
            for (Node n : c) {
                enumeration.add(n);
            }
        }

        for(Node[] c : getViewPort(viewX)){
            for(Node n : c){
                n.setPos(n.getMapX() * 128, n.getMapY() * 80);
            }
        }


    }

    public void pan(int x) {
        viewX += x;
    }

    public int getViewX() {
        return viewX;
    }

    public ArrayList<Node[]> getViewPort(int i) {
        ArrayList<Node[]> viewPort = new ArrayList<Node[]>();
        for (int j = i; j < i + 3 ; j++) {
            viewPort.add(columns.get(j));
        }
        return viewPort;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean click(int x, int y){
        return false;
    }

    public LinkedList<GUI> enumerate() {
        return enumeration;
    }

    public boolean visible(){
        return true;
    }
    public void setVisibility(boolean visible){
        return;
    }

    public PGraphics getTexture() {
        return null;
    }

    public String getName(){
        return "Map";
    }

    
}
