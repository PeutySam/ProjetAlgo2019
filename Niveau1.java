import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;


public class Niveau1 extends JFrame implements ActionListener {
	
	// Les Widgets à déclarer en dehors du constructeur
    BufferedImage b1=null;
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int height = (int)dimension.getHeight();
    int width  = (int)dimension.getWidth();
    monPanelDessin panneauDessin=new monPanelDessin();
    
    JButton BoutonRetour;
   // private MenuNiveaux fen;
		
	public Niveau1(){


        try{
            b1 = ImageIO.read(new File("background.jpg"));

        } catch(IOException ex){
            System.out.println(ex);
        }
        double taillehauteur = height*0.74;
        double taillelargeur = width*0.74;
        int taillehaut=(int)taillehauteur;
        int taillelarg=(int)taillelargeur;
        
        double positionx = width*0.25;
        double positiony = height*0.1;
        int positx=(int)positionx;
        int posity=(int)positiony;
        
		this.setTitle("Niveau 1");
		this.setSize(taillelarg,taillehaut);
		this.setLocation(positx,posity);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
      //Conteneur Principal
        JPanel monConteneur = new JPanel() ;
        setContentPane(monConteneur);
        monConteneur.setBackground(new Color(186,186,186));
        monConteneur.setLayout(null);
        
      //Frame de jeu
        double hauteurgamepanel = taillehaut*0.7;
        int hautgamepanel=(int)hauteurgamepanel;

		panneauDessin.setBounds(0,0,taillelarg,hautgamepanel);
		this.add(panneauDessin);
		JPanel gamepanel = new JPanel();
        
      

   

        
      gamepanel.setSize(taillelarg,hautgamepanel);
      gamepanel.setLocation(0,50);
      gamepanel.setLayout(null);

      monConteneur.add(gamepanel);
      
     
      

      //Objets/Obstacles
    
      

      
        
      
      
        
        //Bouton retour
        JButton BoutonRetour = new JButton();
        
        double xboutonRetour = taillelarg*0.1;
        double yboutonRetour = taillehauteur*0.8;
        int ybout=(int)yboutonRetour;
        int xbout=(int)xboutonRetour;
        
        BoutonRetour.setSize(250,75);
        BoutonRetour.setLocation(xbout,ybout);
        BoutonRetour.setBackground(new Color(118,110,100));
        monConteneur.add(BoutonRetour) ;
        
        Font policeretour= new Font("Arial",Font.BOLD, 20);
        BoutonRetour.setFont(policeretour);
        BoutonRetour.setText("Retour Menu Principal");

        
        //Actions
       BoutonRetour.addActionListener(this);
      
		

		this.setVisible(true);
		
	}
	
	public void actionPerformed (ActionEvent e){ 
        if(e.getSource()==BoutonRetour) {
            this.setVisible(false);

            //this.dispose();
            // fen=new MenuNiveaux();
        }
		
	}
    public void dessine(Graphics g) {
	    g.drawImage(b1,0,0,null);
    }
	
}

		

		
		

		
