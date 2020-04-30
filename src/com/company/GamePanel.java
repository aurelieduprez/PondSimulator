package com.company;

import com.company.pond.PondManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GamePanel extends JPanel implements ActionListener {

    private static int width = 1280;
    private static int height = 720;

    private final int DELAY = 15;

    private PondManager pm;

    public static int getWindowHeight() {
        return height;
    }

    public static int getWindowWidth() {
        return width;
    }

    public GamePanel(int width, int height) {
        GamePanel.width = width;
        GamePanel.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(new Color(45, 100, 106));
        requestFocus();

        init();
    }

    public void init() {
        Timer timer = new Timer(DELAY, this);
        timer.start();

        pm = PondManager.getSingleton();
        try {
            System.out.println("Loading game assets ...");
            pm.loadAssets();
            System.out.println("Game assets successfully loaded !");
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to load game assets !");
            e.printStackTrace();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pm.update();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render((Graphics2D) g);
    }

    public void render(Graphics2D g) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(rh);
        pm.render(g);
    }

}
