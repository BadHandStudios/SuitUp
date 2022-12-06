package com.badhand.suitup.game;

import com.badhand.suitup.entities.SlotMachine;
import com.badhand.suitup.ui.map.Node;

public class NodeFactory {
    private static NodeFactory instance;

    private static EnemyFactory ef = EnemyFactory.getInstance();

    public static int level = 1;

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
                break;
            case 2:
                nodeContents.add(0, 2); // Nothing
                nodeContents.add(1, 2); // Slot Machine
                nodeContents.add(2, 3); // Enemies
            case 3:
                nodeContents.add(0, 2); // Nothing
                nodeContents.add(1, 2); // Slot Machine
                nodeContents.add(2, 4); // Enemies
            default:
                break;

        }
        

    }

    public Node randomNode(int i, int j, int level){
        if(this.level != level) this.setLevel(level);
        Node n = new Node(i, j);
        switch(nodeContents.next()){
            case 1:
                n.setEntity(new SlotMachine());
                break;
            case 2:
                n.setEntity(ef.getEnemy(1, 1));
                break;
            default:
        }

        return n;

    }

}
