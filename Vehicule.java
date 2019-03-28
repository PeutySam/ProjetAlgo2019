import java.awt.*;

public class Vehicule {

    private Point pos; //Coordonnées du Véhicule
    private Point vit; //Vitesse du Véhicule
    private Point acc; //Accélération du Véhicule
    private double poids; //Poids du Véhicule
    private Color maCouleur; //Couleur du Véhicule

    //Constructeur du Véhicule
    public Vehicule(double m,Color c) {
        maCouleur=c;
        this.poids = m;
        this.pos=new Point(50,50);
        this.vit=new Point(0,0); //pas de vitesse initiale
        this.acc=new Point(0,0);

    }
    //Accesseur
    public Point getPos() {
        return this.pos;
    }

    public Point getVit() {
        return this.vit;
    }

    public Point getAcc() {
        return this.acc;
    }

    public double getPoids() {
        return this.poids;
    }

    public double getInclinaison() {
        return Math.atan(this.vit.getY() / this.vit.getX());
    }

    public void Avance() {
        this.pos.setX(this.pos.getX() + this.vit.getX());
        this.pos.setY(this.pos.getY() + this.vit.getY());
    }

    public void Accelere() {
        this.vit.setX(this.vit.getX() + this.acc.getX());
        this.vit.setY(this.vit.getY() + this.acc.getY());
    }
    public void setAcc(double x, double y){
        this.acc.setX(x);
        this.acc.setY(y);

    }
    public void setPos(double x,double y) {
        this.pos.setX(x);
        this.pos.setY(y);
    }

    public void setVit(double x,double y) {
        this.vit.setX(x);
        this.vit.setY(y);
    }

    public void setPoids(double m){
        this.poids=m;

    }
    public void dessine (Graphics g){
       g.setColor(maCouleur);
       g.fillOval((int)this.pos.getX()-10,(int)this.pos.getY()-10,10,10);

    }
    public double normeVit(){
        return Math.sqrt(Math.pow(this.getVit().getX(),2)+Math.pow(this.getVit().getY(),2));
    }
}

