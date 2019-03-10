

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList; 

public class monPanelDessin extends JPanel {
        
    private LinkedList<Courbe> mesCourbes;
    private Color maCouleur;
    private boolean click;
         
	public monPanelDessin(){
		mesCourbes = new LinkedList<Courbe>();
	}

	public void update(LinkedList<Courbe> maListeCourbes){
		mesCourbes = maListeCourbes;
		// Couleur initiale
		maCouleur = new Color( 200,200,200);
		
	}

	public void paint(Graphics g){
		// On dessine le fond
		g.setColor(maCouleur);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		// On dessine toutes les courbes du tableau si la liste n'est pas vide
		if (!mesCourbes.isEmpty()) {
			for (int i = 0;i<mesCourbes.size();i++){
				mesCourbes.get(i).dessine(g);
			}
		}
	}
	
	
	

}
