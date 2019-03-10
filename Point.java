public class Point {
 
	private double x;
	private double y;
 
 
	public Point(double x, double y){
		this.x=x;
		this.y=y;
	}
 
	public double getX(){
		return this.x;
	}
 
	public double getY(){
		return this.y;
	}
	public void setX(double x){
		this.x=x;
	}
	public void setY(double y){
		this.y=y;
	}
	public void setCoords(double x, double y){
		this.x=x;
		this.y=y;
	}
	public double distance(Point p){
		return Math.pow(this.x-p.getX() ,2)+Math.pow(this.y-p.getY() ,2);
	}
	
}
