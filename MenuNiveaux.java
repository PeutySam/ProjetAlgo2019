import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;


public class MenuNiveaux extends JFrame implements ActionListener {

    // Les Widgets à déclarer en dehors du constructeur
    private JButton BoutonNiveau1;
    private JButton BoutonNiveau2;
    private JButton BoutonNiveau3;
    private JButton BoutonNiveau4;
    private JButton BoutonNiveau5;
    private JButton BoutonNiveau6;
    private JButton BoutonNiveau7;
    private JButton BoutonNiveau8;
    private JButton BoutonNiveau9;
    private JButton BoutonNiveau10;
    private JButton BoutonNiveau11;
    private JButton BoutonNiveau12;
    private JButton BoutonNiveau13;
    private JButton BoutonNiveau14;
    private JButton BoutonNiveau15;
    private JButton BoutonNiveau16;
    private JButton BoutonNiveau17;


    private JButton BoutonRandom;

    private JButton BoutonUnlock;





    public MenuNiveaux(int n) {
        this.setTitle("Menu Niveaux");
        this.setSize(500, 1200);
        this.setLocation(300, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        //Conteneur Principal
        JPanel monConteneur = new JPanel();
        monConteneur.setBounds(0, 0, 450, 600);

        monConteneur.setBackground(new Color(186, 186, 186));
        monConteneur.setLayout(null);

        JLabel etiquetteniveaux = new JLabel();
        etiquetteniveaux.setText("NIVEAUX");
        Font police = new Font("Bitstream Vera Sans Mono", Font.BOLD, 40);
        etiquetteniveaux.setFont(police);
        etiquetteniveaux.setSize(200, 30);
        etiquetteniveaux.setLocation(165, 50);
        monConteneur.add(etiquetteniveaux);


        //Bouton 1
        BoutonNiveau1 = new JButton("1");
        BoutonNiveau1.setSize(75, 75);
        BoutonNiveau1.setLocation(40, 150);
        BoutonNiveau1.setBackground(new Color(150, 255, 150));
        monConteneur.add(BoutonNiveau1);
        Font policeniv = new Font("Arial", Font.BOLD, 30);
        BoutonNiveau1.setFont(policeniv);

        //Bouton 2
        BoutonNiveau2 = new JButton("2");
        BoutonNiveau2.setSize(75, 75);
        BoutonNiveau2.setLocation(210, 150);
        monConteneur.add(BoutonNiveau2);
        if (n < 2) {
            BoutonNiveau2.setEnabled(false);
        }
        BoutonNiveau2.setFont(policeniv);

        //Bouton 3
        BoutonNiveau3 = new JButton("3");
        BoutonNiveau3.setSize(75, 75);
        BoutonNiveau3.setLocation(380, 150);
        monConteneur.add(BoutonNiveau3);
        if (n < 3) {
            BoutonNiveau3.setEnabled(false);
        }
        BoutonNiveau3.setFont(policeniv);

        //Bouton 4
        BoutonNiveau4 = new JButton("4");
        BoutonNiveau4.setSize(75, 75);
        BoutonNiveau4.setLocation(40, 250);
        monConteneur.add(BoutonNiveau4);
        if (n < 4) {
            BoutonNiveau4.setEnabled(false);
        }
        BoutonNiveau4.setFont(policeniv);

        //Bouton 5
        BoutonNiveau5 = new JButton("5");
        BoutonNiveau5.setSize(75, 75);
        BoutonNiveau5.setLocation(210, 250);
        monConteneur.add(BoutonNiveau5);
        if (n < 5) {
            BoutonNiveau5.setEnabled(false);
        }
        BoutonNiveau5.setFont(policeniv);

        //Bouton 6
        BoutonNiveau6 = new JButton("6");
        BoutonNiveau6.setSize(75, 75);
        BoutonNiveau6.setLocation(380, 250);
        monConteneur.add(BoutonNiveau6);
        if (n < 6) {
            BoutonNiveau6.setEnabled(false);
        }
        BoutonNiveau6.setFont(policeniv);

        BoutonNiveau7 = new JButton("7");
        BoutonNiveau7.setSize(75, 75);
        BoutonNiveau7.setLocation(40, 350);
        monConteneur.add(BoutonNiveau7);
        if (n < 7) {
            BoutonNiveau7.setEnabled(false);
        }
        BoutonNiveau7.setFont(policeniv);

        BoutonNiveau8 = new JButton("8");
        BoutonNiveau8.setSize(75, 75);
        BoutonNiveau8.setLocation(210, 350);
        monConteneur.add(BoutonNiveau8);
        if (n < 8) {
            BoutonNiveau8.setEnabled(false);
        }
        BoutonNiveau8.setFont(policeniv);

        BoutonNiveau9 = new JButton("9");
        BoutonNiveau9.setSize(75, 75);
        BoutonNiveau9.setLocation(380, 350);
        monConteneur.add(BoutonNiveau9);
        if (n < 9) {
            BoutonNiveau9.setEnabled(false);
        }
        BoutonNiveau9.setFont(policeniv);

        BoutonNiveau10 = new JButton("10");
        BoutonNiveau10.setSize(75, 75);
        BoutonNiveau10.setLocation(40, 450);
        monConteneur.add(BoutonNiveau10);
        if (n < 10) {
            BoutonNiveau10.setEnabled(false);
        }
        BoutonNiveau10.setFont(policeniv);

        BoutonNiveau11 = new JButton("11");
        BoutonNiveau11.setSize(75, 75);
        BoutonNiveau11.setLocation(210, 450);
        monConteneur.add(BoutonNiveau11);
        if (n < 11) {
            BoutonNiveau11.setEnabled(false);
        }
        BoutonNiveau11.setFont(policeniv);

        BoutonNiveau12 = new JButton("12");
        BoutonNiveau12.setSize(75, 75);
        BoutonNiveau12.setLocation(380, 450);
        monConteneur.add(BoutonNiveau12);
        if (n < 12) {
            BoutonNiveau12.setEnabled(false);
        }
        BoutonNiveau12.setFont(policeniv);

        BoutonNiveau13 = new JButton("13");
        BoutonNiveau13.setSize(75, 75);
        BoutonNiveau13.setLocation(40, 550);
        monConteneur.add(BoutonNiveau13);
        if (n < 13) {
            BoutonNiveau13.setEnabled(false);
        }
        BoutonNiveau13.setFont(policeniv);

        BoutonNiveau14 = new JButton("14");
        BoutonNiveau14.setSize(75, 75);
        BoutonNiveau14.setLocation(210, 550);
        if (n < 14) {
            BoutonNiveau14.setEnabled(false);
        }
        monConteneur.add(BoutonNiveau14);
        BoutonNiveau14.setFont(policeniv);

        BoutonNiveau15 = new JButton("15");
        BoutonNiveau15.setSize(75, 75);
        BoutonNiveau15.setLocation(380, 550);
        if (n < 15) {
            BoutonNiveau15.setEnabled(false);
        }
        monConteneur.add(BoutonNiveau15);
        BoutonNiveau15.setFont(policeniv);

        BoutonNiveau16 = new JButton("16");
        BoutonNiveau16.setSize(75, 75);
        BoutonNiveau16.setLocation(40, 650);
        if (n < 16) {
            BoutonNiveau16.setEnabled(false);
        }
        monConteneur.add(BoutonNiveau16);
        BoutonNiveau16.setFont(policeniv);

        BoutonNiveau17 = new JButton("17");
        BoutonNiveau17.setSize(75, 75);
        BoutonNiveau17.setLocation(210, 650);
        monConteneur.add(BoutonNiveau17);
        if (n < 17) {
            BoutonNiveau17.setEnabled(false);
        }
        BoutonNiveau17.setFont(policeniv);

        //Bouton retour
        BoutonRandom = new JButton();
        BoutonRandom.setSize(250, 75);
        BoutonRandom.setLocation(20, 750);
        BoutonRandom.setBackground(new Color(118, 110, 100));
        monConteneur.add(BoutonRandom);


        BoutonRandom.setFont(new Font("Arial", Font.BOLD, 20));
        BoutonRandom.setText("Aléatoire");

        BoutonUnlock = new JButton("Unlock");
        BoutonUnlock.setSize(150, 75);
        BoutonUnlock.setLocation(300, 750);
        BoutonUnlock.setBackground(new Color(118, 110, 100));
        BoutonUnlock.setFont(new Font("Arial", Font.BOLD, 20));
        monConteneur.add(BoutonUnlock);
        BoutonUnlock.addActionListener(this);


        this.setContentPane(monConteneur);


        //Actions
        BoutonNiveau1.addActionListener(this);
        BoutonNiveau2.addActionListener(this);
        BoutonNiveau3.addActionListener(this);
        BoutonNiveau4.addActionListener(this);
        BoutonNiveau5.addActionListener(this);
        BoutonNiveau6.addActionListener(this);
        BoutonNiveau7.addActionListener(this);
        BoutonNiveau8.addActionListener(this);
        BoutonNiveau9.addActionListener(this);
        BoutonNiveau10.addActionListener(this);
        BoutonNiveau11.addActionListener(this);
        BoutonNiveau12.addActionListener(this);
        BoutonNiveau13.addActionListener(this);
        BoutonNiveau14.addActionListener(this);
        BoutonNiveau15.addActionListener(this);
        BoutonNiveau16.addActionListener(this);
        BoutonNiveau17.addActionListener(this);
        BoutonRandom.addActionListener(this);


        // Pour rendre la fenêtre visible
        this.setVisible(true);

    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BoutonNiveau1) {
            this.dispose();
            new FenetrePlotCourbe(1);
        }
        if (e.getSource() == BoutonNiveau2) {
            this.dispose();
            new FenetrePlotCourbe(2);
        }
        if (e.getSource() == BoutonNiveau3) {
            this.dispose();
            new FenetrePlotCourbe(3);
        }
        if (e.getSource() == BoutonNiveau4) {
            this.dispose();
            new FenetrePlotCourbe(4);
        }
        if (e.getSource() == BoutonNiveau5) {
            this.dispose();
            new FenetrePlotCourbe(5);
        }
        if (e.getSource() == BoutonNiveau6) {
            this.dispose();
            new FenetrePlotCourbe(6);
        }

        if (e.getSource() == BoutonNiveau7) {
            this.dispose();
            new FenetrePlotCourbe(7);
        }
        if (e.getSource() == BoutonNiveau8) {
            this.dispose();
            new FenetrePlotCourbe(8);
        }
        if (e.getSource() == BoutonNiveau9) {
            this.dispose();
            new FenetrePlotCourbe(9);
        }
        if (e.getSource() == BoutonNiveau10) {
            this.dispose();
            new FenetrePlotCourbe(10);
        }
        if (e.getSource() == BoutonNiveau11) {
            this.dispose();
            new FenetrePlotCourbe(11);
        }
        if (e.getSource() == BoutonNiveau12) {
            this.dispose();
            new FenetrePlotCourbe(12);
        }
        if (e.getSource() == BoutonNiveau13) {
            this.dispose();
            new FenetrePlotCourbe(13);
        }
        if (e.getSource() == BoutonNiveau14) {
            this.dispose();
            new FenetrePlotCourbe(14);
        }
        if (e.getSource() == BoutonNiveau15) {
            this.dispose();
            new FenetrePlotCourbe(15);
        }
        if (e.getSource() == BoutonNiveau16) {
            this.dispose();
            new FenetrePlotCourbe(16);
        }
        if (e.getSource() == BoutonNiveau17) {
            this.dispose();
            new FenetrePlotCourbe(17);
        }
        if (e.getSource() == BoutonUnlock) {
            BoutonNiveau2.setEnabled(true);
            BoutonNiveau3.setEnabled(true);
            BoutonNiveau4.setEnabled(true);
            BoutonNiveau5.setEnabled(true);
            BoutonNiveau6.setEnabled(true);
            BoutonNiveau7.setEnabled(true);
            BoutonNiveau8.setEnabled(true);
            BoutonNiveau9.setEnabled(true);
            BoutonNiveau10.setEnabled(true);
            BoutonNiveau11.setEnabled(true);
            BoutonNiveau12.setEnabled(true);
            BoutonNiveau13.setEnabled(true);
            BoutonNiveau14.setEnabled(true);
            BoutonNiveau15.setEnabled(true);
            BoutonNiveau16.setEnabled(true);
            BoutonNiveau17.setEnabled(true);
        }if (e.getSource() == BoutonRandom) {
            this.dispose();
            new FenetrePlotCourbe((int)(Math.random()*17+1));



        }


    }
}

		

		
		

		
		
		
