


public class Vehicule {
	
	private Point pos;
	private Point vit;
	private Point acc;
	private double poids;
	
	public Vehicule (double m) {
		this.poids=m;
	}
	
	public Point getPos (){
		return this.pos;
	}
	
	public Point getVit(){
		return this.vit;
	}
	
	public Point fetAcc (){
		return this.acc;
	}
	
	public double getPoids(){
		return this.poids;
	}
	
	public double getInclinaison() {
		return Math.atan(this.vit.getY()/this.vit.getX());
	}
	
	public void Avance(){
		this.pos.setX(this.pos.getX()+this.vit.getX());
		this.pos.setY(this.pos.getY()+this.vit.getY());
	}
	
	public void Accelere(){
		this.vit.setX(this.vit.getX()+this.acc.getX());
		this.vit.setY(this.vit.getY()+this.acc.getY());
	}
}

