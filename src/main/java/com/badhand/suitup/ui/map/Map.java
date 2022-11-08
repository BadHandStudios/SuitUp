package com.badhand.suitup.ui.map;

import com.badhand.suitup.ui.*;

import java.util.*;

import javax.naming.ldap.ManageReferralControl;

import processing.core.*;


public class Map implements GUI {

    private ArrayList<Node[]> columns = new ArrayList<Node[]>();

    private final int INITIAL_SIZE = 8;

    private int maxMoves = 100;
    private int movesRemaining = maxMoves;
    private int viewX = 0;

    boolean panning = false;
    private int panSpeed = 10;
    private int panAmount;
    private int panWidth;

    private LinkedList<GUI> enumeration;

    private int x, y, width, height;

    int viewportOffsetX = 0;

    private int nodeWidth, nodeHeight;
    private int nodePaddingX, nodePaddingY;

    private static WindowManager wm = WindowManager.getInstance();

    private Node mainPath;

    private Random rand = new Random();

    public Map() {


        // Create first column
        Node[] column = new Node[3];
        for (int i = 0; i < column.length; i++) {
            column[i] = new Node(i, 0);
        }
        mainPath = column[1];
        mainPath.setFilled(true);
        columns.add(column);

        // Add additional columns until size == INITIAL_SIZE
        for (int i = 1; i < INITIAL_SIZE; i++) {
            addColumn();
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

    

        updateViewport();

    }

    private Node getNode(int i, int j){
        return columns.get(j)[i];
    }

    public void pan(boolean direction){
        if(!direction){
            if(viewX < columns.size() - 5){
                viewX++;
                if(viewX == columns.size() - 5){
                    addColumn();
                }
                updateViewport();


            }
        } else {
            if(viewX > 0){
                viewX--;
                updateViewport();
            }
        }
        
    }
    private void addColumn(){
        Node[] prevCol = columns.get(columns.size() - 1);
        Node[] col = new Node[3];

        for (int r = 0; r < col.length; r++) {
            col[r] = new Node(r, columns.size());
        }
        columns.add(col);

        for(int i = 0; i < prevCol.length; i++){
            if(!prevCol[i].isFilled()) continue;
            randomizeEdges(prevCol[i]);
            for(int edge = 0; edge < 4; edge++){
                if(prevCol[i].getEdge(edge)){
                   followEdge(prevCol[i], edge).setFilled(true);
                }
            }
        }

        if(mainPath.connectingEdges() == 0){
            int edge;
            switch(mainPath.getMapRow()){
                case 0:
                    edge = rand.nextBoolean() ? 1 : 2;
                    break;
                case 1:
                    edge = rand.nextInt(3);
                    break;
                case 2:
                    edge = rand.nextBoolean() ? 0 : 1;
                    break;                    
                default:
                    edge = 1;
            }
            mainPath.setEdge(edge, true);
            mainPath = followEdge(mainPath, edge);
            mainPath.setFilled(true);
            

        }else{
            do{
                int edge = rand.nextInt(3);
                if(mainPath.getEdge(edge)){
                    mainPath = followEdge(mainPath, edge);
                    mainPath.setFilled(true);
                    break;
                }
            }while(true);
        }
        
    }

    private Node followEdge(Node node, int edge){
        int i = node.getMapRow();
        int j = node.getMapColumn();
        switch(edge){
            case 0:
                return getNode(i - 1, j + 1);
            case 1:
                return getNode(i, j + 1);
            case 2:
                return getNode(i + 1, j + 1);
            case 3:
                return getNode(i + 1, j);
            default:
                return null;
        }
    }
    private void randomizeEdges(Node node){
        for(int edge = 0; edge < 4; edge++){
            if(rand.nextInt(100) < 40){
                node.setEdge(edge, true);
            }
        }
        if(node.getMapRow() == 0){
            node.setEdge(0, false);
        }else if(node.getMapRow() == 2){
            node.setEdge(2, false);
            node.setEdge(3, false);
        }
    }

    private void updateViewport(){
        this.enumeration = new LinkedList<GUI>();
        ArrayList<Node[]> vp = getViewPort(viewX);
        for(Node[] j : vp){
            for(Node n : j){
                n.setPos(x + nodeWidth * (n.getMapColumn() - viewX) + nodePaddingX * (n.getMapColumn() - viewX), y + nodeHeight * n.getMapRow() + nodePaddingY * n.getMapRow());
            }
        }

        for(Node[] c : vp) {
            if(vp.indexOf(c) == vp.size() - 1) continue;
            for (Node n : c) {
                for(int i = 0; i < 4 && n.getMapColumn() < columns.size() - 1; i++){
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

        for (Node[] j: vp) {
            for (Node n : j) {
                enumeration.addAll(n.enumerate());
            }
        }
    }

    public int getViewX() {
        return viewX;
    }

    public ArrayList<Node[]> getViewPort(int j) {
        ArrayList<Node[]> viewPort = new ArrayList<Node[]>();
        if(j > 0) j--;
        for (int i = j; i < j+5 && i < columns.size(); i++) {
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