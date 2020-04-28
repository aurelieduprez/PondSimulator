package com.company.entities;

import com.company.pond.PondManager;
import com.company.tools.Vector2D;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.Rectangle;

public class Lilypad implements IPondEntity {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public Lilypad(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
        this.color = new Color(51,153,0);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {

        PondManager pm = PondManager.getSingleton();
        Image lilypadImg = null;
        lilypadImg = pm.getLilypadImg();

        //g.setColor(new Color(45, 100, 106));
        //g.fill(new Rectangle(this.x, this.y, this.width, this.height));

        g.drawImage(lilypadImg, this.x, this.y, this.width, this.height, null);



        //g.dispose();

        /*g.setColor(this.color);
        g.drawImage(lilypadImg, this.x, this.y, duckSize, duckSize, null);
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 50, 50);
        g.fill(circle); //lily pad = rond coloré
        g.draw(circle);*/
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(this.x, this.y);
    }

    @Override
    public Vector2D getSize() {
        return new Vector2D(this.width, this.height);
    }
}