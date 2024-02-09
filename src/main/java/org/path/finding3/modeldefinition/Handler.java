package org.path.finding3.modeldefinition;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    public LinkedList<GameObject>allGameObjects = new LinkedList<>();

    public void tick(){
        if(this.allGameObjects.size() > 0){
            for(int i = 0; i < this.allGameObjects.size(); i++){
                this.allGameObjects.get(i).tick();
            }
        }
    }

    public void render(Graphics g){
        if(this.allGameObjects.size() > 0){
            for(int i = 0; i < this.allGameObjects.size(); i++){
                this.allGameObjects.get(i).render(g);
            }
        }
    }

    public void addObjectToList(GameObject tempObject){
        if(!this.allGameObjects.contains(tempObject)){
            this.allGameObjects.add(tempObject);
        }
    }

    public GameObject returnObjectFromList(GameObject tempObject){
        if(this.allGameObjects.contains(tempObject)){
            for(int i = 0; i < this.allGameObjects.size(); i++){
                if(tempObject.getId() == this.allGameObjects.get(i).id){
                    return this.allGameObjects.get(i);
                }
            }
        }
        return null;
    }


    public void removeObjectFromList(GameObject tempObject){
        if(this.allGameObjects.stream().count() > 0){
            if(this.allGameObjects.contains(tempObject)){
                this.allGameObjects.remove(tempObject);
            }
        }
    }
}
