package com.badhand.suitup.game;

import com.badhand.suitup.entities.SlotMachine;
import com.badhand.suitup.ui.map.Node;

public class NodeFactory {
    private static NodeFactory instance;

    private static EnemyFactory ef = EnemyFactory.getInstance();

    private NodeFactory(){
        setLevel(1);
    }

    private ShuffleBag<Integer> nodeContents = new ShuffleBag<Integer>();

    public static NodeFactory getInstance(){
        if(instance == null) instance = new NodeFactory();
        return instance;
    }

    public void setLevel(int level){
        nodeContents.add(0, 3); // Nothing
        nodeContents.add(1, 2); // Slot Machine
        nodeContents.add(2, 3); // Enemies

    }

    public Node randomNode(int i, int j){
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
