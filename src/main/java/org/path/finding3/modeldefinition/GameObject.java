package org.path.finding3.modeldefinition;

import java.awt.*;
import java.util.UUID;

public abstract class GameObject {
    public enum objectType{
        start,
        end,
        block,
        normal,
        validPath,
        checked
    }

    protected UUID id;
    protected int xPos, yPos;
    protected int sizeWidth, sizeHeight;
    protected int col, row;
    protected objectType objectType;
    protected Color color;

    public GameObject(){

    }

    public GameObject(int xPos, int yPos, int sizeWidth, int sizeHeight, int col, int row, GameObject.objectType objectType, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
        this.col = col;
        this.row = row;
        this.objectType = objectType;
        this.color = color;
        this.id = UUID.randomUUID();
    }

    protected abstract void tick();
    protected abstract void render(Graphics g);

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(int sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public int getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(int sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public GameObject.objectType getObjectType() {
        return objectType;
    }

    public void setObjectType(GameObject.objectType objectType) {
        this.objectType = objectType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public UUID getId() {
        return id;
    }
}
