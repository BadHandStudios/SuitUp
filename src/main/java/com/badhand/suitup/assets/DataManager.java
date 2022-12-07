package com.badhand.suitup.assets;

import java.io.FileWriter;

import org.json.*;
import org.json.simple.parser.JSONParser;

public class DataManager {
    private static DataManager instance;

    private static AssetManager am = AssetManager.getInstance();

    private JSONObject saveData;

    private DataManager(){
        String saveFileName = am.getJSON("save.json");

        JSONParser jsonParser = new JSONParser();
        try{
            saveData = (JSONObject) jsonParser.parse(saveFileName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static DataManager getInstance(){
        if(instance == null) instance = new DataManager();

        return instance;
    }

    public void saveData(String key, String value){
        saveData.put(key, value);
    }
    public void saveData(String key, int value){
        saveData.put(key, value);
    }

    public int getInt(String key){
        return (int) saveData.get(key);
    }

    public String getString(String key){
        return (String) saveData.get(key);
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
