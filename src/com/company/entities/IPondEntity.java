package com.company.entities;
import com.company.tools.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D;


public interface IPondEntity {
    public void update();
    public void render(Graphics2D g);

    public Vector2D getPosition();
    public Vector2D getSize();
}
