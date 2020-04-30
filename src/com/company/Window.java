package com.company;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        setTitle("Pond Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GamePanel(GamePanel.getWindowWidth(), GamePanel.getWindowHeight()));
        pack();
        setLocationRelativeTo(null);
    }

}
