package com.badhand.suitup.assets;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import org.json.*;

public class DataManager {
    private static DataManager instance;

    private static AssetManager am = AssetManager.getInstance();

    private JSONObject saveData;

    private DataManager(){
        String saveFileName = am.getJSON("save.json");
        Scanner scanner;
        try{
            scanner = new Scanner(new File(saveFileName));
        }catch(Exception e){
            System.out.println("Save file not found. Creating new save file.");
            saveData = new JSONObject();
            save();
            return;
        }
        String saveText = "";
        while(scanner.hasNextLine()){
            saveText += scanner.nextLine();
        }
        scanner.close();
        System.out.println(saveText);

        saveData = new JSONObject(saveText);

    }

    public static DataManager getInstance(){
        if(instance == null) instance = new DataManager();

        return instance;
    }

    public void saveData(String key, String value){
        saveData.put(key, value);
        save();
    }
    public void saveData(String key, int value){
        saveData.put(key, value);
        save();
    }

    public int getInt(String key){
        try {
            return (int) saveData.get(key);
        }catch(Exception e){
            return 0;
        }
    }

    public String getString(String key){
        try {
            return (String) saveData.get(key);
        }catch(Exception e){
            return "";
        }
    }

    private void save(){
        String saveFileName = am.getJSON("save.json");
        String saveText = saveData.toString();

        try{
            FileWriter file = new FileWriter(saveFileName);
            file.write(saveText);
            file.flush();
            file.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void clearSave(){
        saveData = new JSONObject();
        save();
    }
}
