package com.badhand.suitup.ui;

import processing.core.PGraphics;

import java.util.*;

public interface GUI {

    public int getWidth();
    public int getHeight();

    // Position of the GUI relative to the screen
    public int getX();
    public int getY();
    public void setPos(int x, int y);

    public PGraphics getTexture(); // Returns a texture of the gui element with a transparent background
    public boolean visible(); // Returns whether or not the element should be drawn
    public void setVisibility(boolean visible); // Sets the visibility of the element

    public boolean click(int mouseX, int mouseY); // Returns true if the GUI element was clicked

    public String getName(); // Returns the name of the GUI element

    public List<GUI> enumerate(); // Returns an ordered list of all GUI elements contained within this GUI element
}
