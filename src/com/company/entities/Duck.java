package com.company.entities;

import com.company.pond.PondManager;
import com.company.tools.GraphicUtils;
import com.company.tools.MathUtils;
import com.company.tools.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

public class Duck implements IPondEntity {
    private double x;   //position en x
    private double y;   // position en y
    private int width;  //largeur
    private int height; //hauteur
    private double rotation; //rotation en radians
    private boolean justChangedRot; //booléen
    private long lastChangedRot;    //booléen
    public int remainingTime;   //temps de vie
    private int level;      //niveau du canard
    private BufferedImage image;    //image associée

    public Duck(int x, int y) { //nouvelle instance Duck en x, y; avec les caractéristiques suivantes
        this.x = x;
        this.y = y;
        this.width = this.height = 50;
        this.level = 1;
        this.rotation = 45;
        this.image = PondManager.getSingleton().getDuckImg1();
        this.remainingTime = 1000;
    }

    @Override
    public void update() { //fonction décrite par l'interface
        this.forward();
        this.remainingTime -=1;
    }

    public void forward() {     //déplacement selon position, angle et vitesse données
        Double translated = MathUtils.translate2D(this.x, this.y, this.rotation, getMoveSpeed(this.level));
        this.x = translated.x;
        this.y = translated.y;

    }

    public void levelUp() { //augmentation du niveau
        if (level >= 10) return;

        level += 1;

        this.width = this.height += 5;

        if (level == 10) { // Becomes chief
            this.image = PondManager.getSingleton().getDuckImg2();
        }
    }

    int getMoveSpeed(int level) {   //vitesse selon niveau du canard
        if (level < 10){
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public void render(Graphics2D g) {      //rendu graphique du canard en cours
        PondManager pm = PondManager.getSingleton();
        BufferedImage duckImg = null;
        Color color;
        if (level <= 3) { // bébé canard
            color = new Color(255,255,0);
            duckImg = pm.getDuckImg1();
        }
        else if (level < 10) { // canard
            color = new Color(255,255,0);
            duckImg = pm.getDuckImg1();
        }
        else { // chef de la tribu des canards
            color = new Color(220,220,220);
            duckImg = pm.getDuckImg2();
        }

        g.drawImage(GraphicUtils.rotateImageByDegrees(duckImg, rotation), (int)this.x, (int)this.y, width, height, null);
    }

    @Override
    public Vector2D getPosition() { //getter sur la position du canard, return un vector2D
        return new Vector2D(this.x, this.y);
    }

    @Override
    public Vector2D getSize() {     //getter de dimensions, return un vector2D
        return new Vector2D(this.width, this.height);
    }

    public void setRotation(double rot) {   //défini la prochaine rotation, spam protect pour éviter les glitch
        setRotation(rot, false);
    }

    public void setRotation(double rot, boolean spamProtect) {  //calcule le temp de la dernière rotation, afin de limiter les glitch
        long nowTimeMilli = System.currentTimeMillis();
        if (spamProtect && nowTimeMilli - this.lastChangedRot < 100) return;

        if (rot < 0) rot += 360;
        this.rotation = rot;
        lastChangedRot = nowTimeMilli;
    }

    public double getRotation() { //getter de la prochaine rotation
        return this.rotation;

    }

}

