package org.path.finding3.display;

import org.path.finding3.Main;
import org.path.finding3.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class Window {
    private int width, height;
    private Main main;

    private JFrame frame;

    public Window(int width, int height, Main mainParam) {
        this.width = width;
        this.height = height;
        this.main = mainParam;
        init();
    }

    private void init(){
        if(this.frame == null){
            this.frame = new JFrame();
        }

        this.frame.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        this.frame.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        this.frame.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));

        this.frame.setTitle(Constants.TITLE);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        this.frame.add(this.main);
        this.frame.pack();

    }


}
