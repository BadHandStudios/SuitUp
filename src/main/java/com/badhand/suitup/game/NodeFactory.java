package com.badhand.suitup.game;

import com.badhand.suitup.entities.*;
import com.badhand.suitup.ui.map.Node;

public class NodeFactory {
    private static NodeFactory instance;

    private static EnemyFactory ef = EnemyFactory.getInstance();

    public int level = 1;

    private NodeFactory(){
        setLevel(level);
    }

    private ShuffleBag<Integer> nodeContents = new ShuffleBag<Integer>();

    public static NodeFactory getInstance(){
        if(instance == null) instance = new NodeFactory();
        return instance;
    }

    public void setLevel(int level){
        nodeContents.clear();
        switch(level){
            case 1:
                nodeContents.add(0, 3); // Nothing
                nodeContents.add(1, 2); // Slot Machine
                nodeContents.add(2, 3); // Enemies
                nodeContents.add(3, 2); // Hearts
                break;
            case 2:
                nodeContents.add(0, 2); // Nothing
                nodeContents.add(1, 2); // Slot Machine
                nodeContents.add(2, 3); // Enemies
                nodeContents.add(3, 2); // Hearts
            case 3:
                nodeContents.add(0, 2); // Nothing
                nodeContents.add(1, 2); // Slot Machine
                nodeContents.add(2, 4); // Enemies
                nodeContents.add(3, 2); // Hearts
            default:
                break;

        }
        

    }

    public Node randomNode(int i, int j, int level, int episode){
        if(this.level != level) this.setLevel(level);
        Node n = new Node(i, j);
        switch(nodeContents.next()){
            case 1:
                n.setEntity(new SlotMachine());
                break;
            case 2:
                n.setEntity(ef.getEnemy(episode, 1));
                break;
            case 3:
                n.setEntity(new Heart());
                break;
            default:
        }

        return n;

    }

}
