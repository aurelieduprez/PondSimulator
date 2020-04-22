package com.company.pond;

import com.company.GamePanel;
import com.company.entities.Duck;
import com.company.entities.IPondEntity;
import com.company.entities.Lilypad;
import com.company.entities.Rock;
import com.company.tools.MathUtils;
import com.company.tools.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PondManager {


    private ArrayList<IPondEntity> entities = new ArrayList<>();

    private int height = GamePanel.getWindowHeight();
    private int width = GamePanel.getWindowWidth();

    private BufferedImage rockImg;
    private BufferedImage duckImg1;
    private BufferedImage duckImg2;
    private BufferedImage lilypadImg;

    // TODO: A supprimer
    boolean debugSpawned = false;

    private static PondManager _instance = null;

    private PondManager() {}

    public static PondManager getSingleton() {
        if (_instance == null) {
            _instance = new PondManager();
        }
        return _instance;
    }

    public void loadAssets() throws IOException {
        this.rockImg = ImageIO.read(new File("assets/rock.png"));
        this.duckImg1 = ImageIO.read(new File("assets/duck1_right.png"));
        this.duckImg2 = ImageIO.read(new File("assets/duck2_right.png"));
        this.lilypadImg = ImageIO.read(new File("assets/lilypad.png"));
    }


    public void spawnDuck() {
        int randx = ThreadLocalRandom.current().nextInt(0, 1280);
        System.out.println(randx);
        int randy = ThreadLocalRandom.current().nextInt(0, 800);
        System.out.println(randy);
        entities.add(new Duck(randx, randy));

    }

    public void spawnLilypad() {
        int randx = ThreadLocalRandom.current().nextInt(0, 1280);
        System.out.println(randx);
        int randy = ThreadLocalRandom.current().nextInt(0, 800);
        System.out.println(randy);
        entities.add(new Lilypad(randx, randy));

    }

    public void spawnRock() {
        int randx = ThreadLocalRandom.current().nextInt(0, 1280);
        System.out.println(randx);
        int randy = ThreadLocalRandom.current().nextInt(0, 800);
        System.out.println(randy);
        entities.add(new Rock (randx, randy));

    }


    // TODO:manage rotations
    private int nbLilypads = 0;
    private int nbRocks = 0;
    private int nbDucks = 0;
    public void update() {
        //entities.forEach(IPondEntity::update);
        for (IPondEntity entity : entities) {
            entity.update();
        }

        // Collisions detection
        for (IPondEntity entity : entities) {
            if (!(entity instanceof Duck)) continue;
            Duck duck = (Duck)entity;

            Vector2D pos = duck.getPosition();
            Vector2D duckSize = duck.getSize();
            double rotation = duck.getRotation();
            double angle = Math.toRadians(rotation);
            System.out.println(pos);

            // pond borders
            double newRot = 0;
            boolean shouldUpdateRot = false;
            if (pos.x <= 0) {
                Vector2D vec = MathUtils.reflect(new Vector2D(1, 0).rotate(-angle), new Vector2D(1, 0).rotate(Math.toRadians(180)));
                System.out.println(duck.getRotation());
                System.out.println(vec.getRotation());
                newRot = vec.getRotation();
                shouldUpdateRot = true;
            } else if (pos.x + duckSize.x >= width) {
                Vector2D vec = MathUtils.reflect(new Vector2D(1, 0).rotate(-angle), new Vector2D(1, 0).rotate(Math.toRadians(0)));
                System.out.println(duck.getRotation());
                System.out.println(vec.getRotation());
                newRot = vec.getRotation();
                shouldUpdateRot = true;
            }

            if (pos.y <= 0) {
                Vector2D vec = MathUtils.reflect(new Vector2D(1, 0).rotate(-angle), new Vector2D(1, 0).rotate(Math.toRadians(270)));
                System.out.println(duck.getRotation());
                System.out.println(vec.getRotation());
                newRot = vec.getRotation();
                shouldUpdateRot = true;
            } else if (pos.y + duckSize.y >= height) {
                Vector2D vec = MathUtils.reflect(new Vector2D(1, 0).rotate(-angle), new Vector2D(1, 0).rotate(Math.toRadians(90)));
                System.out.println(duck.getRotation());
                System.out.println(vec.getRotation());
                newRot = vec.getRotation();
                shouldUpdateRot = true;
            }

            if (shouldUpdateRot) {
                duck.setRotation(Math.round(newRot));
            }

        }

        if (nbDucks < 1) {
            spawnDuck();
            nbDucks += 1;
        }
        if (nbRocks < 5) {
            spawnRock();
            nbRocks += 1;
        }
        if (nbLilypads <= 25) {
            spawnLilypad();
            nbLilypads += 1;
        }


    }


    public void render(Graphics2D g) {
        // entities.forEach(e -> e.render(g));

        for (IPondEntity entity : entities) {
            entity.render(g);
        }
    }

    public BufferedImage getRockImg() {
        return rockImg;
    }

    public BufferedImage getDuckImg1() {
        return duckImg1;
    }

    public BufferedImage getDuckImg2() {
        return duckImg2;
    }

    public BufferedImage getLilypadImg() {
        return lilypadImg;
    }
}
