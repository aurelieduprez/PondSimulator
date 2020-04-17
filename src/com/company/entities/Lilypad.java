package com.company.entities;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Lilypad implements IPondEntity {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Lilypad(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        this.color = new Color(51,153,0);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(this.color);
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 50, 50);
        g.fill(circle); //lily pad = rond coloré
        g.draw(circle);
    }
}