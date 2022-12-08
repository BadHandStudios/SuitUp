package com.badhand.suitup.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EffectTest {
    static Effect testEffect;

    @Test
    void upgradeTest() {
    }

    @Test
    void getEffectTest() {
    }

    @Test
    void getValueTest() {
    }

    @Test
    void setValueTest() {
        String msg = "Pass if set value succeeds";
        float expected = 5f;
        testEffect.setValue(expected);
        assertEquals(expected, testEffect.getValue(), msg);
    }

    @BeforeAll
    static void setUp(){
        testEffect = new Effect(Effects.HEAL);
    }
}