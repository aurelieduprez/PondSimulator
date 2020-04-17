package com.company.entities;

import java.awt.*;
import java.io.IOException;

public class Rock implements IPondEntity {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;

    public Rock(Image img, int x, int y, int width, int height) throws IOException {
        this.image = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    @Override
    public void update() {
        // NOTHING It's a rock !
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(50,50,50));
        g.fillRect(x, y, width, height);
    }
}
