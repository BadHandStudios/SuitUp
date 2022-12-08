package com.badhand.suitup.game;

import com.badhand.suitup.events.*;

public interface Scene {
    
    public void initialize();

    public void update();
    
    public void handle(Event e);
}
