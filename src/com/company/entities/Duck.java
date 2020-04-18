package com.company.entities;

import com.company.Window;
import com.company.pond.PondManager;
import com.company.GamePanel;
import java.awt.*;
import java.awt.geom.Ellipse2D;



public class Duck implements IPondEntity {
    private int x;
    private int y;
    private int width;
    private int height;
    private int level;
    private int radius;


    public Duck(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = this.height = 50;
        this.level = 1;
        this.radius = 20;

    }

    @Override
    public void update() {
        this.y = this.y - getMoveSpeed(this.level);
        if (this.y < 1){
            this.y = this.y + 10;
            System.out.println(this.y);
        }
    }

    int getMoveSpeed(int level) {
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
        if (level <= 3) { // baby duck
            color = new Color(255,255,0);
            duckImg = pm.getDuckImg1();
        }
        else if (level < 10) { // pretty duck
            color = new Color(255,255,0);
            duckImg = pm.getDuckImg1();
        }
        else { // king of the ducks
            color = new Color(220,220,220);
            duckImg = pm.getDuckImg2();
        }

        g.drawImage(duckImg, this.x, this.y, duckSize, duckSize, null);
    }
}
