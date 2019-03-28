/**
 * La fenêtre principale pour sélectionner les courbes à étudier
 * Placement des widgets
 * Interaction avec le bouton
 * Utilisation d'un JTextArea
 * Utilisation de 2 boutons
 */

// Chargement des bibliothèques Swing et AWT

import com.sun.crypto.provider.JceKeyStore;
import javafx.scene.transform.TransformChangedEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Parametres extends JFrame implements ActionListener, ChangeListener {

    // Les Widgets à déclarer en dehors du constructeur
    private JComboBox courbeList;
    private JTextArea affHistorique;
    private JButton monBoutonAfficher;
    private JButton monBoutonEffacer;
    private JTextField textChoix;
    private double coeff;
    private double gravite;
    private double poids;
    private JSlider sliderGravite;
    private JSlider sliderPoids;


    public Parametres() {
        this.setTitle("Parametrage");
        this.setSize(400, 450);
        // Pour placer la fenêtre au centre de l'écran
        //.....this.setLocationRelativeTo(null);
        this.setLocation(50, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.gravite=50;
        this.coeff=0;
        this.poids=50;

        //JComboBox


        JPanel monConteneur = new JPanel();
        setContentPane(monConteneur);
        monConteneur.setBackground(new Color(140, 150, 200));
        monConteneur.setLayout(null);

        JLabel etiquettePoids = new JLabel();
        etiquettePoids.setText("Poids");
        etiquettePoids.setSize(50, 20);
        etiquettePoids.setLocation(20, 50);
        monConteneur.add(etiquettePoids);

        JLabel etiquetteGravite = new JLabel();
        etiquetteGravite.setText("Cste Gravitationelle");
        etiquetteGravite.setSize(150, 20);
        etiquetteGravite.setLocation(20, 150);
        monConteneur.add(etiquetteGravite);

        JLabel etiquetteMatiere = new JLabel();
        etiquetteMatiere.setText("Matiere");
        etiquetteMatiere.setSize(50, 20);
        etiquetteMatiere.setLocation(20, 250);
        monConteneur.add(etiquetteMatiere);


        sliderPoids = new JSlider(20, 100, 50);
        sliderPoids.setSize(200, 50);
        sliderPoids.setLocation(150, 50);
        sliderPoids.setPaintTrack(true);
        sliderPoids.setPaintTicks(true);
        sliderPoids.setPaintLabels(true);
        sliderPoids.setMajorTickSpacing(20);
        sliderPoids.setMinorTickSpacing(5);
        sliderPoids.addChangeListener(this);
        monConteneur.add(sliderPoids);

        sliderGravite = new JSlider(0, 100, 50);
        sliderGravite.setSize(200, 50);
        sliderGravite.setLocation(150, 150);
        sliderGravite.setPaintTrack(true);
        sliderGravite.setPaintTicks(true);
        sliderGravite.setPaintLabels(true);
        sliderGravite.setMajorTickSpacing(25);
        sliderGravite.setMinorTickSpacing(5);
        sliderGravite.addChangeListener(this);
        monConteneur.add(sliderGravite);

        JComboBox<String> boxMatiere = new JComboBox<String>();
        boxMatiere.setSize(200, 50);
        boxMatiere.setLocation(150, 250);
        boxMatiere.addItem("Glace");
        boxMatiere.addItem("Acier");
        boxMatiere.addItem("Bois");
        boxMatiere.addItem("Aluminium");
        boxMatiere.addItem("Caoutchouc");
        boxMatiere.addActionListener(this);
        monConteneur.add(boxMatiere);

        this.setVisible(true);

    }


    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        String materiaux = (String) cb.getSelectedItem();
        switch (materiaux) {
            case "Aluminium":
                coeff = 9;
                break;
            case "Acier":
                coeff = 5;
                break;
            case "Bois":
                coeff = 2;
                break;
            case "Glace":
                coeff = 0;
                break;
            case "Caoutchouc":
                coeff = 3.5;
                break;

        }
        System.out.println(coeff);
    }
    public double getPoids(){
        return this.poids;
    }
    public double getGravite(){
        return this.gravite;
    }
    public double getCoeff(){
        return this.coeff;
    }


	public void stateChanged(ChangeEvent e ){
        gravite=sliderGravite.getValue();
        poids=sliderPoids.getValue();
        System.out.println(poids+"     "+gravite);
    }
}

		

		
		

		
		
		
