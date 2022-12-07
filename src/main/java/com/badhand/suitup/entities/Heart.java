package com.badhand.suitup.entities;

import com.badhand.suitup.assets.AssetManager;

public class Heart extends Entity {
    private static AssetManager am = AssetManager.getInstance();

    public Heart() {
        super(am.getImage("heart.png"), 0, 0, 100, 100);
        wm.registerDiffered(this);
        wm.put(this);
    }
    
}
