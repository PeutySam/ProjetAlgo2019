import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics;

public class Courbe {
	
	private LinkedList<Point> listePoint;
	private Color maCouleur;
	
	
	public Courbe(LinkedList<Point> liste, Color c){
		
		this.maCouleur=c;
		this.listePoint=liste;
	}
	
	public void dessine (Graphics g) {
		g.setColor (maCouleur);
		for (int i=1 ; i<listePoint.size();i++){
			g.drawLine((int)listePoint.get(i-1).getX(), (int)listePoint.get(i-1).getY(), (int)listePoint.get(i).getX(), (int)listePoint.get(i).getY());
		}
	}
	
	public boolean verifier () {
		Point lastCoeff=new Point(1,0);
		Point currentCoeff=new Point(1,0);
		//Point origine=new Point(0,0);
		int compteur=0;
		/*double lastCoeff=0;
		double currentCoeff=0;*/
		for (int i=2 ; i<listePoint.size(); i++){
			lastCoeff.setY((listePoint.get(i-1).getY()-listePoint.get(i-2).getY())/(listePoint.get(i-1).getX()-listePoint.get(i-2).getX()));
			currentCoeff.setY((listePoint.get(i).getY()-listePoint.get(i-1).getY())/(listePoint.get(i).getX()-listePoint.get(i-1).getX()));
			//System.out.println("angle = "+Math.acos((1+lastCoeff.getY()*currentCoeff.getY())/(Math.sqrt(lastCoeff.distance(origine)*currentCoeff.distance(origine))))*(360/(2*Math.PI)));
			//if(Math.acos((1+lastCoeff.getY()*currentCoeff.getY())/(Math.sqrt(lastCoeff.distance(origine)*currentCoeff.distance(origine))))<Math.PI/2){
			//lastCoeff=(listePoint.get(i-1).getY()-listePoint.get(i-2).getX())/((listePoint.get(i-1).getX()-listePoint.get(i-2).getX()));
			//currentCoeff=(listePoint.get(i).getY()-listePoint.get(i-1).getX())/(listePoint.get(i).getX()-listePoint.get(i-1).getX());
			//System.out.println("lastCoeff = "+lastCoeff);
			//System.out.println("currentCoeff = "+currentCoeff);
			
			System.out.println("lastCoeff X= "+lastCoeff.getX()+" Y= "+lastCoeff.getY());
			System.out.println("currentCoeff X= "+currentCoeff.getX()+" Y= "+currentCoeff.getY());
			
			System.out.println("angle1= "+Math.atan2(lastCoeff.getY(),lastCoeff.getX())*(360/(2*Math.PI)));
			System.out.println("angle2= "+Math.atan2(currentCoeff.getY(),currentCoeff.getX())*(360/(2*Math.PI)));
			
			System.out.println("angle = "+(Math.atan2(currentCoeff.getY(),currentCoeff.getX())-Math.atan2(lastCoeff.getY(),lastCoeff.getX()))*(360/(2*Math.PI)));
			if(Math.abs(Math.atan2(currentCoeff.getY(),currentCoeff.getX())-Math.atan2(lastCoeff.getY(),lastCoeff.getX()))<Math.PI/2){
				compteur++;
			}
		}
		System.out.println("compteur = "+ compteur);
		if(compteur==listePoint.size()-2){
			return true;
		}else{
			return false;
		}
	}
			
}

