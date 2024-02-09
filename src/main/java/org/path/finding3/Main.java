package org.path.finding3;

import org.path.finding3.display.Window;
import org.path.finding3.input.KeyHandler;
import org.path.finding3.modeldefinition.GameObject;
import org.path.finding3.modeldefinition.Handler;
import org.path.finding3.objects.Node;
import org.path.finding3.pathfinding.Pathfinding;
import org.path.finding3.utils.Constants;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {
    private Window window;

    private Handler handler;

    private Thread thread;

    private boolean isRunning = false;

    private Node node = new Node();

    private KeyHandler keyHandler;

    public Main(){
        this.window = new Window(Constants.WIDTH, Constants.HEIGHT, this);
        init();
    }


    public static void main(String[] args) {
        new Main();
    }

    private void init(){
        if(this.thread == null){
            this.thread = new Thread(this, "Additional_Thread_1");
        }

        //handler instantiation
        if(this.handler == null){
            this.handler = new Handler();
        }

        //Node instantiation
        if(this.node == null){
            this.node = new Node();
        }
        //create grid
        this.node.createNodeGrid();
        this.node.initialization();

        for(int i = 0; i < this.node.nodes.length; i++){
            for(int j = 0; j < this.node.nodes[i].length; j++){
                this.handler.addObjectToList(this.node.nodes[i][j]);
                //System.out.println("Row : " + this.node.nodes[i][j].getRow() + " Col : " + this.node.nodes[i][j].getCol());
            }
        }

        if(this.keyHandler == null){
            this.keyHandler = new KeyHandler(this.node);
        }
        this.addKeyListener(this.keyHandler);


        this.requestFocus();



        start();
    }

    private void start(){
        if(this.isRunning){
            return;
        }

        if(this.thread != null){
            this.thread.start();
        }
        this.isRunning = true;
    }

    private void stop(){
        if(!this.isRunning){
            return;
        }

        if(this.thread != null){
            try{
                this.thread.join();
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        this.isRunning = false;
    }

    private void mainLoop(){
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        long lastLoopTime = System.nanoTime();
        long lastFpsTime = 0;

        while (this.isRunning) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;

            double delta = updateLength / ((double) OPTIMAL_TIME);

            lastFpsTime += updateLength;
            if (lastFpsTime >= 1000000000) {
                lastFpsTime = 0;
            }

            // Update game logic based on delta
            tick(delta);

            // Repaint or render the game
            render();

            try {
                // Sleep to maintain frame rate
                long gameTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                Thread.sleep(gameTime);
            } catch (Exception e) {
                // Handle exceptions
                this.isRunning = false;
            }
        }
        stop();
    }

    @Override
    public void run(){
        mainLoop();
    }

    private void tick(double delta){
        //master tick method
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(03);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        //other render calls go here

        //handler render
        if(this.handler != null){
            this.handler.render(g);
        }

        bs.show();
        g.dispose();
    }


}