package org.path.finding3.objects;

import org.path.finding3.modeldefinition.GameObject;
import org.path.finding3.pathfinding.Pathfinding;
import org.path.finding3.utils.Constants;
import org.path.finding3.utils.MathHelper;

import java.awt.*;
import java.util.List;

public class Node extends GameObject {
    public Node[][] nodes = new Node[Constants.MAX_ROWS][Constants.MAX_COLS];

    public Node parent;

    public Node startNode, currentNode, endNode;

    int gCost = 0, hCost = 0, fCost = 0;

    boolean isVisited = false, open = false, checked = false;

    public Node(){

    }

    public Node(int xPos, int yPos, int sizeWidth, int sizeHeight, int col, int row, GameObject.objectType objectType, Color color) {
        super(xPos, yPos, sizeWidth, sizeHeight, col, row, objectType, color);
        this.isVisited = false;
    }

    @Override
    protected void tick() {

    }

    @Override
    protected void render(Graphics g) {
        if(this.objectType == GameObject.objectType.start){
            this.setColor(Color.YELLOW);
        }else if(this.objectType == GameObject.objectType.end){
            this.setColor(Color.MAGENTA);
        }else if(this.objectType == GameObject.objectType.normal){
            this.setColor(Color.WHITE);
        }else if(this.objectType == GameObject.objectType.block){
            this.setColor(Color.RED);
        }else if(this.objectType == GameObject.objectType.checked){
            this.setColor(Color.ORANGE);
        }else if(this.objectType == GameObject.objectType.validPath){
            this.setColor(Color.GREEN);
        }
        g.setColor(this.getColor());
        g.fillRect(this.getxPos(), this.getyPos(), this.sizeWidth, this.sizeHeight);
    }

    public void createNodeGrid(){
        for(int i = 0; i < this.nodes.length; i++){
            for(int j = 0; j < this.nodes[i].length; j++){
                //new Node(100, 150, 16, 16, 0, 0, GameObject.objectType.start, Color.green)
                this.nodes[i][j] = new Node(
                        i * Constants.NODE_SIZE, //node size x
                        j * Constants.NODE_SIZE, //node size y
                        16,//this.sizeWidth, //size x
                        16,//this.sizeHeight, //size y
                        j, //col
                        i, //row
                        GameObject.objectType.normal,
                        Color.white
                        );
            }
        }
    }

    public void initialization(){
        if(this.startNode == null){
            this.setStartNode(this.nodes[0][0]);
            this.setCurrentNode(this.startNode);
            this.startNode.objectType = GameObject.objectType.start;
            //this.currentNode.objectType = GameObject.objectType.start;
            System.out.println("Start Node Col : " + this.startNode.col);
            System.out.println("Start Node Row : " + this.startNode.row);
        }

        if(this.endNode == null){
            this.setEndNode(this.nodes[Constants.MAX_ROWS - 1][Constants.MAX_COLS - 1]);
            this.endNode.objectType = GameObject.objectType.end;
            System.out.println("End Node Col : " + this.endNode.col);
            System.out.println("End Node Row : " + this.endNode.row);
        }

        for(int i = 0; i < this.nodes.length; i++){
            for (int j = 0; j < this.nodes[i].length; j++){
                if(this.nodes[i][j] == this.startNode || this.nodes[i][j] == this.endNode){
                    continue;
                }
                if(MathHelper.GetRandomNumber(0, 5) > 5){
                    this.nodes[i][j].objectType = GameObject.objectType.block;
                }else {
                    this.nodes[i][j].objectType = GameObject.objectType.normal;
                }
            }
        }


        //setting up the cost
        for(int i = 0; i < this.nodes.length; i++){
            for (int j = 0; j < this.nodes[i].length; j++){
                setNodeCost(this.nodes[i][j], this.startNode, this.endNode);
            }
        }


    }

    public void setNodeCost(Node node, Node startNode, Node endNode){

        //gCost
        int xDistance = Math.abs(node.getCol() - startNode.getCol());
        int yDistance = Math.abs(node.getRow() - startNode.getRow());
        node.setgCost(xDistance + yDistance);

        //hCost
        xDistance = Math.abs(node.getCol() - endNode.getCol());
        yDistance = Math.abs(node.getRow() - endNode.getRow());
        node.sethCost(xDistance + yDistance);

        //fCost
        node.setfCost(Math.abs(node.getgCost() + node.gethCost()));
    }

    public void setAsOpen(){
        this.setOpen(true);
    }

    public void setAsChecked(Node node){
        if(node != this.getStartNode() && node != this.getEndNode()){
            node.setObjectType(GameObject.objectType.checked);
        }
        node.setChecked(true);
    }

    public void setAsPath(Node node){
        if(node != this.startNode && node != this.endNode){
            node.setObjectType(GameObject.objectType.validPath);
        }

    }




    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public int getfCost() {
        return fCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
