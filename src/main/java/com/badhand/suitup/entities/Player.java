package com.badhand.suitup.entities;

import java.util.*;
import com.badhand.suitup.ui.*;

public class Player extends Entity {

    private static Player instance = null;
    private Player() {};
    public static Player getInstance() {
        if(instance == null) instance = new Player();
        return instance;
    }
    
    private int chips;
    private String[] items = {"","","","",""};

    public void setChips(int amount) {
        this.chips = amount;
    }
    public void addChips(int amount) {
        this.chips += amount;
    }
    public void removeChips(int amount) {
        this.chips -= amount;
    }
    public void clearChips(int amount) {
        this.chips = 0;
    }
    public int getChips() {
        return this.chips;
    }
    public void addItem(String item) {
        for (int i = 0; i < 5; i++) {
            if (items[i] == "") {
                items[i] = item;
            }
        }
    }
    public void removeItem(int index) {
        items[index] = "";
        for (int i = 0; i < 5; i++) {
            if (items[i] == "" && items[i+1] != null) {
                items[i] = items[i+1];
                items[i+1] = "";
            }
        }
    }

    public void clearItems() {
        items = new String[]{"","","","",""};
    }

}
