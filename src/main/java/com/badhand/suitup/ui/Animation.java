package com.badhand.suitup.ui;

public interface Animation {

    public void update(); // Called every frame

    public void setSpeed(int speed); // Set the speed of the animation

    public void pause(); // Pause the animation

    public void resume(); // Resume the animation

    public void stop(); // Resets the animation
    
}
