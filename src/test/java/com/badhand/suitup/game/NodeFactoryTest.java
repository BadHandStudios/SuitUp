package com.badhand.suitup.game;

import com.badhand.suitup.ui.map.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeFactoryTest {
    static NodeFactory nodeFactory;
    @Test
    void setLevelTest() {
        String msg = "Pass if level is successfully set";
        int expected = 1;
        nodeFactory.setLevel(expected);
        assertEquals(expected, nodeFactory.level, msg);

        expected = 2;
        nodeFactory.setLevel(expected);
        assertEquals(expected, nodeFactory.level, msg);

        expected = 3;
        nodeFactory.setLevel(expected);
        assertEquals(expected, nodeFactory.level, msg);

        expected = 0;
        nodeFactory.setLevel(expected);
        assertEquals(expected, nodeFactory.level, msg);
    }

    @Test
    void randomNodeTest() {
        String msg = "Pass if node is not null";
        Node testNode = nodeFactory.randomNode(1,1,1,1);
        assertNotNull(testNode);
    }

    @BeforeAll
    static void setup(){
        nodeFactory = NodeFactory.getInstance();
    }
}