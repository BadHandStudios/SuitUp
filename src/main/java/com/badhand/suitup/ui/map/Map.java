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

    private int nodeWidth, nodeHeight;
    private int nodePaddingX, nodePaddingY;

    private static WindowManager wm = WindowManager.getInstance();



    public Map() {



        // Populate the map with empty nodes
        for (int c = 0; c < INITIAL_SIZE; c++) {
            Node[] col = new Node[3];
            for (int r = 0; r < col.length; r++) {
                col[r] = new Node(r, c);
                col[r].setVisibility(true);
            }
            columns.add(col);
        }

        // Initialize enumeration
        enumeration = new LinkedList<GUI>();

        
        nodeWidth = columns.get(0)[0].getWidth();
        nodeHeight = columns.get(0)[0].getHeight();
        nodePaddingX = nodeWidth;
        nodePaddingY = nodeHeight;

        width = nodeWidth * 2 + nodePaddingX * 2;
        height = nodeHeight * 2 + nodePaddingY * 2;

        x = wm.getWidth()/2 - width/2;
        y = wm.getHeight()/2 - height/2;

        // Add nodes to enumeration
    
        for(Node[] j : columns){
            for(Node n : j){
                n.setPos(x + nodeWidth * n.getMapColumn() + nodePaddingX * n.getMapColumn(), y + nodeHeight * n.getMapRow() + nodePaddingY * n.getMapRow());
                if(n.getMapRow() > 0){
                    n.setEdge(0, true);
                }
                if(n.getMapRow() < 2){
                    n.setEdge(2, true);
                }
                if(n.getMapRow() < 2){
                    n.setEdge(3, true);
                }
                n.setEdge(1, true);

            }
        }

        for(Node[] c : getViewPort(viewX)) {
            for (Node n : c) {
                for(int i = 0; i < 4; i++){
                    if(n.getEdge(i)){
                        switch(i){
                            case 0:
                                // i - 1, j + 1
                                Node connection = getNode(n.getMapRow() - 1, n.getMapColumn() + 1);
                                LineElement line = new LineElement(n.getX(), n.getY(), connection.getX(), connection.getY(), 3, new Color(255, 255, 255));
                                enumeration.add(line);
                                break;
                            case 1:
                                // i, j + 1
                                connection = getNode(n.getMapRow(), n.getMapColumn() + 1);
                                line = new LineElement(n.getX(), n.getY(), connection.getX(), connection.getY(), 3, new Color(255, 255, 255));
                                enumeration.add(line);
                                break;
                            case 2:
                                // i + 1, j + 1
                                connection = getNode(n.getMapRow() + 1, n.getMapColumn() + 1);
                                line = new LineElement(n.getX(), n.getY(), connection.getX(), connection.getY(), 3, new Color(255, 255, 255));
                                enumeration.add(line);
                                break;
                            case 3:
                                // i + 1, j
                                connection = getNode(n.getMapRow() + 1, n.getMapColumn());
                                line = new LineElement(n.getX(), n.getY(), connection.getX(), connection.getY(), 3, new Color(255, 255, 255));
                                enumeration.add(line);
                                break;

                        }
                    }
                }
            }
        }

        for (Node[] j: getViewPort(viewX)) {
            for (Node n : j) {
                enumeration.addAll(n.enumerate());
            }
        }



    }

    private Node getNode(int i, int j){
        return columns.get(j)[i];
    }

    public void pan(int x) {
        viewX += x;
    }

    public int getViewX() {
        return viewX;
    }

    public ArrayList<Node[]> getViewPort(int j) {
        ArrayList<Node[]> viewPort = new ArrayList<Node[]>();
        for (int i = j; i < j + 3 ; i++) {
            viewPort.add(columns.get(i));
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
