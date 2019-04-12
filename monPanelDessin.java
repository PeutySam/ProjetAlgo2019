

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;


public class monPanelDessin extends JPanel {

    private Courbe c;
    private Color maCouleur;
    private Vehicule veh;
    private Image b1;
    private ImageIcon fond;
    private BufferedImage[] tabObstacle = new BufferedImage[21];
    private LinkedList<Obstacle> obstacle;
    private int niveau;
    private boolean show = false;
    private double rotate = 0;
    private long lastTemps;
    private long thisTemps;
    private boolean anim;
    private ImageIcon gif;
    private ImageIcon gif2;


    public monPanelDessin(Image b) {
        this.b1 = b;
    }

    public monPanelDessin(ImageIcon b) {
        this.fond = b;
    }

    public monPanelDessin(Vehicule v, Image b, LinkedList<Obstacle> r, int n) {
        this.veh = v;
        this.c = new Courbe(new LinkedList<Point>());

        this.obstacle = new LinkedList<>();
        for (Obstacle ob : r) {
            this.obstacle.add(ob);
        }
        b1 = b;
        niveau = n;
        thisTemps = System.currentTimeMillis();
        lastTemps = System.currentTimeMillis();


        switch (niveau) {
            case 1:
                try {
                    tabObstacle[1] = ImageIO.read(new File("Sam/images/plateforme.png"));
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/flag.png"));

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 2:
                try {
                    tabObstacle[1] = ImageIO.read(new File("Sam/images/tuyau_haut.png"));
                    tabObstacle[2] = ImageIO.read(new File("Sam/images/tuyau_bas.png"));
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/damier.png"));

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 3:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/flag.png"));
                    gif = new ImageIcon("Sam/images/tornado.gif");


                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 4:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/damier.png"));
                    tabObstacle[1] = ImageIO.read(new File("Sam/images/building-flat.png"));
                    tabObstacle[2] = ImageIO.read(new File("Sam/images/building.png"));
                    tabObstacle[3] = ImageIO.read(new File("Sam/images/ufo.png"));


                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 5:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/flocon.png"));
                    tabObstacle[1] = ImageIO.read(new File("Sam/images/icicle.png"));
                    tabObstacle[2] = ImageIO.read(new File("Sam/images/icicle2.png"));


                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 6:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/damier2.png"));
                    tabObstacle[1] = ImageIO.read(new File("Sam/images/warp.png"));

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 7:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/axe.png"));
                    tabObstacle[1] = ImageIO.read(new File("Sam/images/bowser.png"));

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 8:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/light.png"));
                    tabObstacle[1] = ImageIO.read(new File("Sam/images/stalactites.png"));
                    tabObstacle[2] = ImageIO.read(new File("Sam/images/stalactitesUp.png"));

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 9:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/portal_win.png"));
                    tabObstacle[1] = ImageIO.read(new File("Sam/images/pillar.png"));
                    tabObstacle[2] = ImageIO.read(new File("Sam/images/portal_left.png"));
                    tabObstacle[3] = ImageIO.read(new File("Sam/images/portal_right.png"));
                    tabObstacle[4] = ImageIO.read(new File("Sam/images/hole.png"));

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 10:
                try {
                    tabObstacle[1] = ImageIO.read(new File("Sam/images/plateforme_bleu.png"));
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/hole_plateforme.png"));

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 11:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/vortex.png"));
                    gif = new ImageIcon("Sam/images/fireball.gif");

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 12:
                try {
                    gif = new ImageIcon("Sam/images/helicopter.gif");
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/damier.png"));
                    tabObstacle[1] = ImageIO.read(new File("Sam/images/building_night1.png"));
                    tabObstacle[2] = ImageIO.read(new File("Sam/images/building_night2.png"));
                    tabObstacle[3] = ImageIO.read(new File("Sam/images/building_night3.png"));
                    tabObstacle[4] = ImageIO.read(new File("Sam/images/building_night4.png"));
                    tabObstacle[5] = ImageIO.read(new File("Sam/images/building_night5.png"));


                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 13:
                try {
                    gif = new ImageIcon("Sam/images/ghost.gif");
                    gif2 = new ImageIcon("Sam/images/light.gif");


                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 14:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/blue_flag.png"));
                    gif = new ImageIcon("Sam/images/skull.gif");

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 15:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/ovni.png"));

                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 16:
                try {
                    gif = new ImageIcon("Sam/images/oiseau.gif");
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/nid.png"));


                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 17:
                try {
                    tabObstacle[0] = ImageIO.read(new File("Sam/images/tresor.png"));


                    tabObstacle[1] = ImageIO.read(new File("Sam/images/vague_03.png"));
                    tabObstacle[2] = ImageIO.read(new File("Sam/images/vague_04.png"));
                    tabObstacle[3] = ImageIO.read(new File("Sam/images/vague_05.png"));
                    tabObstacle[4] = ImageIO.read(new File("Sam/images/vague_06.png"));
                    tabObstacle[5] = ImageIO.read(new File("Sam/images/vague_07.png"));
                    tabObstacle[6] = ImageIO.read(new File("Sam/images/vague_08.png"));
                    tabObstacle[7] = ImageIO.read(new File("Sam/images/vague_09.png"));
                    tabObstacle[8] = ImageIO.read(new File("Sam/images/vague_10.png"));
                    tabObstacle[9] = ImageIO.read(new File("Sam/images/vague_11.png"));
                    tabObstacle[10] = ImageIO.read(new File("Sam/images/vague_12.png"));
                    tabObstacle[11] = ImageIO.read(new File("Sam/images/vague_13.png"));
                    tabObstacle[12] = ImageIO.read(new File("Sam/images/vague_14.png"));
                    tabObstacle[13] = ImageIO.read(new File("Sam/images/vague_15.png"));
                    tabObstacle[14] = ImageIO.read(new File("Sam/images/vague_16.png"));
                    tabObstacle[15] = ImageIO.read(new File("Sam/images/vague_17.png"));
                    tabObstacle[16] = ImageIO.read(new File("Sam/images/vague_18.png"));
                    tabObstacle[17] = ImageIO.read(new File("Sam/images/vague_19.png"));
                    tabObstacle[18] = ImageIO.read(new File("Sam/images/vague_20.png"));
                    tabObstacle[19] = ImageIO.read(new File("Sam/images/poisson.png"));


                } catch (Exception e) {
                    System.out.println(e);
                }
                break;

        }


    }

    public void update(Courbe c) {
        this.c = c;
        // Couleur initiale
        maCouleur = new Color(200, 200, 200);

    }


    public void setShow(boolean b) {
        this.show = b;
    }

    public boolean getShow() {
        return this.show;
    }

    public void setAnim(boolean b) {
        this.anim = b;
    }


    public void paint(Graphics g) {
        // On dessine le fond
        g.setColor(maCouleur);


        // On dessine toutes les courbes du tableau si la liste n'est pas vide
        if (b1 != null) {

            g.drawImage(b1, 0, 0, this.getWidth(), this.getHeight(), null);
        }
        if (fond != null) {

            g.drawImage(fond.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
        if (this.veh != null) {
            doDrawing(g, Color.lightGray, new Obstacle(0, 400, 400, 400), 5);
            this.veh.dessine((Graphics2D) g);
        }


        if (c != null) {

            c.dessine(g);
        }


        if (this.obstacle != null) {

            switch (niveau) {
                case 1:


                    g.drawImage(tabObstacle[1], this.obstacle.get(3).x - 40, this.obstacle.get(1).y - 20, 290, 140, null);
                    g.drawImage(tabObstacle[0], this.obstacle.get(3).x + 65, this.obstacle.get(1).y - 115, 85, 140, null);

                    break;
                case 2:
                    for (int i = 0; i < 3; i++) {
                        g.drawImage(tabObstacle[0], this.obstacle.get(0).x - 20, this.obstacle.get(0).y - 50, 120, 300, null);
                        g.drawImage(tabObstacle[0], this.obstacle.get(0).x - 20, this.obstacle.get(0).y + 180, 120, 300, null);
                        g.drawImage(tabObstacle[0], this.obstacle.get(0).x - 20, this.obstacle.get(0).y + 410, 120, 300, null);
                        g.drawImage(tabObstacle[0], this.obstacle.get(0).x - 20, this.obstacle.get(0).y + 650, 120, 300, null);
                        g.drawImage(tabObstacle[1], this.obstacle.get(2 * i + 1).x, this.obstacle.get(2 * i + 1).y, (int) this.obstacle.get(2 * i + 1).getWidth(), (int) this.obstacle.get(2 * i + 1).getHeight(), null);
                        g.drawImage(tabObstacle[2], this.obstacle.get(2 * i + 2).x, this.obstacle.get(2 * i + 2).y, (int) this.obstacle.get(2 * i + 2).getWidth(), (int) this.obstacle.get(2 * i + 2).getHeight(), null);
                    }

                    break;
                case 3:

                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x + 25, this.obstacle.get(0).y - 100, 85, 140, null);
                    g.drawImage(gif.getImage(), this.obstacle.get(1).x - 40, this.obstacle.get(1).y - 10, (int) this.obstacle.get(1).getWidth() + 80, (int) this.obstacle.get(1).getHeight() + 50, this);
                    g.drawImage(gif.getImage(), this.obstacle.get(2).x - 50, this.obstacle.get(2).y, (int) this.obstacle.get(2).getWidth() + 100, (int) this.obstacle.get(2).getHeight() + 70, this);


                    break;
                case 4:

                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x - 20, this.obstacle.get(0).y, this.obstacle.get(0).width + 40, 40, null);
                    g.drawImage(tabObstacle[1], this.obstacle.get(2).x - 35, this.obstacle.get(2).y - 30, (int) this.obstacle.get(2).getWidth() + 70, (int) this.obstacle.get(2).getHeight(), null);
                    g.drawImage(tabObstacle[2], this.obstacle.get(3).x - 60, this.obstacle.get(3).y, (int) this.obstacle.get(3).getWidth() + 120, (int) this.obstacle.get(3).getHeight(), null);
                    g.drawImage(tabObstacle[3], this.obstacle.get(1).x - 20, this.obstacle.get(1).y, this.obstacle.get(1).width + 40, this.obstacle.get(1).height, null);


                    break;
                case 5:

                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x, this.obstacle.get(0).y, (int) this.obstacle.get(0).getWidth(), (int) this.obstacle.get(0).getHeight(), null);
                    g.drawImage(tabObstacle[1], this.obstacle.get(1).x, this.obstacle.get(1).y - 30, (int) this.obstacle.get(1).getWidth(), (int) this.obstacle.get(1).getHeight() + 70, null);
                    g.drawImage(tabObstacle[2], this.obstacle.get(2).x - 40, this.obstacle.get(2).y - 20, (int) this.obstacle.get(2).getWidth() + 120, (int) this.obstacle.get(2).getHeight() - 100, null);


                    break;
                case 6:

                    g.drawImage(tabObstacle[1], this.obstacle.get(1).x, this.obstacle.get(1).y - 15, (int) this.obstacle.get(1).getWidth() + 300, (int) this.obstacle.get(1).getHeight() + 150, null);
                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x, this.obstacle.get(0).y - 35, (int) this.obstacle.get(0).getWidth(), (int) this.obstacle.get(0).getHeight() + 70, null);


                    break;
                case 7:

                    g.drawImage(tabObstacle[1], this.obstacle.get(1).x - 20, this.obstacle.get(1).y - 30, (int) this.obstacle.get(1).getWidth() + 40, (int) this.obstacle.get(1).getHeight() + 40, null);


                    break;
                case 8:

                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x - 250, this.obstacle.get(0).y, (int) this.obstacle.get(0).getWidth() + 650, (int) this.obstacle.get(0).getHeight() + 50, null);
                    for (int i = 0; i < 5; i++) {
                        g.drawImage(tabObstacle[1], this.obstacle.get(2 * i + 1).x - 35, this.obstacle.get(2 * i + 1).y, (int) this.obstacle.get(2 * i + 1).getWidth() + 70, (int) this.obstacle.get(2 * i + 1).getHeight(), null);
                        g.drawImage(tabObstacle[2], this.obstacle.get(2 * i + 2).x - 35, this.obstacle.get(2 * i + 2).y, (int) this.obstacle.get(2 * i + 2).getWidth() + 70, (int) this.obstacle.get(2 * i + 2).getHeight(), null);

                    }


                    break;
                case 9:

                    thisTemps = System.currentTimeMillis();
                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x - 50, this.obstacle.get(0).y - 50, (int) this.obstacle.get(0).getWidth() + 100, (int) this.obstacle.get(0).getHeight() + 100, null);
                    g.drawImage(tabObstacle[1], this.obstacle.get(1).x - 25, this.obstacle.get(1).y, (int) this.obstacle.get(1).getWidth() + 50, (int) this.obstacle.get(1).getHeight() + 50, null);
                    g.drawImage(tabObstacle[1], this.obstacle.get(2).x - 25, this.obstacle.get(2).y, (int) this.obstacle.get(2).getWidth() + 50, (int) this.obstacle.get(2).getHeight() + 50, null);
                    g.drawImage(tabObstacle[2], this.obstacle.get(1).x - 40, this.obstacle.get(1).y - 10, (int) this.obstacle.get(1).getWidth() + 80, 70, null);
                    g.drawImage(tabObstacle[3], this.obstacle.get(2).x - 35, this.obstacle.get(2).y, (int) this.obstacle.get(2).getWidth() + 80, 70, null);
                    if ((thisTemps > lastTemps + 50) && (anim)) {
                        rotate += 10;
                        lastTemps = System.currentTimeMillis();


                    }

                    g.drawImage(rotateImage(tabObstacle[4], rotate % 360), this.obstacle.get(3).x - 40, this.obstacle.get(3).y - 40, (int) this.obstacle.get(3).getWidth() + 80, (int) this.obstacle.get(3).getHeight() + 80, null);


                    break;
                case 10:

                    g.drawImage(tabObstacle[1], this.obstacle.get(3).x - 30, this.obstacle.get(1).y - 20, 250, 120, null);
                    g.drawImage(tabObstacle[0], this.obstacle.get(3).x + 60, this.obstacle.get(1).y - 24, 70, 70, null);


                    break;
                case 11:


                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x - 90, this.obstacle.get(0).y - 80, (int) this.obstacle.get(0).getWidth() + 180, (int) this.obstacle.get(0).getHeight() + 160, null);
                    g.drawImage(gif.getImage(), this.obstacle.get(1).x - 30, this.obstacle.get(1).y - 80, (int) this.obstacle.get(1).getWidth() + 60, (int) this.obstacle.get(1).getHeight() + 80, this);
                    g.drawImage(gif.getImage(), this.obstacle.get(2).x - 30, this.obstacle.get(2).y - 80, (int) this.obstacle.get(2).getWidth() + 60, (int) this.obstacle.get(2).getHeight() + 80, this);
                    g.drawImage(gif.getImage(), this.obstacle.get(3).x - 30, this.obstacle.get(3).y - 80, (int) this.obstacle.get(3).getWidth() + 60, (int) this.obstacle.get(3).getHeight() + 80, this);
                    g.drawImage(gif.getImage(), this.obstacle.get(4).x - 30, this.obstacle.get(4).y - 80, (int) this.obstacle.get(4).getWidth() + 60, (int) this.obstacle.get(4).getHeight() + 80, this);
                    g.drawImage(gif.getImage(), this.obstacle.get(5).x - 30, this.obstacle.get(5).y - 80, (int) this.obstacle.get(5).getWidth() + 60, (int) this.obstacle.get(5).getHeight() + 80, this);


                    break;
                case 12:


                    g.drawImage(gif.getImage(), this.obstacle.get(6).x - 25, this.obstacle.get(6).y - 25, (int) this.obstacle.get(6).getWidth() + 50, (int) this.obstacle.get(6).getHeight() + 50, this);
                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x, this.obstacle.get(0).y, this.obstacle.get(0).width, 20, null);
                    g.drawImage(tabObstacle[1], this.obstacle.get(1).x - 125, this.obstacle.get(1).y - 60, this.obstacle.get(1).width + 290, this.obstacle.get(1).height + 60, null);
                    g.drawImage(tabObstacle[2], this.obstacle.get(2).x - 125, this.obstacle.get(2).y - 60, this.obstacle.get(2).width + 200, this.obstacle.get(2).height + 60, null);
                    g.drawImage(tabObstacle[3], this.obstacle.get(3).x - 150, this.obstacle.get(3).y - 60, this.obstacle.get(3).width + 250, this.obstacle.get(3).height + 60, null);
                    g.drawImage(tabObstacle[4], this.obstacle.get(4).x - 220, this.obstacle.get(4).y - 60, this.obstacle.get(4).width + 420, this.obstacle.get(4).height + 60, null);
                    g.drawImage(tabObstacle[5], this.obstacle.get(5).x - 180, this.obstacle.get(5).y - 60, this.obstacle.get(5).width + 350, this.obstacle.get(5).height + 60, null);


                    break;
                case 13:
                    g.drawImage(gif.getImage(), this.obstacle.get(1).x - 25, this.obstacle.get(1).y - 10, (int) this.obstacle.get(1).getWidth() + 50, (int) this.obstacle.get(1).getHeight() + 30, this);
                    g.drawImage(gif.getImage(), this.obstacle.get(2).x - 25, this.obstacle.get(2).y - 10, (int) this.obstacle.get(2).getWidth() + 50, (int) this.obstacle.get(2).getHeight() + 30, this);
                    g.drawImage(gif.getImage(), this.obstacle.get(3).x - 25, this.obstacle.get(3).y - 10, (int) this.obstacle.get(3).getWidth() + 50, (int) this.obstacle.get(3).getHeight() + 30, this);
                    g.drawImage(gif2.getImage(), this.obstacle.get(0).x, this.obstacle.get(0).y - 10, (int) this.obstacle.get(0).getWidth(), (int) this.obstacle.get(0).getHeight(), this);


                    break;
                case 14:
                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x + 30, this.obstacle.get(0).y - 65, 85, 140, null);
                    for (int i = 1; i < this.obstacle.size(); i++) {
                        g.drawImage(gif.getImage(), this.obstacle.get(i).x - 30, this.obstacle.get(i).y - 65, (int) this.obstacle.get(i).getWidth() + 60, (int) this.obstacle.get(i).getHeight() + 80, this);

                    }


                    break;
                case 15:
                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x - 40, this.obstacle.get(0).y - 40, this.obstacle.get(0).width + 80, this.obstacle.get(0).height + 80, null);


                    break;
                case 16:
                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x - 10, this.obstacle.get(0).y - 10, this.obstacle.get(0).width + 20, this.obstacle.get(0).height + 20, null);

