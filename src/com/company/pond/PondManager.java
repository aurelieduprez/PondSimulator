package com.company.pond;

import com.company.GamePanel;
import com.company.entities.Duck;
import com.company.entities.IPondEntity;
import com.company.entities.Lilypad;
import com.company.entities.Rock;

import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

public class PondManager {


    private ArrayList<IPondEntity> entities = new ArrayList<>();

    private int height = GamePanel.getWindowHeight();
    private int width = GamePanel.getWindowWidth();

    private Image rockImg;
    private Image duckImg1;
    private Image duckImg2;
    private Image lilypadImg;

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
        this.duckImg1 = ImageIO.read(new File("assets/duck1_up.png"));
        this.duckImg2 = ImageIO.read(new File("assets/duck2_up.png"));
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

        if (nbDucks <= 30) {
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


    public Image getRockImg() {
        return rockImg;
    }

    public Image getDuckImg1() {
        return duckImg1;
    }

    public Image getDuckImg2() {
        return duckImg2;
    }

    public Image getLilypadImg() {
        return lilypadImg;
    }
}
