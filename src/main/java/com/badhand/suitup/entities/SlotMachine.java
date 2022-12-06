package com.badhand.suitup.entities;


import com.badhand.suitup.assets.AssetManager;




public class SlotMachine extends Entity {
    private static AssetManager am = AssetManager.getInstance();


    public SlotMachine() {
        super(am.getImage("slotmachine_big.png"), 0, 0, 100, 120);
        // enumeration = new LinkedList<GUI>();
        // enumeration.add(texture);
        wm.registerDiffered(this);
        wm.put(this);
    }

}
