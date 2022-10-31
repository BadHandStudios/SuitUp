package com.badhand.suitup.assets;

import java.io.*;
import javazoom.jl.player.*;

public class FilePlayer {
    private File MP3File;
    private Player jlPlayer;

    public FilePlayer(File MP3File) {
        this.MP3File = MP3File;
    }

    public void play() {
        try {
            FileInputStream fileInputStream = new FileInputStream(MP3File);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
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
    
    public void close() {
        if (jlPlayer != null) jlPlayer.close();
    }
}
