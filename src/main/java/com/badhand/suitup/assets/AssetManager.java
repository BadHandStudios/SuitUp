package com.badhand.suitup.assets;

import com.badhand.suitup.*;
import com.badhand.suitup.ui.WindowManager;

import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.io.*;
import processing.core.*;

public class AssetManager {
    private static AssetManager single_instance = null;
    private HashMap<String, File> assets = null;
    private HashMap<String, File> backupAssets = null;
    private FilePlayer player;
    private WindowManager winManager = WindowManager.getInstance();

    private AssetManager() {
        File dir;
        File[] files;
        try {
            dir = new File(SuitUp.class.getResource("/backupAssets/").toURI().getPath());

            backupAssets = new HashMap<>(4, 1);

            files = dir.listFiles();

            for (int i = 0; i < files.length; i++) {
                String hold = files[i].getName();
                backupAssets.put(hold, files[i]);
            }
        }
        catch(Exception e) {
            System.out.println("Failed to grab backupAssets folder");
        }
        remapAssets();
    }

    public void remapAssets() {
        File soundsDir;
        File imagesDir;
        File fontsDir;
        File JSONDir;
        File[] files;
        try {
            fontsDir = new File(SuitUp.class.getResource("/fonts/").toURI().getPath());
            soundsDir = new File(SuitUp.class.getResource("/sounds/").toURI().getPath());
            imagesDir = new File(SuitUp.class.getResource("/images/").toURI().getPath());
            JSONDir = new File(SuitUp.class.getResource("/JSONFiles/").toURI().getPath());
            int size = fontsDir.list().length + imagesDir.list().length + soundsDir.list().length + JSONDir.list().length;

            assets = new HashMap<>(size + 1, 1);

            for(int j = 0; j < 4; j++) {
                switch (j) {
                    case 0: files = fontsDir.listFiles();
                    break;
                    case 1: files = imagesDir.listFiles();
                    break;
                    case 2: files = soundsDir.listFiles();
                    break;
                    case 3: files = JSONDir.listFiles();
                    break;
                    default: files = new File[0];
                }
                for (int i = 0; i < files.length; i++) {
                    String hold = files[i].getName();
                    assets.put(hold, files[i]);
                }
            }
        }
        catch(Exception e) {
            System.out.println("Failed to grab resources folder");
        }
    }

    public static AssetManager getInstance() {
        if (single_instance == null) {
            single_instance = new AssetManager();
        }
        return single_instance; 
    }

    public PImage getImage(String fileName) {
        File hold = assets.get(fileName);
        if (hold == null) {
            System.out.println("No file by that name!");
        }
        else if(StringUtils.containsAny(fileName, ".gif", ".jpg", ".tga", ".png")) {
            try {
                return winManager.importImage(hold.getPath());
            }catch(Exception e) {
                System.out.println("Failed to importImage!");
            }
        }
        else{
            System.out.println("File not in compatible format!");
        }

        try {
            return winManager.importImage(backupAssets.get("Missing_Texture.ttf").getPath());
        }catch(Exception e) {
            System.out.println("Failed to grab Missing_Texture!");
            return null;
        }
    }

    public String getFont(String fileName) {
        File hold = assets.get(fileName);
        if (hold == null) {
            System.out.println("No file by that name!");
        }
        else if(StringUtils.containsAny(fileName, ".ttf")) {
            try {
                return hold.getPath();
            }catch(Exception e) {
                System.out.println("Failed to grab font!");
            }
        }
        else{
            System.out.println("File not in compatible format!");
        }

        try {
            return backupAssets.get("Missing_Font.ttf").getPath();
        }catch(Exception e) {
            System.out.println("Failed to grab Missing_Text!");
            return null;
        }
    }

    public String getJSON(String fileName) {
        File hold = assets.get(fileName);
        if (hold == null) {
            System.out.println("No file by that name!");
        }
        else if(StringUtils.containsAny(fileName, ".json")) {
            try {
                return hold.getPath();
            }catch(Exception e) {
                System.out.println("Failed to grab JSON file!");
            }
        }
        else{
            System.out.println("File not in compatible format!");
        }
        return null;
    }

    public void playSound(String fileName) {
        File hold = assets.get(fileName);
        if(hold == null) {
            System.out.println("No file by that name!");
            return;
        } 
        else if(StringUtils.containsAny(fileName, ".mp3")) {
            try {
                player = new FilePlayer(hold.getPath());
                player.play();
            } catch (Exception e) {
                System.out.println("Failed to grab mp3 file");
            }
        }
        else {
            System.out.println("File not in compatible format!");
        }
    }

    public void stopSound() {
        player.close();
    }
    
}
