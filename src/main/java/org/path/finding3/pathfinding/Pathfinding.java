package org.path.finding3.pathfinding;

import org.path.finding3.modeldefinition.GameObject;
import org.path.finding3.objects.Node;
import org.path.finding3.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Pathfinding {
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    boolean goalReached = false;

    public void search(Node node){
        if(!this.goalReached){
            int col = node.getCurrentNode().getCol();
            int row = node.getCurrentNode().getRow();

            node.getCurrentNode().setAsChecked(node.getCurrentNode());

            if(!this.checkedList.contains(node.getCurrentNode())){
                this.checkedList.add(node.getCurrentNode());
            }

            if(this.openList.contains(node.getCurrentNode())){
                this.openList.remove(node.getCurrentNode());
            }

            if(row - 1 >= 0){
                //UP
                openNode(node.nodes[col][row-1], node.getCurrentNode());
            }

            if(row + 1 < Constants.MAX_ROWS){
                //DOWN
                openNode(node.nodes[col][row+1], node.getCurrentNode());
            }

            if(col - 1 >= 0){
                //LEFT
                openNode(node.nodes[col-1][row], node.getCurrentNode());

            }

            if(col + 1 < Constants.MAX_COLS){
                //RIGHT
                openNode(node.nodes[col+1][row], node.getCurrentNode());
            }

            node.setCurrentNode(node.nodes[col][row]);

            int bestNodeIndex = 0;
            int bestNodefCost = 9999;

            for(int i = 0; i < openList.size(); i++){
                if(openList.get(i).getfCost() < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).getfCost();
                }else if(openList.get(i).getfCost() == bestNodefCost){
                    if(openList.get(i).getgCost() < openList.get(bestNodeIndex).getgCost()){
                        bestNodeIndex = i;
                    }
                }
            }

            node.setCurrentNode(openList.get(bestNodeIndex));

            if(node.getCurrentNode() == node.getEndNode()){
                backTrackValidPath(node.getCurrentNode());
                goalReached = true;
            }else {
                System.out.println("---------");
                System.out.println("Current Node Row : " + node.getCurrentNode().getRow());
                System.out.println("Current Node Col : " + node.getCurrentNode().getCol());
                System.out.println("---------");

            }
        }
    }

    private void openNode(Node targetNode, Node currentNode){
        if(!targetNode.isOpen() && !targetNode.isChecked() && targetNode.getObjectType() != GameObject.objectType.block){
            targetNode.setAsOpen();
            targetNode.parent = currentNode;
            this.openList.add(targetNode);
        }
    }

    private void backTrackValidPath(Node node){
        if(node.getCurrentNode() != node.getEndNode()){
            node.setCurrentNode(node.getEndNode());
        }

        while (node.getCurrentNode() != node.getStartNode()){
            node.setCurrentNode(node.parent);
            node.setAsPath(node);
        }
    }
}
