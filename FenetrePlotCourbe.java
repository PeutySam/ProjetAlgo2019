
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


public class FenetrePlotCourbe extends JFrame implements ActionListener, MouseMotionListener, ChangeListener {

    private Courbe curvedrawn;
    private Courbe curve;
    private JButton monBoutonGo;
    private JButton reStart;
    private JButton boutonRetour;
    private JButton help;
    private JButton show;
    private monPanelDessin panneauDessin;
    private long temps;
    private double temps2;
    private Timer monTimer;
    private Vehicule cadis;
    private Image background = null;
    private double coeffF;
    private double coeffG;
    private LinkedList<Obstacle> obstacles;
    private int win;
    private boolean test = false;
    private SoundEffect sound1 = new SoundEffect();
    private SoundEffect sound2 = new SoundEffect();
    private Point lastP;
    private Point thisP;
    private LinkedList<Point> maListeP;
    private JSlider sliderGravite;
    private JSlider sliderPoids;
    private JSlider sliderVolume;
    private float volume;
    private JComboBox<String> boxMatiere;


    public FenetrePlotCourbe(int n) {

        super("Jeu Injouable");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

        win = n;
        coeffG = 50;
        coeffF = 1;
        thisP = new Point(0, 0);
        lastP = new Point(0, 0);
        maListeP = new LinkedList<>();
        curvedrawn = new Courbe(maListeP);
        sound1.setFile("Sam/soundFX/win.wav");
        sound2.setFile("Sam/soundFX/echec.wav");
        volume = -20f;
        sound1.setVolume(volume);
        sound2.setVolume(volume - 10f);


        this.cadis = new Vehicule(50);
        this.setTitle("IHM Courbe - Graphisme ");
        this.setLayout(null);
        this.setSize((int) (screensize.getWidth()), (int) (0.92 * screensize.getHeight()));
        this.setLocation(0, 0);
        this.setResizable(false);
        this.setVisible(true);

        help = new JButton(new ImageIcon("Sam/images/question_mark2.png"));
        help.setBounds(5, 5, 40, 40);
        help.addActionListener(this);


        monBoutonGo = new JButton("Démarrer");
        monBoutonGo.setBounds(75, (int) (0.05 * screensize.getHeight()), 150, 60);
        monBoutonGo.addActionListener(this);

        boutonRetour = new JButton("Retour");
        boutonRetour.setBounds(75, (int) (0.25 * screensize.getHeight()), 150, 60);
        boutonRetour.addActionListener(this);

        reStart = new JButton("Recommencer");
        reStart.setBounds(75, (int) (0.15 * screensize.getHeight()), 150, 60);
        reStart.addActionListener(this);

        show = new JButton("show hitboxes");
        show.setBounds(75, (int) (0.35 * screensize.getHeight()), 150, 60);
        show.addActionListener(this);

        JPanel zoneBouton = new JPanel();
        zoneBouton.setBounds(20, 20, 300, 900);
        zoneBouton.setLayout(null);
        zoneBouton.setBackground(Color.gray);
        zoneBouton.add(monBoutonGo);
        zoneBouton.add(boutonRetour);
        zoneBouton.add(reStart);
        zoneBouton.add(help);
        zoneBouton.add(show);

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

        sliderVolume = new JSlider(0, 50, 25);
        sliderVolume.setSize(250, 50);
        sliderVolume.setLocation(20, 800);
        sliderVolume.addChangeListener(this);
        zoneBouton.add(sliderVolume);

        JLabel etiquetteVolume = new JLabel("FX Volume");
        etiquetteVolume.setSize(50, 20);
        etiquetteVolume.setLocation(20, 775);
        zoneBouton.add(etiquetteVolume);

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
        int w = (int) (0.8 * screensize.getWidth());
        int h = (int) (0.85 * screensize.getHeight());

        //SELECTION NIVEAU
        switch (n) {
            case 1:
                try {
                    background = ImageIO.read(new File("Sam/images/background1.png"));
                    Obstacle gg = new Obstacle((int) (w * 0.618), (int) (h * 0.247), (int) (w * 0.098), (int) (h * 0.033));
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle((int) (w * 0.716), (int) (h * 0.222), (int) (w * 0.015), (int) (h * 0.0587));
                    Obstacle gameOver2 = new Obstacle((int) (w * 0.603), (int) (h * 0.222), (int) (w * 0.015), (int) (h * 0.0587));
                    Obstacle gameOver3 = new Obstacle((int) (w * 0.603), (int) (h * 0.278), (int) (w * 0.128), (int) (h * 0.0223));
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);

                } catch (IOException ex) {
                    System.out.println(ex);

                }
                break;
            case 2:
                try {
                    background = ImageIO.read(new File("Sam/images/background2.png"));
                    Obstacle gg = new Obstacle((int) (w * 0.829), 0, (int) (w * 0.008), 1000);
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle((int) (w * 0.414), (int) (h * 0.612), (int) (w * 0.060), (int) (h * 0.452));
                    Obstacle gameOver2 = new Obstacle((int) (w * 0.414), 0, (int) (w * 0.060), (int) (h * 0.301));
                    Obstacle gameOver3 = new Obstacle((int) (w * 0.528), (int) (h * 0.501), (int) (w * 0.060), (int) (h * 0.501));
                    Obstacle gameOver4 = new Obstacle((int) (w * 0.528), 0, (int) (w * 0.060), (int) (h * 0.256));
                    Obstacle gameOver5 = new Obstacle((int) (w * 0.641), (int) (h * 0.590), (int) (w * 0.060), (int) (h * 0.452));
                    Obstacle gameOver6 = new Obstacle((int) (w * 0.641), 0, (int) (w * 0.060), (int) (h * 0.334));

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
                    Obstacle gg = new Obstacle(1000, 250, 80, 80);
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle(500, 400, 30, 145);
                    Obstacle gameOver2 = new Obstacle(700, -50, 30, 200);
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);


                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 4:
                try {
                    background = ImageIO.read(new File("Sam/images/background4.jpg"));
                    Obstacle gg = new Obstacle((int) (w * 0.392), (int) (h * 0.668), (int) (w * 0.1515), (int) (h * 0.0223));
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle((int) (w * 0.317), 0, (int) (w * 0.075), (int) (h * 0.278));
                    Obstacle gameOver2 = new Obstacle((int) (w * 0.317), (int) (h * 0.445), (int) (w * 0.075), (int) (h * 0.668));
                    Obstacle gameOver3 = new Obstacle((int) (w * 0.543), 0, (int) (w * 0.060), (int) (h * 1.114));
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
                    Obstacle gg = new Obstacle((int) (w * 0.754), (int) (h * 0.668), (int) (w * 0.113), (int) (h * 0.2));
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle((int) (w * 0.452), 0, (int) (w * 0.086), (int) (h * 0.111));
                    Obstacle gameOver2 = new Obstacle((int) (w * 0.452), (int) (h * 0.25), (int) (w * 0.086), (int) (h * 0.9));
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 6:
                try {
                    background = ImageIO.read(new File("Sam/images/background6.png"));
                    Obstacle gg = new Obstacle((int) (w * 0.656), (int) (h * 0.078), (int) (w * 0.015), (int) (h * 0.111));
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle((int) (w * 0.603), (int) (h * 0.056), (int) (w * 0.452), (int) (h * 0.022));
                    Obstacle gameOver2 = new Obstacle((int) (w * 0.603), (int) (h * 0.189), (int) (w * 0.452), (int) (h * 0.022));
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 7:
                try {
                    background = ImageIO.read(new File("Sam/images/background7.jpg"));

                    Obstacle gg = new Obstacle((int) (w * 0.839), (int) (h * 0.535), (int) (w * 0.075), (int) (h * 0.111));
                    obstacles.add(gg);
                    Obstacle gameOver = new Obstacle(600, 490, 150, 150);
                    obstacles.add(gameOver);


                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 8:
                try {
                    background = ImageIO.read(new File("Sam/images/background8.png"));
                    Obstacle gg = new Obstacle(1250, 0, 20, 900);
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle(550, 0, 80, 400);
                    Obstacle gameOver2 = new Obstacle(550, 400, 80, 600);
                    Obstacle gameOver3 = new Obstacle(650, 0, 80, 400);
                    Obstacle gameOver4 = new Obstacle(650, 400, 80, 600);
                    Obstacle gameOver5 = new Obstacle(750, 0, 80, 400);
                    Obstacle gameOver6 = new Obstacle(750, 400, 80, 600);
                    Obstacle gameOver7 = new Obstacle(850, 0, 80, 400);
                    Obstacle gameOver8 = new Obstacle(850, 400, 80, 600);
                    Obstacle gameOver9 = new Obstacle(950, 0, 80, 400);
                    Obstacle gameOver10 = new Obstacle(950, 400, 80, 600);
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
                    background = ImageIO.read(new File("Sam/images/background9.png"));
                    Obstacle gg = new Obstacle((int) (w * 0.830), (int) (h * 0.612), (int) (w * 0.075), (int) (h * 0.111));
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle((int) (w * 0.350), (int) (h * 0.557), (int) (w * 0.092), (int) (h * 0.454));
                    Obstacle gameOver2 = new Obstacle((int) (w * 0.550), (int) (h * 0.390), (int) (w * 0.090), (int) (h * 0.621));
                    Obstacle gameOver3 = new Obstacle(560, 140, 100, 100);

                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);


                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 10:
                try {
                    background = ImageIO.read(new File("Sam/images/background10.jpg"));
                    Obstacle gg = new Obstacle(820, 300, 130, 10);
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle(950, 260, 20, 50);
                    Obstacle gameOver2 = new Obstacle(800, 260, 20, 50);
                    Obstacle gameOver3 = new Obstacle(800, 310, 170, 20);
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 11:
                try {
                    background = ImageIO.read(new File("Sam/images/background11.gif"));
                    Obstacle gg = new Obstacle((int) (w * 0.852), (int) (h * 0.780), (int) (w * 0.098), (int) (h * 0.011));
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle((int) (w * 0.301), (int) (-h * 0.083), (int) (w * 0.053), (int) (h * 0.078));
                    Obstacle gameOver2 = new Obstacle((int) (w * 0.452), (int) (-h * 0.083), (int) (w * 0.053), (int) (h * 0.078));
                    Obstacle gameOver3 = new Obstacle((int) (w * 0.603), (int) (-h * 0.083), (int) (w * 0.053), (int) (h * 0.078));
                    Obstacle gameOver4 = new Obstacle((int) (w * 0.754), (int) (-h * 0.083), (int) (w * 0.053), (int) (h * 0.078));
                    Obstacle gameOver5 = new Obstacle((int) (w * 0.904), (int) (-h * 0.083), (int) (w * 0.053), (int) (h * 0.078));
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);
                    obstacles.add(gameOver4);
                    obstacles.add(gameOver5);


                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 12:
                try {

                    background = ImageIO.read(new File("Sam/images/background12.jpg"));
                    Obstacle gg = new Obstacle((int) (w * 0.724), (int) (h * 0.972), (int) (w * 0.1694), (int) (h * 0.022));
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle((int) (w * 0.272), (int) (h * 0.857), (int) (w * 0.085), (int) (h * 0.143));
                    Obstacle gameOver2 = new Obstacle((int) (w * 0.355), (int) (h * 0.728), (int) (w * 0.095), (int) (h * 0.272));
                    Obstacle gameOver3 = new Obstacle((int) (w * 0.44), (int) (h * 0.837), (int) (w * 0.055), (int) (h * 0.163));
                    Obstacle gameOver4 = new Obstacle((int) (w * 0.573), (int) (h * 0.707), (int) (w * 0.151), (int) (h * 0.293));
                    Obstacle gameOver5 = new Obstacle((int) (w * 0.894), (int) (h * 0.791), (int) (w * 0.106), (int) (h * 0.210));
                    Obstacle gameOver6 = new Obstacle((int) (w * 0.214), (int) (h * 0.27), (int) (w * 0.078), (int) (h * 0.070));
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
            case 13:
                try {

                    background = ImageIO.read(new File("Sam/images/background13.png"));
                    Obstacle gg = new Obstacle((int) (w * 0.830), (int) (h * 0.557), (int) (w * 0.098), (int) (h * 0.098));
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle((int) (w * 0.452), 0, (int) (w * 0.038), (int) (h * 0.056));
                    Obstacle gameOver2 = new Obstacle((int) (w * 0.603), 0, (int) (w * 0.038), (int) (h * 0.056));
                    Obstacle gameOver3 = new Obstacle((int) (w * 0.710), 0, (int) (w * 0.038), (int) (h * 0.056));
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);

                } catch (IOException ex) {
                    System.out.println(ex);

                }
                break;
            case 14:
                try {
                    background = ImageIO.read(new File("Sam/images/background15.gif"));
                    Obstacle gg = new Obstacle(1270, 250, 100, 100);
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle(600, 50, 50, 50);
                    Obstacle gameOver2 = new Obstacle(600, 50, 50, 50);
                    Obstacle gameOver3 = new Obstacle(600, 600, 50, 50);
                    Obstacle gameOver4 = new Obstacle(600, 600, 50, 50);
                    Obstacle gameOver5 = new Obstacle(1100, 600, 50, 50);
                    Obstacle gameOver6 = new Obstacle(1100, 600, 50, 50);
                    Obstacle gameOver7 = new Obstacle(1100, 50, 50, 50);
                    Obstacle gameOver8 = new Obstacle(1100, 50, 50, 50);
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);
                    obstacles.add(gameOver4);
                    obstacles.add(gameOver5);
                    obstacles.add(gameOver6);
                    obstacles.add(gameOver7);
                    obstacles.add(gameOver8);
                } catch (IOException ex) {
                    System.out.println(ex);

                }
                break;
            case 15:
                try {
                    background = ImageIO.read(new File("Sam/images/background15.jpg"));
                    Obstacle gg = new Obstacle(700, 550, 100, 100);
                    obstacles.add(gg);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;

            case 16:
                try {
                    background = ImageIO.read(new File("Sam/images/background16.jpg"));
                    Obstacle gg = new Obstacle(1180, 400, 70, 70);
                    obstacles.add(gg);
                    Obstacle gameOver1 = new Obstacle((int) (w * 0.325), (int) (h * 0.217), (int) (w * 0.084), (int) (h * 0.038));
                    Obstacle gameOver2 = new Obstacle((int) (w * 0.39), (int) (h * 0.272), (int) (w * 0.045), (int) (h * 0.027));
                    Obstacle gameOver3 = new Obstacle((int) (w * 0.36), (int) (h * 0.49), (int) (w * 0.075), (int) (h * 0.035));
                    Obstacle gameOver4 = new Obstacle((int) (w * 0.36), (int) (h * 0.065), (int) (w * 0.075), (int) (h * 0.035));
                    obstacles.add(gameOver1);
                    obstacles.add(gameOver2);
                    obstacles.add(gameOver3);
                    obstacles.add(gameOver4);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
            case 17:
                try {
                    background = ImageIO.read(new File("Sam/images/background17.png"));
                    Obstacle gg = new Obstacle(1150, 825, 95, 85);
                    obstacles.add(gg);
                    int k = 1;
                    for (int i = 0; i < 75; i++) {
                        if (k <= 10) {
                            obstacles.add(new Obstacle(i * 25, 920 - k * 12, 25, 600));
                            obstacles.get(i).setVit(0, -3);
                        }
                        if (k > 10) {
                            obstacles.add(new Obstacle(i * 25, 680 + k * 12, 25, 600));
                            obstacles.get(i).setVit(0, 3);
                            if (k == 19) {
                                k = 1;
                            }
                        }
                        k++;

                    }
                    obstacles.add(new Obstacle(900, 925, 50, 50));
                    obstacles.add(new Obstacle(900, 925, 50, 50));

                } catch (IOException ex) {
                    System.out.println(ex);
                }
                break;
        }


        panneauDessin = new monPanelDessin(cadis, background, obstacles, win);
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

    private void Collision(double k, Point p1, Point p2) {

        double coeffD = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());


        if (coeffD > 0.1) {
            if (((this.cadis.getVit().getX()) < 1) && (this.cadis.getVit().getY() < 1)) {
                this.cadis.setVit(-(1 - 0.01 * k) * this.cadis.normeVit() * Math.cos(Math.atan(coeffD)), -(1 - 0.01 * k) * this.cadis.normeVit() * Math.sin(Math.atan(coeffD)));
            } else {
                this.cadis.setVit((1 - 0.002 * k) * this.cadis.normeVit() * Math.cos(Math.atan(coeffD)), (1 - 0.002 * k) * this.cadis.normeVit() * Math.sin(Math.atan(coeffD)));
            }
        }
        if (coeffD < -0.1) {
            if (((this.cadis.getVit().getX()) < 1) && (this.cadis.getVit().getY() > -1)) {

                this.cadis.setVit(-this.cadis.normeVit() * Math.cos(Math.atan(coeffD)), -this.cadis.normeVit() * Math.sin(Math.atan(coeffD)));
            } else {
                this.cadis.setVit((1 - 0.01 * k) * this.cadis.normeVit() * Math.cos(Math.atan(coeffD)), (1 - 0.01 * k) * this.cadis.normeVit() * Math.sin(Math.atan(coeffD)));
            }
        }
        if ((coeffD == 0) && (this.cadis.getVit().getX() == 0)) {
            this.cadis.setVit(0, 1);

            this.cadis.setVit(0, 0);
        }
        if ((Math.abs(this.cadis.getVit().getX()) < 0.5) && (Math.abs(this.cadis.getVit().getY()) < 0.5)) {

            this.cadis.setVit(0, 0);

        }

    }

    private void Crossing(Point p1) {

        if (this.cadis.getPos().getY() + 10 > p1.getY() + 15) {
            this.cadis.getPos().setY(this.cadis.getPos().getY() + (p1.getY()) - this.cadis.getPos().getY());
        }


    }

    public void Trajectoire(double k, double g) {
        this.cadis.setAcc(-k * this.cadis.getVit().getX() / this.cadis.getPoids(), g);
    }

    public void mouseDragged(MouseEvent e) {

        panneauDessin.setAnim(false);
        thisP.setCoords(e.getX(), e.getY());
        monTimer.stop();
        if ((thisP.distance(lastP) > 150) && (e.getX() < 400) && (e.getY() < 800) && (e.getX() > 0) && (e.getY() > 400)) {

            lastP.setCoords(thisP.getX(), thisP.getY());
            maListeP.add(new Point(e.getX(), e.getY()));
            this.curvedrawn = new Courbe(maListeP);
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
                    coeffF = 6;
                    break;
                case "Bois":
                    coeffF = 4.5;
                    break;
                case "Glace":
                    coeffF = 2;
                    break;
                case "Caoutchouc":
                    coeffF = 8;
                    break;
            }

        }
        if (e.getSource() == monTimer) {
            temps += 50;
            temps2 += Math.PI / 60;
            if (this.cadis.getVit().getX() != 0) {
                this.cadis.setInclinaison(Math.toDegrees(Math.atan(this.cadis.getVit().getY() / this.cadis.getVit().getX())));
            }


        }

        if (e.getSource() == monBoutonGo) {
            if (this.curvedrawn.verifier()) {

                this.curve = new Courbe(curvedrawn.getList());
                test = false;
                monTimer.start();


                panneauDessin.update(curve);
                sliderGravite.setEnabled(false);
                sliderPoids.setEnabled(false);
                boxMatiere.setEnabled(false);

                cadis.setAcc(0, coeffG / 35);

            }

        }
        if (e.getSource() == reStart) {
            sliderPoids.setEnabled(true);
            sliderGravite.setEnabled(true);
            boxMatiere.setEnabled(true);

            this.cadis.setPos(50, 50);
            this.cadis.setAcc(0, 0);
            this.cadis.setVit(0, 0);
            this.cadis.setInclinaison(0);

            if (this.curve != null) {
                this.curve.getList().clear();
            }
            if (this.curvedrawn != null) {
                this.curvedrawn.getList().clear();
            }

        }
        if (e.getSource() == boutonRetour) {
            this.dispose();
            new MenuNiveaux(win);
        }

        if (e.getSource() == show) {
            panneauDessin.setShow(!panneauDessin.getShow());
        }
        if (e.getSource() == help) {
            new Aide();
        }

        if ((e.getSource() == monTimer) && (curve != null) && (this.curvedrawn.verifier())) {

            cadis.Accelere();
            cadis.Avance();


            //ECHEC
            for (int i = 1; i < this.obstacles.size(); i++) {
                if (new Rectangle((int) this.cadis.getPos().getX(), (int) this.cadis.getPos().getY(), 15, 15).intersects(this.obstacles.get(i))) {
                    this.cadis.setPos(50, 50);
                    this.cadis.setAcc(0, 0);
                    this.cadis.setVit(0, 0);

                    sliderGravite.setEnabled(true);
                    sliderPoids.setEnabled(true);
                    boxMatiere.setEnabled(true);

                    this.cadis.setInclinaison(0);

                    sound2.play();
                }
            }
            if ((this.cadis.getPos().getX() < 0) || (this.cadis.getPos().getY() > panneauDessin.getHeight())) {
                this.cadis.setPos(50, 50);
                this.cadis.setAcc(0, 0);
                this.cadis.setVit(0, 0);
                sliderGravite.setEnabled(true);
                sliderPoids.setEnabled(true);
                boxMatiere.setEnabled(true);
                this.cadis.setInclinaison(0);


                sound2.play();
            }


            for (int i = 1; i < curve.getList().size(); i++) {
                if ((curve.getList().get(i - 1).getX() <= this.cadis.getPos().getX()) && (curve.getList().get(i).getX() >= this.cadis.getPos().getX())) {
                    if (this.cadis.getPos().getY() + 5 > curve.getList().get(i - 1).getY()) {
                        this.cadis.setAcc(0, 0);

                        Crossing(curve.getList().get(i - 1));
                        Collision(coeffF, curve.getList().get(i - 1), curve.getList().get(i));

                    }
                }
                if ((curve.getList().get(i - 1).getX() >= this.cadis.getPos().getX()) && (curve.getList().get(i).getX() <= this.cadis.getPos().getX())) {
                    if (this.cadis.getPos().getY() + 5 > curve.getList().get(i - 1).getY()) {
                        this.cadis.setAcc(0, 0);
                        Crossing(curve.getList().get(i));
                        Collision(coeffF, curve.getList().get(i), curve.getList().get(i - 1));

                    }
                }
                if (this.curve.getList().get(curve.getList().size() - 1).getX() > this.curve.getList().get(0).getX()) {
                    if ((this.cadis.getPos().getX() > curve.getList().get(curve.getList().size() - 1).getX()) || ((this.cadis.getPos().getX() > 70) && (this.cadis.getPos().getY() < 425))) {
                        Trajectoire(0.5, coeffG / 35);
                    }
                } else {
                    if ((this.cadis.getPos().getX() > curve.getList().get(0).getX()) || ((this.cadis.getPos().getX() > 70) && (this.cadis.getPos().getY() < 425))) {
                        Trajectoire(0.5, coeffG / 35);
                    }
                }
            }
        }


        //MOUVEMENT
        if ((e.getSource() == monTimer) && (win == 3)) {
            if (this.obstacles.get(1).getY() == 100) {
                this.obstacles.get(1).setVit(0, 10);
            }
            if (this.obstacles.get(1).getY() == 400) {
                this.obstacles.get(1).setVit(0, -10);
            }
            if (this.obstacles.get(2).getY() == -50) {
                this.obstacles.get(2).setVit(0, 25);
            }
            if (this.obstacles.get(2).getY() == 500) {
                this.obstacles.get(2).setVit(0, -25);
            }

            this.obstacles.get(1).move();
            this.obstacles.get(2).move();
        }
        if ((e.getSource() == monTimer) && (win == 7)) {

            this.obstacles.get(1).move();
            if (this.obstacles.get(1).getY() == 100) {
                this.obstacles.get(1).setVit((int) this.obstacles.get(1).getVit().getX(), 30);
            }
            if (this.obstacles.get(1).getY() == 490) {
                this.obstacles.get(1).setVit(0, 0);
                if (temps % 600 == 0) {
                    this.obstacles.get(1).setVit((int) (Math.random() * 16 - 8), -30);
                }
            }
            if ((this.obstacles.get(1).getX() < 490) && (this.obstacles.get(1).getY() == 490)) {
                this.obstacles.get(1).setVit(5, (int) this.obstacles.get(1).getVit().getY());
            }
            if ((this.obstacles.get(1).getX() > 700) && (this.obstacles.get(1).getY() == 490)) {
                this.obstacles.get(1).setVit(-5, (int) this.obstacles.get(1).getVit().getY());

            }
        }
        if ((e.getSource() == monTimer) && (win == 8)) {
            for (Obstacle ob : this.obstacles) {
                if (ob.getY() == 0) {
                    ob.setVit(0, -20);
                }
                if (ob.getY() == -300) {
                    ob.setVit(0, 20);
                }
                if (ob.getY() == 400) {
                    ob.setVit(0, 20);
                }
                if (ob.getY() == 700) {
                    ob.setVit(0, -20);
                }
            }
            this.obstacles.get(1).move();
            this.obstacles.get(2).move();
            if (temps > 250) {
                this.obstacles.get(3).move();
                this.obstacles.get(4).move();
            }
            if (temps > 500) {
                this.obstacles.get(5).move();
                this.obstacles.get(6).move();
            }
            if (temps > 750) {
                this.obstacles.get(7).move();
                this.obstacles.get(8).move();
            }
            if (temps > 1000) {
                this.obstacles.get(9).move();
                this.obstacles.get(10).move();
            }


        }
        if ((e.getSource() == monTimer) && (win == 9)) {
            if ((this.obstacles.get(3).getY() == 500) && (this.obstacles.get(3).getX() == 560)) {
                this.obstacles.get(3).setLocation(860, 340);
                this.obstacles.get(3).setVit(0, -20);
            }
            if ((this.obstacles.get(3).getY() == 140) && (this.obstacles.get(3).getX() == 560)) {
                this.obstacles.get(3).setVit(0, 20);
            }
            if ((this.obstacles.get(3).getY() == 360) && (this.obstacles.get(3).getX() == 860)) {
                this.obstacles.get(3).setLocation(560, 480);
                this.obstacles.get(3).setVit(0, -20);
            }
            if ((this.obstacles.get(3).getY() == 60) && (this.obstacles.get(3).getX() == 860)) {
                this.obstacles.get(3).setVit(0, 20);
            }

            this.obstacles.get(3).move();

        }
        if ((e.getSource() == monTimer) && (win == 10)) {

            for (Obstacle ob : this.obstacles) {
                ob.setVit((int) (10 * (Math.cos(temps2))), (int) (5 * Math.sin(temps2)));
                ob.move();
            }
            if (temps % 6000 == 0) {

                this.obstacles.get(0).setLocation(820, 300);
                this.obstacles.get(1).setLocation(950, 260);
                this.obstacles.get(2).setLocation(800, 260);
                this.obstacles.get(3).setLocation(800, 310);

            }
        }
        if ((e.getSource() == monTimer) && (win == 11)) {


            this.obstacles.get(1).setLocation(this.obstacles.get(1).x, this.obstacles.get(1).y + 30);
            this.obstacles.get(2).setLocation(this.obstacles.get(2).x, this.obstacles.get(2).y + 22);
            this.obstacles.get(3).setLocation(this.obstacles.get(3).x, this.obstacles.get(3).y + 20);
            this.obstacles.get(4).setLocation(this.obstacles.get(4).x, this.obstacles.get(4).y + 23);
            this.obstacles.get(5).setLocation(this.obstacles.get(5).x, this.obstacles.get(5).y + 15);
            if ((this.obstacles.get(1).getY() > 900)) {
                this.obstacles.get(1).setLocation(400, -275);
            }
            if ((this.obstacles.get(2).getY() > 900)) {
                this.obstacles.get(2).setLocation(600, -275);
            }
            if ((this.obstacles.get(3).getY() > 900)) {
                this.obstacles.get(3).setLocation(800, -275);
            }
            if ((this.obstacles.get(4).getY() > 900)) {
                this.obstacles.get(4).setLocation(1000, -175);
            }
            if ((this.obstacles.get(5).getY() > 900)) {
                this.obstacles.get(5).setLocation(1200, -95);
            }


        }

        if ((e.getSource() == monTimer) && (win == 12)) {

            if ((this.obstacles.get(6).getX() > 1100)) {
                this.obstacles.get(6).setVit(-20, 0);
            }
            if ((this.obstacles.get(6).getX() < 400)) {
                this.obstacles.get(6).setVit(20, 0);
            }
            this.obstacles.get(6).move();

        }
        if ((e.getSource() == monTimer) && (win == 13)) {

            this.obstacles.get(1).getVit().setX((int) (30 * (Math.cos(2 * temps))));
            this.obstacles.get(2).getVit().setX((int) (10 * (Math.sin(0.5 * temps))));
            this.obstacles.get(3).getVit().setX((int) (20 * (Math.cos(temps))));


            this.obstacles.get(1).move();

            if (temps > 1000) {
                this.obstacles.get(3).move();
            }
            if (temps > 2300) {
                this.obstacles.get(2).move();
            }
            for (Obstacle ob : this.obstacles) {
                if (ob.getY() == 800) {
                    ob.setVit((int) ob.getVit().getX(), -20);
                }
                if (ob.getY() == 0) {
                    ob.setVit((int) ob.getVit().getX(), 20);
                }
            }
        }
        if ((e.getSource() == monTimer) && (win == 14)) {
            for (Obstacle rec : obstacles) {
                if ((rec.getX() == 600) && (rec.getY() == 600)) {
                    rec.setVit(10, 0);
                }
                if ((rec.getX() == 1100) && (rec.getY() == 600)) {
                    rec.setVit(0, -10);
                }
                if ((rec.getX() == 1100) && (rec.getY() == 50)) {
                    rec.setVit(-10, 0);
                }
                if ((rec.getX() == 600) && (rec.getY() == 50)) {
                    rec.setVit(0, 10);
                }

            }
            this.obstacles.get(1).move();
            this.obstacles.get(3).move();
            this.obstacles.get(5).move();
            this.obstacles.get(7).move();
            if (temps > 1250) {
                this.obstacles.get(2).move();
                this.obstacles.get(4).move();
                this.obstacles.get(6).move();
                this.obstacles.get(8).move();

            }
        }
        if ((e.getSource() == monTimer) && (win == 15)) {
            int a = 0;

            if (temps % 1500 == 0) {
                a = (int) Math.floor((Math.random() * 5) + 1);
            }

            switch (a) {
                case 1:
                    this.obstacles.get(0).setLocation(1000, 100);
                    break;
                case 2:
                    this.obstacles.get(0).setLocation(1100, 500);
                    break;
                case 3:
                    this.obstacles.get(0).setLocation(400, 300);
                    break;
                case 4:
                    this.obstacles.get(0).setLocation(800, 500);
                    break;
                case 5:
                    this.obstacles.get(0).setLocation(600, 600);
                    break;
            }
        }

        if ((e.getSource() == monTimer) && (win == 16)) {

            if (temps % 700 == 0) {
                if (this.obstacles.get(1).x < 400) {
                    this.obstacles.get(1).setVit(20, (int) this.obstacles.get(1).getVit().getY());
                } else {
                    if (this.obstacles.get(1).x > 1100) {
                        this.obstacles.get(1).setVit(-20, (int) this.obstacles.get(1).getVit().getY());
                    } else {
                        this.obstacles.get(1).setVit((int) (40 * Math.random() - 20), (int) this.obstacles.get(1).getVit().getY());
                    }
                }
                if (this.obstacles.get(1).y < 270) {
                    this.obstacles.get(1).setVit((int) this.obstacles.get(1).getVit().getX(), 20);
                } else {
                    if (this.obstacles.get(1).y > 600) {
                        this.obstacles.get(1).setVit((int) this.obstacles.get(1).getVit().getX(), -20);
                    } else {
                        this.obstacles.get(1).setVit((int) this.obstacles.get(1).getVit().getX(), (int) (40 * Math.random() - 20));
                    }
                }


                if (this.obstacles.get(2).x < 400) {
                    this.obstacles.get(2).setVit(15, (int) this.obstacles.get(2).getVit().getY());
                } else {
                    if (this.obstacles.get(2).x > 1100) {
                        this.obstacles.get(2).setVit(-15, (int) this.obstacles.get(2).getVit().getY());
                    } else {
                        this.obstacles.get(2).setVit((int) (30 * Math.random() - 15), (int) this.obstacles.get(2).getVit().getY());
                    }
                }
                if (this.obstacles.get(2).y < 270) {
                    this.obstacles.get(2).setVit((int) this.obstacles.get(2).getVit().getX(), 15);
                } else {
                    if (this.obstacles.get(2).y > 620) {
                        this.obstacles.get(2).setVit((int) this.obstacles.get(2).getVit().getX(), -15);
                    } else {
                        this.obstacles.get(2).setVit((int) this.obstacles.get(2).getVit().getX(), (int) (30 * Math.random() - 15));
                    }
                }


                if (this.obstacles.get(3).x < 400) {
                    this.obstacles.get(3).setVit(16, (int) this.obstacles.get(3).getVit().getY());
                } else {
                    if (this.obstacles.get(3).x > 1100) {
                        this.obstacles.get(3).setVit(-16, (int) this.obstacles.get(3).getVit().getY());
                    } else {
                        this.obstacles.get(3).setVit((int) (36 * Math.random() - 16), (int) this.obstacles.get(3).getVit().getY());
                    }
                }

                if (this.obstacles.get(3).y < 450) {
                    this.obstacles.get(3).setVit((int) this.obstacles.get(3).getVit().getX(), 16);
                } else {
                    if (this.obstacles.get(3).y > 720) {
                        this.obstacles.get(3).setVit((int) this.obstacles.get(3).getVit().getX(), -16);
                    } else {
                        this.obstacles.get(3).setVit((int) this.obstacles.get(3).getVit().getX(), (int) (32 * Math.random() - 16));
                    }
                }


                if (this.obstacles.get(4).x < 400) {
                    this.obstacles.get(4).setVit(16, (int) this.obstacles.get(4).getVit().getY());
                } else {
                    if (this.obstacles.get(4).x > 1100) {
                        this.obstacles.get(4).setVit(-16, (int) this.obstacles.get(4).getVit().getY());
                    } else {
                        this.obstacles.get(4).setVit((int) (36 * Math.random() - 16), (int) this.obstacles.get(4).getVit().getY());
                    }
                }
                if (this.obstacles.get(4).y < 100) {
                    this.obstacles.get(4).setVit((int) this.obstacles.get(4).getVit().getX(), 16);
                } else {
                    if (this.obstacles.get(4).y > 600) {
                        this.obstacles.get(4).setVit((int) this.obstacles.get(4).getVit().getX(), -16);
                    } else {

                        this.obstacles.get(4).setVit((int) this.obstacles.get(4).getVit().getX(), (int) (32 * Math.random() - 16));
                    }
                }

            }
            for (Obstacle ob : this.obstacles) {
                if ((ob.intersects(this.obstacles.get(1))) && (ob != this.obstacles.get(1))) {
                    ob.setVit(-(int) ob.getVit().getX(), -(int) ob.getVit().getY());
                    this.obstacles.get(1).setVit(-(int) this.obstacles.get(1).getVit().getX(), -(int) this.obstacles.get(1).getVit().getY());

                }
                if ((ob.intersects(this.obstacles.get(2))) && (ob != this.obstacles.get(2))) {
                    ob.setVit(-(int) ob.getVit().getX(), -(int) ob.getVit().getY());
                    this.obstacles.get(2).setVit(-(int) this.obstacles.get(2).getVit().getX(), -(int) this.obstacles.get(2).getVit().getY());
                }
                if ((ob.intersects(this.obstacles.get(3))) && (ob != this.obstacles.get(3))) {
                    ob.setVit(-(int) ob.getVit().getX(), -(int) ob.getVit().getY());
                    this.obstacles.get(3).setVit(-(int) this.obstacles.get(3).getVit().getX(), -(int) this.obstacles.get(3).getVit().getY());
                }
                if ((ob.intersects(this.obstacles.get(4))) && (ob != this.obstacles.get(4))) {
                    ob.setVit(-(int) ob.getVit().getX(), -(int) ob.getVit().getY());
                    this.obstacles.get(4).setVit(-(int) this.obstacles.get(4).getVit().getX(), -(int) this.obstacles.get(4).getVit().getY());
                }
                ob.move();
            }

        }
        if ((e.getSource() == monTimer) && (win == 17)) {
            for (Obstacle ob : this.obstacles) {

                if ((obstacles.indexOf(ob) != 0) && (obstacles.indexOf(ob) != 76) && (obstacles.indexOf(ob) != 77) && (obstacles.indexOf(ob) != 78)) {
                    ob.move();
                    if (ob.y >= 908) {
                        ob.setVit(0, -3);
                    }
                    if (ob.y <= 800) {
                        ob.setVit(0, 3);
                    }
                }
            }
            if (this.obstacles.get(76).y > 930) {
                this.obstacles.get(76).setLocation(1000, 925);
            } else {
                this.obstacles.get(76).setLocation(this.obstacles.get(76).x + 3, 700 + (int) (0.01 * Math.pow((this.obstacles.get(76).x - 1150), 2)));
            }
            if (this.obstacles.get(77).y > 930) {
                this.obstacles.get(77).setLocation(1000, 925);
            } else {
                if ((this.obstacles.get(76).y < 710) || (this.obstacles.get(77).y < 925)) {
                    this.obstacles.get(77).setLocation(this.obstacles.get(77).x + 3, 700 + (int) (0.01 * Math.pow((this.obstacles.get(77).x - 1150), 2)));
                }
            }


        }

        //VICTOIRE
        if (new Rectangle((int) this.cadis.getPos().getX(), (int) this.cadis.getPos().getY(), 15, 15).intersects(this.obstacles.get(0))) {
            if ((!test) && (win < 18)) {

                sound1.play();

                test = true;
                win++;
                if (win < 18) {
                    new FenetrePlotCourbe(win);
                }
                this.dispose();
                monTimer.stop();


            }

        }
        if (win == 18) {
            this.dispose();

            win++;
            new Victoire();

        }


        panneauDessin.setAnim(true);
        repaint();

    }


    public void stateChanged(ChangeEvent e) {
        coeffG = sliderGravite.getValue();
        this.cadis.setPoids(sliderPoids.getValue());
        volume = (float) sliderVolume.getValue() - 35f;
        if (sliderVolume.getValue() == 0) {
            volume = -80f;
        }
        sound1.setVolume(volume - 10f);
        sound2.setVolume(volume);


    }


}