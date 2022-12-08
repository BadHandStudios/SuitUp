package com.badhand.suitup.ui;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import processing.core.PImage;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CaptionedImageTest {
    @Test
    void getTextureTest() {
        String msg = "Pass if texture is null";
        PImage testImage = new PImage();
        String testCaption = "foo";
        CaptionedImage testCI = new CaptionedImage(testImage, testCaption, 0, 0, 128);
        assertNull(testCI.getTexture(), msg);
    }

    @Test
    void setPosTest() {
        PImage testImage = new PImage();
        String testCaption = "foo";
        int initialX = 0;
        int initialY = 0;
        int expectedX = 128;
        int expectedY = 256;
        CaptionedImage testCI = new CaptionedImage(testImage, testCaption, initialX, initialY, 128);
        testCI.setPos(expectedX, expectedY);

        String msg = "Pass if setPos correctly sets X position";
        assertEquals(expectedX, testCI.getX(),msg);

        msg = "Pass if setPos correctly sets Y position";
        assertEquals(expectedY, testCI.getY(),msg);
    }

    @Test
    void getXTest() {
        PImage testImage = new PImage();
        String testCaption = "foo";
        int initialX = 0;
        int initialY = 0;
        int expectedX = 128;
        int expectedY = 256;
        CaptionedImage testCI = new CaptionedImage(testImage, testCaption, initialX, initialY, 128);

        String msg = "Pass if getX returns correct x value";
        assertEquals(initialX, testCI.getX(), msg);

        testCI.setPos(expectedX, expectedY);
        msg = "Pass if getX returns correct x value";
        assertEquals(expectedX, testCI.getX(), msg);
    }

    @Test
    void getYTest() {
        PImage testImage = new PImage();
        String testCaption = "foo";
        int initialX = 0;
        int initialY = 0;
        int expectedX = 128;
        int expectedY = 256;
        CaptionedImage testCI = new CaptionedImage(testImage, testCaption, initialX, initialY, 128);

        String msg = "Pass if getY returns correct y value";
        assertEquals(initialY, testCI.getY(), msg);

        testCI.setPos(expectedX, expectedY);
        msg = "Pass if getY returns correct y value";
        assertEquals(expectedY, testCI.getY(), msg);
    }

    @Test
    void enumerateTest() {
        String msg = "Pass if enumeration is not null";
        PImage testImage = new PImage();
        String testCaption = "foo";
        CaptionedImage testCI = new CaptionedImage(testImage, testCaption, 0, 0, 128);

        LinkedList<GUI> testEnum = testCI.enumerate();
        assertNotNull(testEnum, msg);

    }

    @Test
    void setCaption() {
    }

    @Test
    void click() {
    }

    @Test
    void visible() {
    }

    @Test
    void setVisibility() {
    }

    @Test
    void getWidth() {
    }

    @Test
    void getHeight() {
    }

    @BeforeAll
    static void setUp(){
        WindowManager wm = WindowManager.getInstance();
        wm.createWindow(1920,1080);
        while(!wm.isReady()){}
    }
}