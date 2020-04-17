package com.company.pond;

import com.company.GamePanel;
import com.company.entities.Duck;
import com.company.entities.IPondEntity;
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
        this.duckImg1 = ImageIO.read(new File("assets/duck1.png"));
        this.duckImg2 = ImageIO.read(new File("assets/duck2.png"));
        this.lilypadImg = ImageIO.read(new File("assets/lilypad.png"));
    }


 /*   public void drawPond(int[][] pond, int height, int width, int pondSize, Graphics2D g) {
        int i, j; // index utilisés pour afficher le tableau avec les boucles for

        for (i = 0;i < pond.length; i++) {
            for (j = 0;j < pond[i].length;j++) {
                if (pond[i][j] == 0) {              // EAU
                    g.setColor(new Color(51,153,255));
                    g.fillRect(((width-pondSize)/2)+50*j,((height-pondSize)/2)+50*i,50,50);
                }

                else if (pond[i][j] == 1) {              // LILY PAD
                    g.setColor(new Color(51,153,255));
                    g.fillRect(((width-pondSize)/2)+50*j,((height-pondSize)/2)+50*i,50,50); //eau d'abord

                    g.setColor(new Color(51,153,0));
                    Ellipse2D.Double circle = new Ellipse2D.Double((((width - pondSize) / 2) + 50 * j)+15, (((height - pondSize) / 2) + 50 * i)+15,20,20);
                    g.fill(circle); //lily pad = rond coloré
                    g.draw(circle);

                }

                else if (pond[i][j] == 2) {              // CAILLOU
                    g.setColor(new Color(50,50,50));
                    g.fillRect(((width-pondSize)/2)+50*j,((height-pondSize)/2)+50*i,50,50);
                }

                else {              // CANARD

                    g.setColor(new Color(51,153,255));
                    g.fillRect(((width-pondSize)/2)+50*j,((height-pondSize)/2)+50*i,50,50); //eau d'abord
                    int duckSize;

                    if (pond[i][j] == 7) { //            BÉBÉ CANARD
                        g.setColor(new Color(255,255,0));
                        duckSize = 26;
                    }
                    else if (pond[i][j] == 8) { //       CANARD NORMAL
                        g.setColor(new Color(255,255,0));
                        duckSize = 40;
                    }
                    else { //       CHEF CANARD
                        g.setColor(new Color(220,220,220));
                        duckSize = 40;
                    }
                    Ellipse2D.Double circle = new Ellipse2D.Double((((width - pondSize) / 2) + 50 * j)+(50-duckSize)/2, (((height - pondSize) / 2) + 50 * i)+(50-duckSize)/2, duckSize, duckSize);
                    g.fill(circle); //canard = rond coloré
                    g.draw(circle);
                }


            }
        }
    }
*/
    public void spawnDuck() {
        int randx = ThreadLocalRandom.current().nextInt(0, 1200);
        System.out.println(randx);
        int randy = ThreadLocalRandom.current().nextInt(0, 820);
        System.out.println(randy);
        entities.add(new Duck(randx, randy));

    }

    // TODO: other spawns

    public void update() {
        //entities.forEach(IPondEntity::update);
        for (IPondEntity entity : entities) {
            entity.update();
        }

        // Logic pour instancier un nouveau duck randomly
        // TODO

        if (!debugSpawned) {
            spawnDuck();
            debugSpawned = true;
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
