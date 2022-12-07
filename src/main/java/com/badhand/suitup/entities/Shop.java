package com.badhand.suitup.entities;

import com.badhand.suitup.assets.AssetManager;

public class Shop extends Entity {
    private static AssetManager am = AssetManager.getInstance();

    public Shop(){
        super(am.getImage("shop.png"), 0, 0, 100, 120);
        wm.registerDiffered(this);
        wm.put(this);
    }
}
