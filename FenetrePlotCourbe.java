import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.event.*;
import java.awt.Rectangle;

import sun.audio.*;

public class FenetrePlotCourbe extends JFrame implements ActionListener, MouseMotionListener, ChangeListener {

    private Courbe curvedrawn;
    private Courbe curve;
    private JButton monBoutonGo;
    private JButton reStart;
    private JButton boutonRetour;
    private JPanel zoneBouton;
    private monPanelDessin panneauDessin;
    private long temps;
    private double temps2;
    private Timer monTimer;
    private Vehicule cadis;
    private Image background = null;
    private Image sprite = null;
    private double coeffF;
    private double coeffG;
    private Parametres parametre;
    private LinkedList<Rectangle> obstacles;
    private int win;
    private boolean test = false;
    // private Canvas dessin;
    private SoundEffect sound = new SoundEffect();
    private SoundEffect song = new SoundEffect();
    private Point lastP;
    private Point thisP;
    private LinkedList<Point> maListeP;
    private JSlider sliderGravite;
    private JSlider sliderPoids;
    private JComboBox<String> boxMatiere;

    private int deplacement1;
    private int deplacement2;
    private int deplacement3;
    private int deplacement4;
    private int deplacement5;
    private int deplacement6;
    private int deplacement7;
    private int deplacement8;
    private int deplacement9;
    private int deplacement10;
    private int elan = 0;

