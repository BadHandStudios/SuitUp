package com.badhand.suitup.assets;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class FilePlayer {
    private String MP3File;
    private AdvancedPlayer jlPlayer;
    private boolean loop = true;

    public FilePlayer(String MP3File) {
        this.MP3File = MP3File;
    }

    public void play() {
        try {
            FileInputStream fileInputStream = new FileInputStream(MP3File);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new AdvancedPlayer(bufferedInputStream);
        } catch (Exception e) {
            System.out.println("Problem playing mp3 file " + MP3File);
            System.out.println(e.getMessage());
        }

        new Thread() {
            public void run() {
                try {
                    jlPlayer.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();


    }

    public void playLoop() {
        loop = true;
        new Thread  () {
            @Override
            public void run() {
                while(loop) {
                    try {
                        try {
                            FileInputStream fileInputStream = new FileInputStream(MP3File);
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                            jlPlayer = new AdvancedPlayer(bufferedInputStream);
                        } catch (Exception e) {
                            System.out.println("Problem playing mp3 file " + MP3File);
                            System.out.println(e.getMessage());
                        }
                         jlPlayer.play();        //This just play the audio for the first time.
                    } catch (JavaLayerException ex) {
                        
                    }
                }
            } 
         }.start();
    }
    
    public void close() {
        if (jlPlayer != null){
            jlPlayer.close();
            loop = false;
        } 
    }
}