                    g.drawImage(gif.getImage(), this.obstacle.get(1).x - 20, this.obstacle.get(1).y - 30, (int) this.obstacle.get(1).getWidth() + 40, (int) this.obstacle.get(1).getHeight() + 40, this);
                    g.drawImage(gif.getImage(), this.obstacle.get(2).x - 20, this.obstacle.get(2).y - 30, (int) this.obstacle.get(2).getWidth() + 40, (int) this.obstacle.get(2).getHeight() + 40, this);
                    g.drawImage(gif.getImage(), this.obstacle.get(3).x - 20, this.obstacle.get(3).y - 30, (int) this.obstacle.get(3).getWidth() + 40, (int) this.obstacle.get(3).getHeight() + 40, this);
                    g.drawImage(gif.getImage(), this.obstacle.get(4).x - 20, this.obstacle.get(4).y - 30, (int) this.obstacle.get(4).getWidth() + 40, (int) this.obstacle.get(4).getHeight() + 40, this);


                    break;

                case 17:

                    g.drawImage(tabObstacle[0], this.obstacle.get(0).x, this.obstacle.get(0).y, this.obstacle.get(0).width, this.obstacle.get(0).height, null);
                    g.drawImage(tabObstacle[19], this.obstacle.get(76).x, this.obstacle.get(76).y, this.obstacle.get(76).width, this.obstacle.get(76).height, null);
                    g.drawImage(tabObstacle[19], this.obstacle.get(77).x, this.obstacle.get(77).y, this.obstacle.get(77).width, this.obstacle.get(77).height, null);
                    for (int i = 1; i < 75; i++) {


                        if ((this.obstacle.get(i).y >= 800) && (this.obstacle.get(i).y <= 812) && (this.obstacle.get(i).getVit().getY() <= 0)) {
                            g.drawImage(tabObstacle[9], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y > 812) && (this.obstacle.get(i).y <= 824) && (this.obstacle.get(i).getVit().getY() <= 0)) {
                            g.drawImage(tabObstacle[8], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y > 824) && (this.obstacle.get(i).y <= 836) && (this.obstacle.get(i).getVit().getY() <= 0)) {
                            g.drawImage(tabObstacle[7], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y > 836) && (this.obstacle.get(i).y <= 848) && (this.obstacle.get(i).getVit().getY() <= 0)) {
                            g.drawImage(tabObstacle[6], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y > 848) && (this.obstacle.get(i).y <= 860) && (this.obstacle.get(i).getVit().getY() <= 0)) {
                            g.drawImage(tabObstacle[5], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y > 860) && (this.obstacle.get(i).y <= 872) && (this.obstacle.get(i).getVit().getY() <= 0)) {
                            g.drawImage(tabObstacle[4], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y > 872) && (this.obstacle.get(i).y <= 884) && (this.obstacle.get(i).getVit().getY() <= 0)) {
                            g.drawImage(tabObstacle[3], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y > 884) && (this.obstacle.get(i).y <= 896) && (this.obstacle.get(i).getVit().getY() <= 0)) {
                            g.drawImage(tabObstacle[2], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y >= 896) && (this.obstacle.get(i).y <= 908) && (this.obstacle.get(i).getVit().getY() <= 0)) {
                            g.drawImage(tabObstacle[1], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }

                        if ((this.obstacle.get(i).y >= 800) && (this.obstacle.get(i).y < 812) && (this.obstacle.get(i).getVit().getY() >= 0)) {
                            g.drawImage(tabObstacle[10], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y >= 812) && (this.obstacle.get(i).y < 824) && (this.obstacle.get(i).getVit().getY() > 0)) {
                            g.drawImage(tabObstacle[11], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y >= 824) && (this.obstacle.get(i).y < 836) && (this.obstacle.get(i).getVit().getY() > 0)) {
                            g.drawImage(tabObstacle[12], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y >= 836) && (this.obstacle.get(i).y < 848) && (this.obstacle.get(i).getVit().getY() > 0)) {
                            g.drawImage(tabObstacle[13], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y >= 848) && (this.obstacle.get(i).y < 860) && (this.obstacle.get(i).getVit().getY() > 0)) {
                            g.drawImage(tabObstacle[14], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y >= 860) && (this.obstacle.get(i).y < 872) && (this.obstacle.get(i).getVit().getY() > 0)) {
                            g.drawImage(tabObstacle[15], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y >= 872) && (this.obstacle.get(i).y < 884) && (this.obstacle.get(i).getVit().getY() > 0)) {
                            g.drawImage(tabObstacle[16], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y >= 884) && (this.obstacle.get(i).y < 896) && (this.obstacle.get(i).getVit().getY() > 0)) {
                            g.drawImage(tabObstacle[17], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }
                        if ((this.obstacle.get(i).y >= 896) && (this.obstacle.get(i).y <= 908) && (this.obstacle.get(i).getVit().getY() > 0)) {
                            g.drawImage(tabObstacle[18], this.obstacle.get(i).x, 790, (int) this.obstacle.get(i).getWidth(), 150, null);
                        }


                    }


                    break;

            }
            if (show) {
                for (Obstacle ob : this.obstacle) {
                    doDrawing(g, Color.red, ob, 4);
                }
                doDrawing(g, Color.blue, this.obstacle.get(0), 4);
            }


        }


    }

    private void doDrawing(Graphics g, Color c, Obstacle ob, float op) {

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setPaint(c);


        float alpha = op * 0.1f;
        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alcom);
        g2d.fillRect(ob.x, ob.y, ob.width, ob.height);


        g2d.dispose();
    }

    private BufferedImage rotateImage(BufferedImage originalImage, double degree) {
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();
        double toRad = Math.toRadians(degree);
        int hPrime = (int) (w * Math.abs(Math.sin(toRad)) + h * Math.abs(Math.cos(toRad)));
        int wPrime = (int) (h * Math.abs(Math.sin(toRad)) + w * Math.abs(Math.cos(toRad)));
        BufferedImage rotatedImage = new BufferedImage(wPrime, hPrime, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = rotatedImage.createGraphics();
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, wPrime, hPrime);

        g.setComposite(AlphaComposite.Src);
        g.translate(wPrime / 2, hPrime / 2);
        g.rotate(toRad);
        g.translate(-w / 2, -h / 2);
        g.drawImage(originalImage, 0, 0, null);
        g.dispose();  // release used resources before g is garbage-collected
        return rotatedImage;
    }


}
