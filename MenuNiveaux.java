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
    private JButton BoutonRandom;
	private JButton BoutonRetour;
	private JButton BoutonUnlock;
	public int victoires=0;

	private JPanel monConteneur;

		
	public MenuNiveaux(){
		this.setTitle("Menu Niveaux");
		this.setSize(500,1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	
      //Conteneur Principal
        monConteneur = new JPanel() ;
        setContentPane(monConteneur);
        monConteneur.setBackground(new Color(186,186,186));
        monConteneur.setLayout(null);
        
        JLabel etiquetteniveaux = new JLabel();
        etiquetteniveaux.setText("NIVEAUX");
        Font police= new Font("Bitstream Vera Sans Mono",Font.BOLD, 40);
        etiquetteniveaux.setFont(police);
        etiquetteniveaux.setSize(200,30);
        etiquetteniveaux.setLocation(165,50);
        monConteneur.add(etiquetteniveaux) ;
        
      //Bouton 1
        BoutonNiveau1 = new JButton("1");
        BoutonNiveau1.setSize(75,75);
        BoutonNiveau1.setLocation(40,150);
        BoutonNiveau1.setBackground(new Color(150,255,150));
        monConteneur.add(BoutonNiveau1) ;
        
        Font policeniv= new Font("Arial",Font.BOLD, 30);
        BoutonNiveau1.setFont(policeniv);

      
        
	  //Bouton 2
        BoutonNiveau2 = new JButton("2");
        BoutonNiveau2.setSize(75,75);
        BoutonNiveau2.setLocation(210,150);
        monConteneur.add(BoutonNiveau2) ;
        BoutonNiveau2.setEnabled(false);
        BoutonNiveau2.setFont(policeniv);

        
        
        //Bouton 3
        BoutonNiveau3 = new JButton("3");
        BoutonNiveau3.setSize(75,75);
        BoutonNiveau3.setLocation(380,150);
        monConteneur.add(BoutonNiveau3) ;
        BoutonNiveau3.setEnabled(false);
        BoutonNiveau3.setFont(policeniv);

        
        //Bouton 4
        BoutonNiveau4 = new JButton("4");
        BoutonNiveau4.setSize(75,75);
        BoutonNiveau4.setLocation(40,300);
        monConteneur.add(BoutonNiveau4) ;
        BoutonNiveau4.setEnabled(false);
        BoutonNiveau4.setFont(policeniv);

        
        //Bouton 5
        BoutonNiveau5 = new JButton("5");
        BoutonNiveau5.setSize(75,75);
        BoutonNiveau5.setLocation(210,300);
        monConteneur.add(BoutonNiveau5) ;
        BoutonNiveau5.setEnabled(false);
        BoutonNiveau5.setFont(policeniv);

        
        //Bouton 6
        BoutonNiveau6 = new JButton("6");
        BoutonNiveau6.setSize(75,75);
        BoutonNiveau6.setLocation(380,300);
        monConteneur.add(BoutonNiveau6) ;
        BoutonNiveau6.setEnabled(false);
        BoutonNiveau6.setFont(policeniv);


        BoutonNiveau7 = new JButton("7");
        BoutonNiveau7.setSize(75,75);
        BoutonNiveau7.setLocation(40,450);
        monConteneur.add(BoutonNiveau7) ;
        BoutonNiveau7.setEnabled(false);
        BoutonNiveau7.setFont(policeniv);

        BoutonNiveau8 = new JButton("8");
        BoutonNiveau8.setSize(75,75);
        BoutonNiveau8.setLocation(210,450);
        monConteneur.add(BoutonNiveau8) ;
        BoutonNiveau8.setEnabled(false);
        BoutonNiveau8.setFont(policeniv);


        BoutonNiveau9 = new JButton("9");
        BoutonNiveau9.setSize(75,75);
        BoutonNiveau9.setLocation(380,450);
        monConteneur.add(BoutonNiveau9) ;
        BoutonNiveau9.setEnabled(false);
        BoutonNiveau9.setFont(policeniv);


        BoutonNiveau10 = new JButton("10");
        BoutonNiveau10.setSize(75,75);
        BoutonNiveau10.setLocation(40,600);
        monConteneur.add(BoutonNiveau10) ;
        BoutonNiveau10.setEnabled(false);
        BoutonNiveau10.setFont(policeniv);

        //Bouton retour
        BoutonRetour = new JButton();
        BoutonRetour.setSize(250,75);
        BoutonRetour.setLocation(20,850);
        BoutonRetour.setBackground(new Color(118,110,100));
        monConteneur.add(BoutonRetour) ;
        
        Font policeretour= new Font("Arial",Font.BOLD, 20);
        BoutonRetour.setFont(policeretour);
        BoutonRetour.setText("Retour Menu Principal");

        BoutonUnlock=new JButton("Unlock");
        BoutonUnlock.setSize(150,75);
        BoutonUnlock.setLocation(300,850);
        BoutonUnlock.setBackground(new Color(118,110,100));
        BoutonUnlock.setFont(policeretour);
        monConteneur.add(BoutonUnlock) ;
        BoutonUnlock.addActionListener(this);


        
        
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
        BoutonRetour.addActionListener(this);
		


		
		// Pour rendre la fenêtre visible
		this.setVisible(true);
		
	}
	
	public void actionPerformed (ActionEvent e){ 
		if (e.getSource()==BoutonNiveau1){
            this.dispose();
            new FenetrePlotCourbe(1);
		}if (e.getSource()==BoutonNiveau2){
            this.dispose();
            new FenetrePlotCourbe(2);
		}if (e.getSource()==BoutonNiveau3){
            this.dispose();
            new FenetrePlotCourbe(3);
		}if (e.getSource()==BoutonNiveau4){
            this.dispose();
            new FenetrePlotCourbe(4);
		}if (e.getSource()==BoutonNiveau5){
            this.dispose();
            new FenetrePlotCourbe(5);
		}if (e.getSource()==BoutonNiveau6){
            this.dispose();
            new FenetrePlotCourbe(6);
		}if (e.getSource()==BoutonRetour){
		    this.dispose();
		    new Menu();
		}if(e.getSource()==BoutonNiveau7) {
            this.dispose();
            new FenetrePlotCourbe(7);
        }if(e.getSource()==BoutonNiveau8) {
            this.dispose();
            new FenetrePlotCourbe(8);
        }if(e.getSource()==BoutonNiveau9) {
            this.dispose();
            new FenetrePlotCourbe(9);
        }if(e.getSource()==BoutonNiveau10){
            this.dispose();
            new FenetrePlotCourbe(10);
        }if(e.getSource()==BoutonUnlock){
		    BoutonNiveau2.setEnabled(true);
		    BoutonNiveau3.setEnabled(true);
		    BoutonNiveau4.setEnabled(true);
		    BoutonNiveau5.setEnabled(true);
		    BoutonNiveau6.setEnabled(true);
		    BoutonNiveau7.setEnabled(true);
		    BoutonNiveau8.setEnabled(true);
            BoutonNiveau9.setEnabled(true);
            BoutonNiveau10.setEnabled(true);
        }

		
		
	}
}

		

		
		

		
		
		
