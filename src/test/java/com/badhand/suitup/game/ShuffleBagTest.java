package com.badhand.suitup.game;

import com.badhand.suitup.ui.Card;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShuffleBagTest {
    static ShuffleBag<String> testBag;
    @Test
    void add() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void next() {
        String msg = "Pass if next returns the item";
        testBag.add("foo");
        assertEquals("foo", testBag.next(), msg);
        msg = "Pass if next reshuffles and returns the item";
        assertEquals("foo", testBag.next(), msg);
    }

    @Test
    void peek() {
    }

    @Test
    void reshuffle() {
    }

    @Test
    void search() {
        String msg = "Pass if shuffle bag finds an item";
        String testString = "foo";
        testBag.add(testString);
        assertEquals(testString, (String)testBag.search("foo"), msg);
        msg = "Pass if shuffle bag fails to find an item";
        assertNull((String) testBag.search("bar"), msg);
    }

    @Test
    void empty() {
        testBag.clear();
        String msg = "Pass if returns true when bag is empty";
        assertTrue(testBag.empty(), msg);

        msg = "Pass if returns false when bag is not empty";
        testBag.add("foo");
        assertFalse(testBag.empty(), msg);
    }

    @Test
    void clear() {
    }

    @BeforeEach
    void beforeEach(){
        testBag = new ShuffleBag<String>();
    }
}