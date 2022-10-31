package com.badhand.suitup.assets;

import java.util.*;
import java.io.*;

public class AssetManager {
    private static AssetManager single_instance = null;
    private HashMap<String, File> images = null;
    private HashMap<String, File> sounds = null;
    private FilePlayer player;

    private AssetManager() {
        File dir = new File( "src\\main\\java\\com\\badhand\\suitup\\assets\\Resources\\images");
        File[] files = dir.listFiles();

        images = new HashMap<>(files.length + 1, 1);

        for (int i = 0; i < files.length; i++) {
            String hold = files[i].getName();
            if(hold != null && hold.contains(".")){
                hold.substring(0, hold.lastIndexOf('.'));
            }
            images.put(hold, files[i]);
        }

        dir = new File( "src\\main\\java\\com\\badhand\\suitup\\assets\\Resources\\sounds");
        files = dir.listFiles();

        sounds = new HashMap<>(files.length + 1, 1);

        for (int i = 0; i < files.length; i++) {
            String hold = files[i].getName();
            if(hold != null && hold.contains(".")){
                hold.substring(0, hold.lastIndexOf('.'));
            }
            sounds.put(hold, files[i]);
        }
    }

    public static AssetManager getInstance() {
        if (single_instance == null) {
            single_instance = new AssetManager();
        }
        return single_instance; 
    }

    public File getImage(String fileName) {
        return images.get(fileName);
    }

    public void playSound(String fileName) {
        player = new FilePlayer(sounds.get(fileName));
        player.play();
    }

    public void stopSound() {
        player.close();
    }
    
}
