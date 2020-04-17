package com.company.entities;

import com.company.Window;
import com.company.pond.PondManager;

import java.awt.*;
import java.awt.geom.Ellipse2D;



public class Duck implements IPondEntity {
    private int x;
    private int y;
    private int width;
    private int height;
    private int level;

    public Duck(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = this.height = 50;
        this.level = 2;
    }

    @Override
    public void update() {
        this.y = this.y - getMoveSpeed(this.level);
        if (this.y < 0){
            this.y = 0;
        }
    }

    int getMoveSpeed(int level) {
        // TODO:
        if (level <= 3){
            return 2;
        }
        else if (level <=10){
            return 2;
        }
        else{
            return 1;
        }
        

    }

    @Override
    public void render(Graphics2D g) {
        PondManager pm = PondManager.getSingleton();
        Image duckImg = null;
        int duckSize = level == 1 ? 40 : 80;
        Color color;
        if (level <= 3) { //            BÉBÉ CANARD
            color = new Color(255,255,0);
            duckImg = pm.getDuckImg1();
        }
        else if (level < 10) { //       CANARD NORMAL
            color = new Color(255,255,0);
            duckImg = pm.getDuckImg1();
        }
        else { //       CHEF CANARD
            color = new Color(220,220,220);
            duckImg = pm.getDuckImg2();
        }

        /*Ellipse2D.Double circle = new Ellipse2D.Double(x, y, duckSize, duckSize);
        g.setColor(color);
        g.fill(circle); //canard = rond coloré
        g.draw(circle);*/

        g.drawImage(duckImg, this.x, this.y, duckSize, duckSize, null);
    }
}
