package com.badhand.suitup.game.Scenes;

import com.badhand.suitup.assets.AssetManager;
import com.badhand.suitup.events.*;
import com.badhand.suitup.game.*;
import com.badhand.suitup.ui.*;

import processing.core.*;

public class Splash implements Scene {
    private ImageElement logo;
    private GraphicsWrapper fade;
    private float opacity = -400;

    private static AssetManager am = AssetManager.getInstance();
    private static WindowManager wm = WindowManager.getInstance();
    private static EventManager em = EventManager.getInstance();



    public void initialize() {
        logo = new ImageElement("logo", 1920/2, 1080/2, 400, 400, am.getImage("badhand.png"));
        wm.setBackground(new Color(255,255,255));
        PGraphics f = wm.newGraphic(1920, 1080);
        f.beginDraw();
        f.background(255, 255, 255, 0);
        f.endDraw();

        fade = new GraphicsWrapper(f, 1920/2, 1080/2);

        wm.put(logo);
        wm.put(fade);



    }


    public void update() {
        if(opacity < 255) {
            opacity += 2;
            fade.getTexture().background(255, 255, 255, opacity);
        }else if(opacity != 999){
            opacity = 999;
            em.push(new Event(Events.SCENE_CHANGE, GameState.MENU_MAIN))
        }
    }

    public void handle(Event e) {
        switch(e.getType()){
            case KEY_PRESS:
                em.push(new Event(Events.SCENE_CHANGE, GameState.MENU_MAIN));
                break;
            default:
                break;
        }
    }

}