    public FenetrePlotCourbe(int n) {

        super("Jeu Injouable");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

        win = n;
        coeffG=50;
        coeffF=1;
        thisP = new Point(0, 0);
        lastP = new Point(0, 0);
        maListeP = new LinkedList<>();
        curvedrawn = new Courbe(maListeP, Color.blue);
        parametre = new Parametres();
        //dessin=new Canvas();
        this.cadis = new Vehicule(50, Color.red);

        this.setTitle("IHM Courbe - Graphisme ");
        this.setLayout(null);
        this.setSize((int) (screensize.getWidth()), (int) (0.92 * screensize.getHeight()));
        this.setLocation(0, 0);
        this.setResizable(false);
        this.setVisible(true);

        monBoutonGo = new JButton("Start");
        monBoutonGo.setBounds(75, (int) (0.05 * screensize.getHeight()), 150, 60);
        monBoutonGo.addActionListener(this);

        boutonRetour = new JButton("Retour");
        boutonRetour.setBounds(75, (int) (0.15 * screensize.getHeight()), 150, 60);
        boutonRetour.addActionListener(this);

        reStart = new JButton("Recommencer");
        reStart.setBounds(75, (int) (0.25 * screensize.getHeight()), 150, 60);
        reStart.addActionListener(this);

        zoneBouton = new JPanel();
        zoneBouton.setBounds(20, 20, 300, 900);
        zoneBouton.setLayout(null);
        zoneBouton.setBackground(Color.gray);
        zoneBouton.add(monBoutonGo);
        zoneBouton.add(boutonRetour);
        zoneBouton.add(reStart);

        JLabel etiquettePoids = new JLabel();
        etiquettePoids.setText("Poids");
        etiquettePoids.setSize(50, 20);
        etiquettePoids.setLocation(20, 475);
        zoneBouton.add(etiquettePoids);

        JLabel etiquetteGravite = new JLabel();
        etiquetteGravite.setText("Cste Gravitationelle");
        etiquetteGravite.setSize(150, 20);
        etiquetteGravite.setLocation(20, 575);
        zoneBouton.add(etiquetteGravite);

        JLabel etiquetteMatiere = new JLabel();
        etiquetteMatiere.setText("Matiere");
        etiquetteMatiere.setSize(50, 20);
        etiquetteMatiere.setLocation(20, 675);
        zoneBouton.add(etiquetteMatiere);

        sliderPoids = new JSlider(20, 100, 50);
        sliderPoids.setSize(250, 50);
        sliderPoids.setLocation(20, 500);
        sliderPoids.setPaintTrack(true);
        sliderPoids.setPaintTicks(true);
        sliderPoids.setPaintLabels(true);
        sliderPoids.setMajorTickSpacing(20);
        sliderPoids.setMinorTickSpacing(5);
        sliderPoids.addChangeListener(this);
        zoneBouton.add(sliderPoids);

        sliderGravite = new JSlider(0, 100, 50);
        sliderGravite.setSize(250, 50);
        sliderGravite.setLocation(20, 600);
        sliderGravite.setPaintTrack(true);
        sliderGravite.setPaintTicks(true);
        sliderGravite.setPaintLabels(true);
        sliderGravite.setMajorTickSpacing(25);
        sliderGravite.setMinorTickSpacing(5);
        sliderGravite.addChangeListener(this);
        zoneBouton.add(sliderGravite);

        boxMatiere = new JComboBox<String>();
        boxMatiere.setSize(250, 50);
        boxMatiere.setLocation(20, 700);
        boxMatiere.addItem("Glace");
        boxMatiere.addItem("Acier");
        boxMatiere.addItem("Bois");
        boxMatiere.addItem("Aluminium");
        boxMatiere.addItem("Caoutchouc");
        boxMatiere.addActionListener(this);
        zoneBouton.add(boxMatiere);


        monTimer = new Timer(50, this);


        obstacles = new LinkedList<>();


        //TENTATIVE MUSIQUE/SON


        //SELECTION NIVEAU
        switch (n) {
            case 1:
                try {
                    background = ImageIO.read(new File("Sam/images/background1.png"));
                    Rectangle gg = new Rectangle(820, 240, 130, 10);
                    obstacles.add(gg);
                    Rectangle gameOver1 = new Rectangle(950, 200, 20, 50);
                    Rectangle gameOver2 = new Rectangle(800, 200, 20, 50);
                    Rectangle gameOver3 = new Rectangle(800, 250, 170, 20);
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);
                } catch (IOException ex) {
                    System.out.println(ex);

                }
                break;
            case 2:
                try {
                    background = ImageIO.read(new File("Sam/images/background2.jpg"));
                    Rectangle gg = new Rectangle(1100, 0, 10, 1000);
                    obstacles.add(gg);
                    Rectangle gameOver1 = new Rectangle(550, 550, 80, 600);
                    Rectangle gameOver2 = new Rectangle(550, 0, 80, 270);
                    Rectangle gameOver3 = new Rectangle(700, 450, 80, 600);
                    Rectangle gameOver4 = new Rectangle(700, 0, 80, 230);
                    Rectangle gameOver5 = new Rectangle(850, 530, 80, 600);
                    Rectangle gameOver6 = new Rectangle(850, 0, 80, 300);

                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);
                    obstacles.add(gameOver4);
                    obstacles.add(gameOver5);
                    obstacles.add(gameOver6);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 3:
                try {
                    background = ImageIO.read(new File("Sam/images/background3.jpg"));
                    Rectangle gg = new Rectangle(1000, 250, 80, 80);
                    obstacles.add(gg);
                    Rectangle gameOver1 = new Rectangle(500, 400, 30, 145);
                    Rectangle gameOver2 = new Rectangle(700, -50, 30, 200);
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);


                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 4:
                try {
                    background = ImageIO.read(new File("Sam/images/background4.png"));
                    Rectangle gg = new Rectangle(520, 600, 200, 20);
                    obstacles.add(gg);
                    Rectangle gameOver1 = new Rectangle(420, 0, 100, 250);
                    Rectangle gameOver2 = new Rectangle(420, 400, 100, 600);
                    Rectangle gameOver3 = new Rectangle(720, 0, 80, 1000);
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 5:
                try {
                    background = ImageIO.read(new File("Sam/images/background5.png"));
                    Rectangle gg = new Rectangle(1000, 600, 150, 50);
                    obstacles.add(gg);
                    Rectangle gameOver1 = new Rectangle(600, 0, 50, 100);
                    Rectangle gameOver2 = new Rectangle(600, 200, 50, 600);
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 6:
                try {
                    background = ImageIO.read(new File("Sam/images/background6.png"));
                    Rectangle gg = new Rectangle(870, 70, 20, 100);
                    obstacles.add(gg);
                    Rectangle gameOver1 = new Rectangle(800, 50, 600, 20);
                    Rectangle gameOver2 = new Rectangle(800, 170, 600, 20);
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 7:
                try {
                    background = ImageIO.read(new File("Sam/images/background7.jpg"));
                    sprite = ImageIO.read(new File("Sam/images/bowser.jpg"));
                    Rectangle gg = new Rectangle(1100, 550, 100, 100);
                    obstacles.add(gg);
                    Rectangle gameOver = new Rectangle(600, 490, 150, 150);
                    obstacles.add(gameOver);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 8:
                try {
                    background = ImageIO.read(new File("Sam/images/background6.png"));
                    Rectangle gg = new Rectangle(1150, 0, 20, 800);
                    obstacles.add(gg);
                    Rectangle gameOver1 = new Rectangle(550, 0, 80, 400);
                    Rectangle gameOver2 = new Rectangle(550, 400, 80, 400);
                    Rectangle gameOver3 = new Rectangle(650, 0, 80, 400);
                    Rectangle gameOver4 = new Rectangle(650, 400, 80, 400);
                    Rectangle gameOver5 = new Rectangle(750, 0, 80, 400);
                    Rectangle gameOver6 = new Rectangle(750, 400, 80, 400);
                    Rectangle gameOver7 = new Rectangle(850, 0, 80, 400);
                    Rectangle gameOver8 = new Rectangle(850, 400, 80, 400);
                    Rectangle gameOver9 = new Rectangle(950, 0, 80, 400);
                    Rectangle gameOver10 = new Rectangle(950, 400, 80, 400);
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);
                    obstacles.add(gameOver4);
                    obstacles.add(gameOver5);
                    obstacles.add(gameOver6);
                    obstacles.add(gameOver7);
                    obstacles.add(gameOver8);
                    obstacles.add(gameOver9);
                    obstacles.add(gameOver10);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 9:
                try {
                    background = ImageIO.read(new File("Sam/images/background6.png"));
                    Rectangle gg = new Rectangle(1100, 550, 100, 100);
                    obstacles.add(gg);
                    Rectangle gameOver1 = new Rectangle(550, 500, 120, 300);
                    Rectangle gameOver2 = new Rectangle(850, 350, 120, 450);
                    Rectangle gameOver3 = new Rectangle(560, 140, 100, 100);

                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);


                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 10:
                try {
                    background = ImageIO.read(new File("Sam/images/background6.png"));
                    Rectangle gg = new Rectangle(820, 300, 130, 10);
                    obstacles.add(gg);
                    Rectangle gameOver1 = new Rectangle(950, 260, 20, 50);
                    Rectangle gameOver2 = new Rectangle(800, 260, 20, 50);
                    Rectangle gameOver3 = new Rectangle(800, 310, 170, 20);
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
        }


        panneauDessin = new monPanelDessin(cadis, background, sprite, obstacles);
        panneauDessin.setBounds(350, 10, (int) (0.8 * screensize.getWidth()), (int) (0.85 * screensize.getHeight()));
        panneauDessin.setLayout(null);
        panneauDessin.setBackground(Color.cyan);
        panneauDessin.addMouseMotionListener(this);
        panneauDessin.update(curvedrawn);

        JPanel panneauGlobal = new JPanel();
        setContentPane(panneauGlobal);
        panneauGlobal.setLayout(null);
        panneauGlobal.setBackground(Color.DARK_GRAY);
        panneauGlobal.add(panneauDessin);
        panneauGlobal.add(zoneBouton);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setCoeffF(double f) {
        this.coeffF = f;
    }

    public void setCoeffG(double g) {
        this.coeffG = g;
    }

    /*public void setCurve(Courbe c) {
        this.curve = c;
    }*/



   /* private Courbe NouvelleCourbe() {

        LinkedList<Point> oldL = dessin.getCurve().getList();
        LinkedList<Point> newL = new LinkedList();
        for (int i = 0; i < oldL.size(); i++) {
            newL.add(new Point(oldL.get(i).getX(), oldL.get(i).getY() + this.getHeight() - 600));
        }
        Courbe newCourbe = new Courbe(newL, Color.red);
        return newCourbe;
    }*/

    private void Collision(double k) {

        double[] points = new double[4];
        double inclinaison = 0;
        double d;
        double CoeffD = 0;
        for (int i = 1; i < curve.getList().size(); i++) {
            if ((curve.getList().get(i - 1).getX() <= this.cadis.getPos().getX()) && (curve.getList().get(i).getX() >= this.cadis.getPos().getX())) {
                points[0] = curve.getList().get(i - 1).getX();
                points[1] = curve.getList().get(i - 1).getY();
                points[2] = curve.getList().get(i).getX();
                points[3] = curve.getList().get(i).getY();

            }
        }
        CoeffD = (points[3] - points[1]) / (points[2] - points[0]);
        d = Math.sqrt((new Point(points[0], points[1])).distance(this.cadis.getPos()));
        inclinaison = Math.atan((this.cadis.getPos().getY() - points[1]) / (this.cadis.getPos().getX() - points[0])) - Math.atan((points[3] - points[1]) / (points[2] - points[0]));
        if (d * Math.sin(inclinaison) < 10) {


            if (CoeffD > 0.1) {
                if (((this.cadis.getVit().getX()) < 1) && (this.cadis.getVit().getY() < 1)) {
                    this.cadis.setVit(-(1 - 0.01 * k) * this.cadis.normeVit() * Math.cos(Math.atan(CoeffD)), -(1 - 0.01 * k) * this.cadis.normeVit() * Math.sin(Math.atan(CoeffD)));
                } else {
                    this.cadis.setVit((1 - 0.002 * k) * this.cadis.normeVit() * Math.cos(Math.atan(CoeffD)), (1 - 0.002 * k) * this.cadis.normeVit() * Math.sin(Math.atan(CoeffD)));
                }
            }
            if (CoeffD < -0.1) {
                if (((this.cadis.getVit().getX()) < 1) && (this.cadis.getVit().getY() > -1)) {

                    this.cadis.setVit(-this.cadis.normeVit() * Math.cos(Math.atan(CoeffD)), -this.cadis.normeVit() * Math.sin(Math.atan(CoeffD)));
                } else {
                    this.cadis.setVit((1 - 0.01 * k) * this.cadis.normeVit() * Math.cos(Math.atan(CoeffD)), (1 - 0.01 * k) * this.cadis.normeVit() * Math.sin(Math.atan(CoeffD)));
                }
            }
            if ((CoeffD == 0) && (this.cadis.getVit().getX() == 0)) {
                this.cadis.setVit(0, 1);

                this.cadis.setVit(0, 0);
            }
            if ((Math.abs(this.cadis.getVit().getX()) < 0.5) && (Math.abs(this.cadis.getVit().getY()) < 0.5)) {

                this.cadis.setVit(0, 0);

            }
        }
    }

    private void Crossing() {
        for (int i = 1; i < curve.getList().size(); i++) {
            if ((curve.getList().get(i - 1).getX() <= this.cadis.getPos().getX()) && (curve.getList().get(i).getX() >= this.cadis.getPos().getX())) {
                if (this.cadis.getPos().getY() + 10 > curve.getList().get(i - 1).getY() + 15) {
                    this.cadis.getPos().setY(this.cadis.getPos().getY() + (curve.getList().get(i - 1).getY()) - this.cadis.getPos().getY());
                }
            }
        }

    }

    public void Trajectoire(double k, double g) {
        this.cadis.setAcc(-k * this.cadis.getVit().getX() / this.cadis.getPoids(), g);
    }

    public void mouseDragged(MouseEvent e) {

        thisP.setCoords(e.getX(), e.getY());
        monTimer.stop();
        if ((thisP.distance(lastP) > 150) && (e.getX() < 400) && (e.getY() < 800) && (e.getX() > 0) && (e.getY() > 400)) {

            lastP.setCoords(thisP.getX(), thisP.getY());
            maListeP.add(new Point(e.getX(), e.getY()));
            this.curvedrawn = new Courbe(maListeP, Color.blue);
            panneauDessin.update(curvedrawn);

        }
        repaint();

    }

    public void mouseMoved(MouseEvent e) {

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boxMatiere) {
            JComboBox cb = (JComboBox) e.getSource();
            String materiaux = (String) cb.getSelectedItem();
            switch (materiaux) {
                case "Aluminium":
                    coeffF = 3.5;
                    break;
                case "Acier":
                    coeffF = 5;
                    break;
                case "Bois":
                    coeffF = 2;
                    break;
                case "Glace":
                    coeffF = 0;
                    break;
                case "Caoutchouc":
                    coeffF = 8;
                    break;
            }

        }
        if (e.getSource() == monTimer) {
            temps += 50;
            temps2 += Math.PI / 30;

        }


        if (e.getSource() == monBoutonGo) {
            if (this.curvedrawn.verifier()) {

                this.curve = new Courbe(curvedrawn.getList(), Color.black);
                test = false;
                monTimer.start();
                panneauDessin.update(curve);
                sliderGravite.setEnabled(false);
                sliderPoids.setEnabled(false);
                boxMatiere.setEnabled(false);

                cadis.setAcc(0, coeffG/35);

            }

        }
        if (e.getSource() == reStart) {
            sliderPoids.setEnabled(true);
            sliderGravite.setEnabled(true);
            boxMatiere.setEnabled(true);
            this.cadis.setPos(50, 50);
            this.cadis.setAcc(0, 0);
            this.cadis.setVit(0, 0);
            //this.coeffF = parametre.getCoeff();
            //this.coeffG = parametre.getGravite() / 40;
            //this.cadis.setPoids(parametre.getPoids());
            if (this.curve != null) {
                this.curve.getList().clear();
            }
            if (this.curvedrawn != null) {
                this.curvedrawn.getList().clear();
            }

        }
        if (e.getSource() == boutonRetour) {
            this.dispose();
            //dessin.dispose();
            parametre.dispose();
            new MenuNiveaux();
        }
        //MOUVEMENT
        if ((e.getSource() == monTimer) && (win == 3)) {
            if (this.obstacles.get(1).getY() == 100) {
                deplacement1 = 10;
            }
            if (this.obstacles.get(1).getY() == 400) {
                deplacement1 = -10;
            }
            if (this.obstacles.get(2).getY() == -50) {
                deplacement2 = 25;
            }
            if (this.obstacles.get(2).getY() == 500) {
                deplacement2 = -25;
            }

            this.obstacles.get(1).setLocation(this.obstacles.get(1).x, this.obstacles.get(1).y + deplacement1);
            this.obstacles.get(2).setLocation(this.obstacles.get(2).x, this.obstacles.get(2).y + deplacement2);
        }
        if ((e.getSource() == monTimer) && (win == 7)) {

            this.obstacles.get(1).setLocation(this.obstacles.get(1).x + elan, this.obstacles.get(1).y + deplacement1);
            if (this.obstacles.get(1).getY() == 100) {
                deplacement1 = 30;
            }
            if (this.obstacles.get(1).getY() == 490) {
                deplacement1 = 0;
                if (temps % 1000 == 0) {
                    deplacement1 = -30;
                }
            }
            if ((this.obstacles.get(1).getX() >= 490) && (this.obstacles.get(1).getX() <= 700) && (this.obstacles.get(1).getY() == 490)) {
                elan = (int) (Math.random() * 16 - 8);
                System.out.println(elan);
            }
            if ((this.obstacles.get(1).getX() < 490) && (this.obstacles.get(1).getY() == 490)) {
                elan = 5;

            }

            if ((this.obstacles.get(1).getX() > 700) && (this.obstacles.get(1).getY() == 490)) {
                elan = -5;

            }
        }
        if ((e.getSource() == monTimer) && (win == 8)) {
            if (this.obstacles.get(1).getY() == 0) {
                deplacement1 = -20;
            }
            if (this.obstacles.get(1).getY() == -300) {
                deplacement1 = 20;
            }
            if (this.obstacles.get(2).getY() == 400) {
                deplacement2 = 20;
            }
            if (this.obstacles.get(2).getY() == 700) {
                deplacement2 = -20;
            }
            if (temps > 250) {
                if (this.obstacles.get(3).getY() == 0) {
                    deplacement3 = -20;
                }
                if (this.obstacles.get(3).getY() == -300) {
                    deplacement3 = 20;
                }
                if (this.obstacles.get(4).getY() == 400) {
                    deplacement4 = 20;
                }
                if (this.obstacles.get(4).getY() == 700) {
                    deplacement4 = -20;
                }
            }
            if (temps > 500) {
                if (this.obstacles.get(5).getY() == 0) {
                    deplacement5 = -20;
                }
                if (this.obstacles.get(5).getY() == -300) {
                    deplacement5 = 20;
                }
                if (this.obstacles.get(6).getY() == 400) {
                    deplacement6 = 20;
                }
                if (this.obstacles.get(6).getY() == 700) {
                    deplacement6 = -20;
                }
            }
            if (temps > 750) {
                if (this.obstacles.get(7).getY() == 0) {
                    deplacement7 = -20;
                }
                if (this.obstacles.get(7).getY() == -300) {
                    deplacement7 = 20;
                }
                if (this.obstacles.get(8).getY() == 400) {
                    deplacement8 = 20;
                }
                if (this.obstacles.get(8).getY() == 700) {
                    deplacement8 = -20;
                }
            }
            if (temps > 1000) {
                if (this.obstacles.get(9).getY() == 0) {
                    deplacement9 = -20;
                }
                if (this.obstacles.get(9).getY() == -300) {
                    deplacement9 = 20;
                }
                if (this.obstacles.get(10).getY() == 400) {
                    deplacement10 = 20;
                }
                if (this.obstacles.get(10).getY() == 700) {
                    deplacement10 = -20;
                }
            }
            this.obstacles.get(1).setLocation(this.obstacles.get(1).x, this.obstacles.get(1).y + deplacement1);
            this.obstacles.get(2).setLocation(this.obstacles.get(2).x, this.obstacles.get(2).y + deplacement2);
            this.obstacles.get(3).setLocation(this.obstacles.get(3).x, this.obstacles.get(3).y + deplacement3);
            this.obstacles.get(4).setLocation(this.obstacles.get(4).x, this.obstacles.get(4).y + deplacement4);
            this.obstacles.get(5).setLocation(this.obstacles.get(5).x, this.obstacles.get(5).y + deplacement5);
            this.obstacles.get(6).setLocation(this.obstacles.get(6).x, this.obstacles.get(6).y + deplacement6);
            this.obstacles.get(7).setLocation(this.obstacles.get(7).x, this.obstacles.get(7).y + deplacement7);
            this.obstacles.get(8).setLocation(this.obstacles.get(8).x, this.obstacles.get(8).y + deplacement8);
            this.obstacles.get(9).setLocation(this.obstacles.get(9).x, this.obstacles.get(9).y + deplacement9);
            this.obstacles.get(10).setLocation(this.obstacles.get(10).x, this.obstacles.get(10).y + deplacement10);

        }
        if ((e.getSource() == monTimer) && (win == 9)) {
            if ((this.obstacles.get(3).getY() == 500) && (this.obstacles.get(3).getX() == 560)) {
                this.obstacles.get(3).setLocation(860, 340);
                deplacement1 = -20;
            }
            if ((this.obstacles.get(3).getY() == 140) && (this.obstacles.get(3).getX() == 560)) {
                deplacement1 = 20;
            }
            if ((this.obstacles.get(3).getY() == 360) && (this.obstacles.get(3).getX() == 860)) {
                this.obstacles.get(3).setLocation(560, 480);
                deplacement1 = -20;
            }
            if ((this.obstacles.get(3).getY() == 60) && (this.obstacles.get(3).getX() == 860)) {
                deplacement1 = 20;
            }

            this.obstacles.get(3).setLocation(this.obstacles.get(3).x, this.obstacles.get(3).y + deplacement1);

        }
        if ((e.getSource() == monTimer) && (win == 10)) {

            this.obstacles.get(0).setLocation((int) (this.obstacles.get(0).x + 20 * Math.cos(temps2)), (int) (this.obstacles.get(0).y + 12 * Math.sin(temps2)));
            this.obstacles.get(1).setLocation((int) (this.obstacles.get(1).x + 20 * Math.cos(temps2)), (int) (this.obstacles.get(1).y + 12 * Math.sin(temps2)));
            this.obstacles.get(2).setLocation((int) (this.obstacles.get(2).x + 20 * Math.cos(temps2)), (int) (this.obstacles.get(2).y + 12 * Math.sin(temps2)));
            this.obstacles.get(3).setLocation((int) (this.obstacles.get(3).x + 20 * Math.cos(temps2)), (int) (this.obstacles.get(3).y + 12 * Math.sin(temps2)));
            if (temps % 3050 == 0) {

                this.obstacles.get(0).setLocation(820, 300);
                this.obstacles.get(1).setLocation(950, 260);
                this.obstacles.get(2).setLocation(800, 260);
                this.obstacles.get(3).setLocation(800, 310);

            }
        }
        if ((e.getSource() == monTimer) && (curve != null) && (this.curvedrawn.verifier())) {

            cadis.Accelere();
            cadis.Avance();
            //VICTOIRE
            if (new Rectangle((int) this.cadis.getPos().getX(), (int) this.cadis.getPos().getY(), 15, 15).intersects(this.obstacles.get(0))) {
                if ((test == false) && (win < 11)) {
                    //sound.setFile("Sam/soundFX/win.wav");
                    //sound.play();

                    test = true;
                    win++;
                    if (win < 11) {
                        new FenetrePlotCourbe(win);
                    }
                    this.dispose();
                    parametre.dispose();
                    //dessin.dispose();
                }

            }
            if (win == 11) {
                this.dispose();
                parametre.dispose();
                //dessin.dispose();
                win++;
                new Victoire();

            }
            //OBSTACLES
            for (int i = 1; i < this.obstacles.size(); i++) {
                if (new Rectangle((int) this.cadis.getPos().getX(), (int) this.cadis.getPos().getY(), 15, 15).intersects(this.obstacles.get(i))) {
                    this.cadis.setPos(50, 50);
                    this.cadis.setAcc(0, 0);
                    this.cadis.setVit(0, 0);
                    //this.coeffF = parametre.getCoeff();
                    //this.coeffG = parametre.getGravite() / 40;
                    //this.cadis.setPoids(parametre.getPoids());
                    sliderGravite.setEnabled(true);
                    sliderPoids.setEnabled(true);
                    boxMatiere.setEnabled(true);
                    //sound.setFile("Sam/soundFX/echec.wav");
                    //sound.play();
                }
            }
            if ((this.cadis.getPos().getX() < 0) || (this.cadis.getPos().getY() > panneauDessin.getHeight())) {
                this.cadis.setPos(50, 50);
                this.cadis.setAcc(0, 0);
                this.cadis.setVit(0, 0);
                sliderGravite.setEnabled(true);
                sliderPoids.setEnabled(true);
                boxMatiere.setEnabled(true);
                //this.coeffF = parametre.getCoeff();
                //this.coeffG = parametre.getGravite() / 40;
                //this.cadis.setPoids(parametre.getPoids());
                //sound.setFile("Sam/soundFX/echec.wav");
                //sound.play();
            }


            for (int i = 1; i < curve.getList().size(); i++) {
                if ((curve.getList().get(i - 1).getX() <= this.cadis.getPos().getX()) && (curve.getList().get(i).getX() >= this.cadis.getPos().getX())) {
                    if (this.cadis.getPos().getY() + 5 > curve.getList().get(i - 1).getY()) {
                        this.cadis.setAcc(0, 0);
                        Crossing();
                        Collision(coeffF);//de 0(pas de frottements) à 10(frotte beaucoup)

                    }
                }
                if ((this.cadis.getPos().getX() > curve.getList().get(curve.getList().size() - 1).getX()) || ((this.cadis.getPos().getX() > 70) && (this.cadis.getPos().getY() < 425))) {
                    Trajectoire(0.5, coeffG/35);
                }
            }

        }


        repaint();
    }

    public void stateChanged(ChangeEvent e) {
        coeffG = sliderGravite.getValue();
        this.cadis.setPoids(sliderPoids.getValue());


    }


}
