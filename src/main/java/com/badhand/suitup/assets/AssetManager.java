package com.badhand.suitup.assets;

import com.badhand.suitup.*;
import com.badhand.suitup.ui.WindowManager;

import java.util.*;
import java.io.*;
import processing.core.*;

public class AssetManager {
    private static AssetManager single_instance = null;
    private HashMap<String, File> images = null;
    private HashMap<String, File> sounds = null;
    private FilePlayer player;
    private WindowManager winManager = WindowManager.getInstance();

    private AssetManager() {
        File dir;
        File[] files;
        try {
            dir = new File(SuitUp.class.getResource("/images/").toURI().getPath());
            files = dir.listFiles();

            images = new HashMap<>(files.length + 1, 1);

            for (int i = 0; i < files.length; i++) {
                String hold = files[i].getName();
                images.put(hold, files[i]);
            }

            dir = new File(SuitUp.class.getResource("/sounds/").toURI().getPath());
            files = dir.listFiles();

            sounds = new HashMap<>(files.length + 1, 1);

            for (int i = 0; i < files.length; i++) {
                String hold = files[i].getName();
                sounds.put(hold, files[i]);
            }
        }
        catch(Exception e) {
            System.out.println("Failed to grab images folder");
        }
        
        try {
            dir = new File(SuitUp.class.getResource("/sounds/").toURI().getPath());
            files = dir.listFiles();

            sounds = new HashMap<>(files.length + 1, 1);

            for (int i = 0; i < files.length; i++) {
                String hold = files[i].getName();
                if(hold != null && hold.contains(".")){
                    hold.substring(0, hold.lastIndexOf('.'));
                }
                sounds.put(hold, files[i]);
            }
        } catch (Exception e) {
            System.out.println("Failed to grab sounds folder");
        }
    }

    public static AssetManager getInstance() {
        if (single_instance == null) {
            single_instance = new AssetManager();
        }
        return single_instance; 
    }

    public PImage getImage(String fileName) {
        return winManager.importImage(images.get(fileName).getPath());
    }

    public void playSound(String fileName) {
        try {
            player = new FilePlayer(sounds.get(fileName).getPath());
            player.play();
        } catch (Exception e) {
            System.out.println("Failed to grab mp3 folder");
        }
    }

    public void stopSound() {
        player.close();
    }
    
}
