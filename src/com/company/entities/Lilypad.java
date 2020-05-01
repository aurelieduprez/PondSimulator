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

    public Lilypad(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {

        PondManager pm = PondManager.getSingleton();
        Image lilypadImg = null;
        lilypadImg = pm.getLilypadImg();
        g.drawImage(lilypadImg, this.x, this.y, this.width, this.height, null);

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