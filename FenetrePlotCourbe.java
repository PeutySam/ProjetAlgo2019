import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.awt.event.*;

public class FenetrePlotCourbe extends JFrame implements ActionListener{

	private LinkedList<Courbe> maListe;
	private JButton monBoutonGo;
	private monPanelDessin panneauDessin;
	
	public FenetrePlotCourbe(){
		maListe = new LinkedList<Courbe>();
		this.setTitle("IHM Courbe - Graphisme ");
		this.setLayout(null);
		this.setSize(900,900);
		this.setLocation(500,200);
		
		this.setResizable(false);
		this.setVisible(true);
		
		monBoutonGo = new JButton("Start");
		monBoutonGo.setBounds(10,10,150,60);
		monBoutonGo.addActionListener(this);
		
		
		//Création du test
		LinkedList<Point> listeP = new LinkedList();
		listeP.add(new Point(50,50));
		listeP.add(new Point(60,150));
		listeP.add(new Point(70,250));
		listeP.add(new Point(80,350));
		listeP.add(new Point(140,450));
		listeP.add(new Point(200,550));
		listeP.add(new Point(250,500));
		listeP.add(new Point(300,445));
		listeP.add(new Point(350,425));
		listeP.add(new Point(400,360));
		Courbe C = new Courbe(listeP,Color.red);
		LinkedList<Courbe> listeC= new LinkedList();
		listeC.add(C);
		
		panneauDessin = new monPanelDessin();
		panneauDessin.setBounds(10,120,800,800);
		panneauDessin.setLayout(null);
		panneauDessin.setBackground(Color.cyan);
		panneauDessin.update(listeC);
		
		JPanel panneauGlobal = new JPanel();
		panneauGlobal.setBounds(0,0,this.getWidth(),this.getHeight());
		panneauGlobal.setLayout(null);
		panneauGlobal.setBackground(new Color(55,145,189));
		panneauGlobal.add(monBoutonGo);
		panneauGlobal.add(panneauDessin);
		
		this.add(panneauGlobal);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
			
		repaint();
	}
	
	
}
