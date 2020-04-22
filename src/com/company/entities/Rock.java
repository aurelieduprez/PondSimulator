package com.company.entities;

import com.company.pond.PondManager;
import com.company.tools.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;

public class Rock implements IPondEntity {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;

    public Rock(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 70;
        this.height = 70;
        //this.color = new Color(153, 150, 133);
    }



    @Override
    public void update() {
        // NOTHING It's a rock !
    }

    @Override
    public void render(Graphics2D g) {
        PondManager pm = PondManager.getSingleton();
        Image RockImg = null;
        RockImg = pm.getRockImg();
        g.drawImage(RockImg, this.x, this.y, this.width, this.height, null);
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
