import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;


public class Canvas extends JFrame implements MouseMotionListener,ActionListener{
	long lastTime;
	long thisTime;
	Point lastP;
	Point thisP;
	
	private JButton verifier;
	private JButton valider;
	private JButton recommencer;
	private monPanelDessin zoneDessin ;
	private JPanel zoneBouton;
	private JPanel panelMain;
	private JTextField zoneTexte;
	private long temps;
	private Timer monTimer;
	private JLabel affTemps;
	private JLabel test;
	private LinkedList<Courbe> maListeC;
	private LinkedList<Point> maListeP;
	private Courbe courbe;
	boolean verifierCourbe=false;
	
	public Canvas () {
		super ("fenetre de dessin");
		
		lastTime=0;
		thisTime=0;
		thisP=new Point (0,0);
		lastP=new Point (0,0);
		maListeC=new LinkedList<Courbe>();
		maListeP=new LinkedList<Point>();
		
		zoneDessin = new monPanelDessin();
		zoneDessin.setBounds( 10 , 10 , 400 , 400);
		zoneDessin.setLayout(null);
		zoneDessin.setBackground(Color.white);
		zoneDessin.addMouseMotionListener(this);
		
		zoneBouton = new JPanel ();
		zoneBouton.setBounds(420,10,100,400);
		zoneBouton.setLayout(null);
		zoneBouton.setBackground(Color.gray);
		
		valider = new JButton("valider");
		valider.setBounds(10,20,80,80);
		valider.setBackground(Color.green);
		valider.setForeground(new Color(10,144,10));
		valider.addActionListener(this);
		zoneBouton.add(valider);
		
		verifier = new JButton("verifier");
		verifier.setBounds(10,150,80,80);
		verifier.setBackground(Color.yellow);
		verifier.setForeground(new Color(10,144,10));
		verifier.addActionListener(this);
		zoneBouton.add(verifier);
		
		recommencer = new JButton("recommencer");
		recommencer.setBounds(10,280,80,80);
		recommencer.setBackground(Color.red);
		recommencer.setForeground(new Color(10,144,10));
		recommencer.addActionListener(this);
		zoneBouton.add(recommencer);
		
		test= new JLabel("Test");
		test.setBounds(10,10,80,10);
		zoneBouton.add(test);
		
		affTemps = new JLabel("temps = 0");
		affTemps.setForeground(Color.white);
		affTemps.setBounds(10,360,120,60);
		zoneBouton.add(affTemps);
		
		panelMain=new JPanel();
		panelMain.setBounds(0,0,540,430);
		panelMain.setLayout(null);
		panelMain.setBackground(new Color (150,120,160));
		panelMain.add(zoneDessin);
		panelMain.add(zoneBouton);
		
		monTimer = new Timer(100,this);
		monTimer.start();
		
		this.add(panelMain);
		this.setSize(540,460);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
		
	public void mouseDragged(MouseEvent e) {
		//thisTime=System.currentTimeMillis();
		thisP.setCoords(e.getX(),e.getY());
		//if(thisTime>lastTime+100){
		
		//System.out.println(thisP.distance(lastP));
		if(thisP.distance(lastP)>150)/*&&(thisTime>lastTime+100))*/{
			//lastTime=thisTime;
			lastP.setCoords(thisP.getX(),thisP.getY());
			//System.out.println("X= "+e.getX()+" Y= "+e.getY());
			maListeP.add(new Point(e.getX(),e.getY()));
			this.courbe=new Courbe(maListeP,Color.blue);
			maListeC.add(courbe);
			zoneDessin.update(maListeC);	
			//}
		}	
	}
	
	
	public Courbe getCourbe(){
		return this.courbe;
	}
	
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==monTimer){
			temps+=100;
			affTemps.setText("temps = "+temps);
		}
		if (e.getSource()==verifier){
			if(this.courbe.verifier()){
				test.setText("GG WP");
				verifierCourbe=true;
			}else{
				test.setText("NOPE");
			}
		}
		if (e.getSource()==valider){
			if(verifierCourbe){
				test.setText("la courbe a ete validee");
			}else{
				test.setText("Veuillez valider votre courbe d'abord");
			}
		}
		if (e.getSource()==recommencer){
			maListeP.clear();
		}
		repaint();
		
	}
	
	
	
	
}

