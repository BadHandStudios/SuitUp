package com.badhand.suitup.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void rgbTest(){
        int r = 0;
        int g = 0;
        int b = 0;
        Color color = new Color(r,g,b);
        int actualRGB[] = color.getRGB();
        String msg = "Pass if getRGB returns the correct red color";
        assertEquals(r, actualRGB[0], msg);
        msg = "Pass if getRGB returns the correct green color";
        assertEquals(g, actualRGB[1], msg);
        msg = "Pass if getRGB returns the correct blue color";
        assertEquals(b, actualRGB[2], msg);
    }

    @Test
    void rgbaTest() {
        String msg = "Pass if getRGB returns the correct red color";
        int r = 255;
        int g = 255;
        int b = 255;
        int a = 255;
        Color color = new Color(r,g,b,a);
        int actualRGB[] = color.getRGB();
        assertEquals(r, actualRGB[0], msg);
        msg = "Pass if getRGB returns the correct green color";
        assertEquals(g, actualRGB[1], msg);
        msg = "Pass if getRGB returns the correct blue color";
        assertEquals(b, actualRGB[2], msg);
    }

    @Test
    void colorIntTest(){
        int c = 255;
        Color color = new Color(c);
        int actualRGB[] = color.getRGB();
        String msg = "Pass if getRGB returns the correct red color";
        assertEquals(c, actualRGB[0], msg);
        msg = "Pass if getRGB returns the correct green color";
        assertEquals(c, actualRGB[1], msg);
        msg = "Pass if getRGB returns the correct blue color";
        assertEquals(c, actualRGB[2], msg);
    }

    @Test
    void colorColorTest(){
        Color initialColor = new Color(255,255,255);
        Color color = new Color(initialColor);
        int actualRGB[] = color.getRGB();
        String msg = "Pass if getRGB returns the correct red color";
        assertEquals(255, actualRGB[0], msg);
        msg = "Pass if getRGB returns the correct green color";
        assertEquals(255, actualRGB[1], msg);
        msg = "Pass if getRGB returns the correct blue color";
        assertEquals(255, actualRGB[2], msg);
    }
    @Test
    void averageTest() {
        int r = 255;
        int g = 255;
        int b = 255;

        Color black = new Color(0,0,0);
        Color color = new Color(r,g,b);

        int expR = 255 / 2;
        int expG = 255 / 2;
        int expB = 255 / 2;

        color = color.average(black);

        int actualRGB[] = color.getRGB();
        String msg = "Pass if average correctly averaged the color value";
        assertEquals(expR, actualRGB[0], msg);
        assertEquals(expG, actualRGB[1], msg);
        assertEquals(expB, actualRGB[2], msg);
    }

    @Test
    void averagePcTest(){
        Color color = new Color(255, 255, 255);

        int expR = 255 / 2;
        int expG = 255 / 2;
        int expB = 255 / 2;

        color = color.average(0);

        int actualRGB[] = color.getRGB();
        String msg = "Pass if average correctly averaged the color value";
        assertEquals(expR, actualRGB[0], msg);
        assertEquals(expG, actualRGB[1], msg);
        assertEquals(expB, actualRGB[2], msg);
    }

    @Test
    void toProcessingColorTest() {
        String msg = "Pass if color is correctly converted";
        Color color = new Color(255,255,255);
        int actualRGB[] = color.getRGB();
        int r = actualRGB[0];
        int g = actualRGB[1];
        int b = actualRGB[2];
        int a = 255;
        int result = b;
        result += (g << 8);
        result += (r << 16);
        result += (a << 24);

        int expected = color.toProcessingColor();
        assertEquals(expected, result, msg);
    }

    @Test
    void toStringTest(){
        String msg = "Pass if color toString returns proper format";
        Color color = new Color(255,255,255);
        String name = color.toString();
        String expected = "R: 255, G: 255, B: 255";
        assertEquals(expected, name, msg);
    }
}
