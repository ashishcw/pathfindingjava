package org.path.finding3.input;

import org.path.finding3.objects.Node;
import org.path.finding3.pathfinding.Pathfinding;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    Node node;

    Pathfinding pathfinding = null;

    public KeyHandler(Node node) {
        this.node = node;
        pathfinding = new Pathfinding();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            pathfinding.search(this.node);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
