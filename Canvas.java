import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;


public class Canvas extends JFrame implements MouseMotionListener, ActionListener {

    private Point lastP;
    private Point thisP;

    private JButton verifier;
    private JButton valider;
    private JButton recommencer;
    private monPanelDessin zoneDessin;
    private JPanel zoneBouton;
    private JPanel panelMain;
    private JTextField zoneTexte;
    private long temps;
    private Timer monTimer;
    private JLabel affTemps;
    private JLabel test;
    private LinkedList<Point> maListeP;
    private Courbe courbe;
    private Courbe courbeSent;
    private boolean verifierCourbe = false;
    private FenetrePlotCourbe fenetreC;


    public Canvas() {
        super("fenetre de dessin");

        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();


        thisP = new Point(0, 0);
        lastP = new Point(0, 0);
        maListeP = new LinkedList<>();

        zoneDessin = new monPanelDessin();
        zoneDessin.setBounds((int)(0.005*screensize.getWidth()), (int)(0.024*screensize.getHeight()), (int)(0.21*screensize.getWidth()), (int)(0.31*screensize.getHeight()));
        zoneDessin.setLayout(null);
        zoneDessin.setBackground(Color.white);
        zoneDessin.addMouseMotionListener(this);

        zoneBouton = new JPanel();
        zoneBouton.setBounds((int)(0.219*screensize.getWidth()), (int)(0.024*screensize.getHeight()), (int)(0.52*screensize.getWidth()), (int)(0.31*screensize.getHeight()));
        zoneBouton.setLayout(null);
        zoneBouton.setBackground(Color.gray);

        valider = new JButton("valider");
        valider.setBounds((int)(0.005*screensize.getWidth()), (int)(0.016*screensize.getHeight()), (int)(0.042*screensize.getWidth()), (int)(0.063*screensize.getHeight()));
        valider.setBackground(Color.green);
        valider.setForeground(Color.black);
        valider.addActionListener(this);
        zoneBouton.add(valider);

        verifier = new JButton("verifier");
        verifier.setBounds((int)(0.005*screensize.getWidth()), (int)(0.12*screensize.getHeight()), (int)(0.042*screensize.getWidth()), (int)(0.063*screensize.getHeight()));
        verifier.setBackground(Color.yellow);
        verifier.setForeground(Color.black);
        verifier.addActionListener(this);
        verifier.setEnabled(false);
        zoneBouton.add(verifier);

        recommencer = new JButton("recommencer");
        recommencer.setBounds((int)(0.005*screensize.getWidth()), (int)(0.219*screensize.getHeight()), (int)(0.042*screensize.getWidth()), (int)(0.063*screensize.getHeight()));
        recommencer.setBackground(Color.red);
        recommencer.setForeground(Color.black);
        recommencer.addActionListener(this);
        zoneBouton.add(recommencer);

        test = new JLabel("Test");
        test.setBounds((int)(0.005*screensize.getWidth()), (int)(0.008*screensize.getHeight()), (int)(0.042*screensize.getWidth()), (int)(0.008*screensize.getHeight()));
        zoneBouton.add(test);

        affTemps = new JLabel("temps = 0");
        affTemps.setForeground(Color.white);
        affTemps.setBounds((int)(0.005*screensize.getWidth()), (int)(0.28*screensize.getHeight()), (int)(0.063*screensize.getWidth()), (int)(0.047*screensize.getHeight()));
        zoneBouton.add(affTemps);

        panelMain = new JPanel();
        setContentPane(panelMain);
        panelMain.setLayout(null);
        panelMain.setBackground(new Color(140, 150, 200));
        panelMain.add(zoneDessin);
        panelMain.add(zoneBouton);

        monTimer = new Timer(100, this);
        monTimer.start();


        this.setSize((int)(0.28*screensize.getWidth()), (int)(0.4*screensize.getHeight()));
        this.setLocation(0,20);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    public Courbe getCurve(){
        return this.courbeSent;
    }

    public int[] getTaille() {
         int[] taille = {zoneDessin.getWidth(),zoneDessin.getHeight()};
        return taille;
    }


    public void mouseMoved(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        thisP.setCoords(e.getX(), e.getY());

        if ((thisP.distance(lastP) > 150)&&(e.getX()<410)&&(e.getY()<410)&&(e.getX()>-10)&&(e.getY()>-10)) {
            lastP.setCoords(thisP.getX(), thisP.getY());
            maListeP.add(new Point(e.getX(), e.getY()));
            this.courbe = new Courbe(maListeP, Color.blue);
            zoneDessin.update(courbe);
           verifier.setEnabled(true);
        }

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == monTimer) {
            temps += 100;
            affTemps.setText("temps = " + temps);
        }
        if (e.getSource() == verifier) {
            if (this.courbe.verifier()) {
                test.setText("GG WP");
                verifierCourbe = true;
            } else {
                test.setText("NOPE");
                verifierCourbe=false;
            }
        }
        if (e.getSource() == valider) {
            if (verifierCourbe) {
                test.setText("la courbe a ete validee");
                LinkedList<Point> liste=new LinkedList<Point>();
                for (int n=0;n<this.courbe.getList().size();n++){
                    liste.add(this.courbe.getList().get(n));
                }
                this.courbeSent=new Courbe(liste,Color.red);
                verifierCourbe=false;


            } else {
                test.setText("Veuillez valider votre courbe d'abord");
            }
        }
        if (e.getSource() == recommencer) {
            maListeP.clear();
        }
        repaint();

    }


}

