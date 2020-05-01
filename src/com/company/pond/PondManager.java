package com.company.pond;

import com.company.GamePanel;
import com.company.entities.Duck;
import com.company.entities.IPondEntity;
import com.company.entities.Lilypad;
import com.company.entities.Rock;
import com.company.tools.MathUtils;
import com.company.tools.Vector2D;

import java.applet.AudioClip;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class PondManager {


    private ArrayList<IPondEntity> entities = new ArrayList<>();

    private int height = GamePanel.getWindowHeight();
    private int width = GamePanel.getWindowWidth();

    private BufferedImage rockImg;
    private BufferedImage duckImg1;
    private BufferedImage duckImg2;
    private BufferedImage lilypadImg;

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

    public static class SoundAnimation {
        public static synchronized void play(final String fileName)
        {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Clip sound = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                        sound.open(inputStream);
                        sound.start();
                    } catch (Exception e) {
                        System.out.println("SoundAnimation: error " + e.getMessage() + " for " + fileName);
                    }
                }
            }).start();

        }
    }


        public void spawnDuck() {
        int randx = ThreadLocalRandom.current().nextInt(0, 1280);
        int randy = ThreadLocalRandom.current().nextInt(0, 800);
        entities.add(new Duck(randx, randy));
    }

    public void spawnLilypad() {
        int randx = ThreadLocalRandom.current().nextInt(0, 1280);
        int randy = ThreadLocalRandom.current().nextInt(0, 800);
        entities.add(new Lilypad(randx, randy));

    }

    public void spawnRock() {
        int randx = ThreadLocalRandom.current().nextInt(0, 1280);
        int randy = ThreadLocalRandom.current().nextInt(0, 800);
        entities.add(new Rock (randx, randy));

    }


    private int nbLilypads = 0;
    private int nbRocks = 0;
    private int nbDucks = 0;
    public void update() {
        for (IPondEntity entity : entities) {
            entity.update();
        }

        ArrayList<IPondEntity> toRemove = new ArrayList<>();

        // Collisions detection
        int tolerance = 15;
        for (IPondEntity entity : entities) {
            if (!(entity instanceof Duck)) continue;
            Duck duck = (Duck) entity;
            Vector2D pos = duck.getPosition();
            Vector2D size = duck.getSize();
            double rotation = duck.getRotation();
            double angle = Math.toRadians(rotation);

            // Apply hitbox tolerance
            pos.x += tolerance;
            pos.y += tolerance;
            size.x -= tolerance*2;
            size.y -= tolerance*2;

            // pond borders
            double newRot = 0;
            boolean shouldUpdateRot = false;
            if (pos.x <= 0) { // LEFT
                Vector2D vec = MathUtils.reflect(new Vector2D(1, 0).rotate(-angle), new Vector2D(1, 0).rotate(Math.toRadians(180)));
                newRot = vec.getRotation();
                shouldUpdateRot = true;
            } else if (pos.x + size.x >= width) {  // RIGHT
                Vector2D vec = MathUtils.reflect(new Vector2D(1, 0).rotate(-angle), new Vector2D(1, 0).rotate(Math.toRadians(0)));
                newRot = vec.getRotation();
                shouldUpdateRot = true;
            }

            if (pos.y <= 0) { // TOP
                Vector2D vec = MathUtils.reflect(new Vector2D(1, 0).rotate(-angle), new Vector2D(1, 0).rotate(Math.toRadians(270)));
                newRot = vec.getRotation();
                shouldUpdateRot = true;
            } else if (pos.y + size.y >= height) { // BOTTOM
                Vector2D vec = MathUtils.reflect(new Vector2D(1, 0).rotate(-angle), new Vector2D(1, 0).rotate(Math.toRadians(90)));
                newRot = vec.getRotation();
                shouldUpdateRot = true;
            }


            //other entities
            for (IPondEntity entity2 : entities) {
                if (entity2 == entity) continue;
                boolean isColliding = false;
                Vector2D pos2 = entity2.getPosition();
                Vector2D size2 = entity2.getSize();

                // Apply hitbox tolerance
                if (entity2 instanceof Duck) {
                    pos2.x += tolerance;
                    pos2.y += tolerance;
                    size2.x -= tolerance*2;
                    size2.y -= tolerance*2;
                    if (((Duck) entity2).remainingTime < 0) {
                        toRemove.add(entity2);
                    }
                }


                if (pos.x + size.x > pos2.x &&
                    pos.x < pos2.x + size2.x &&
                    pos.y + size.y > pos2.y &&
                    pos.y < pos2.y + size2.y) {
                    isColliding = true;
                }


                if (entity2 instanceof Lilypad && isColliding) {

                    duck.levelUp();
                    SoundAnimation.play("assets/Honk.wav");
                    toRemove.add(entity2);
                    nbLilypads -=1;
                    duck.remainingTime += 200;


                } else if (entity2 instanceof Rock && isColliding) {
                    int angleNormal = 0;
                    if (pos.x <= pos2.x) angleNormal = 0;
                    else if (pos.x + size.x >= pos2.x + size2.x) angleNormal = 180;
                    else if (pos.y <= pos2.y) angleNormal = 90;
                    else if (pos.y + size2.y >= pos2.y + size2.y) angleNormal = 270;

                    Vector2D vec = MathUtils.reflect(new Vector2D(1, 0).rotate(-angle), new Vector2D(1, 0).rotate(Math.toRadians(angleNormal)));
                    newRot = vec.getRotation();
                    shouldUpdateRot = true;
                }

            }//end 2nd for


            if (shouldUpdateRot) {
                duck.setRotation(Math.round(newRot), true);
            }
        }//end 1st for


        entities.removeAll(toRemove);

        if (nbDucks < 10) {
            spawnDuck();
            nbDucks += 1;
        }
        if (nbRocks < 5) {
            spawnRock();
            nbRocks += 1;
        }
        if (nbLilypads <= 15) {
            spawnLilypad();
            nbLilypads += 1;
        }


    }


    public void render(Graphics2D g) {
        // entities.forEach(e -> e.render(g));

        for (IPondEntity entity : entities) {
            entity.render(g);
            int tolerance = 15;
            Vector2D pos = entity.getPosition();
            Vector2D size = entity.getSize();

            if (entity instanceof Duck) {
                pos.x += tolerance;
                pos.y += tolerance;
                size.x -= tolerance*2;
                size.y -= tolerance*2;
            }

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

