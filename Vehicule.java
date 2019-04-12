import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;



public class Vehicule {

    private Point pos; //Coordonnées du Véhicule
    private Point vit; //Vitesse du Véhicule
    private Point acc; //Accélération du Véhicule
    private double poids; //Poids du Véhicule
    private BufferedImage image;
    private double inclinaison;



    //Constructeur du Véhicule
    public Vehicule(double m) {

        this.poids = m;
        this.inclinaison=0;
        this.pos=new Point(50,50);
        this.vit=new Point(0,0); //pas de vitesse initiale
        this.acc=new Point(0,0);
        try {

            image = ImageIO.read(new File("Sam/images/cadis.png"));

        }catch (Exception e){
            System.out.println(e);
        }


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
    public void setInclinaison(double i){
        this.inclinaison=i;
    }


    public void dessine (Graphics g){


        g.drawImage(rotateImage(image,this.inclinaison),(int)this.getPos().getX()-45,(int)this.getPos().getY()-50,70,70,null);
        g.fillOval((int)this.pos.getX()-10,(int)this.pos.getY()-10,10,10);



    }
    public double normeVit(){
        return Math.sqrt(Math.pow(this.getVit().getX(),2)+Math.pow(this.getVit().getY(),2));
    }
    private BufferedImage rotateImage(BufferedImage originalImage, double degree) {
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();
        double toRad = Math.toRadians(degree);
        int hPrime = (int) (w * Math.abs(Math.sin(toRad)) + h * Math.abs(Math.cos(toRad)));
        int wPrime = (int) (h * Math.abs(Math.sin(toRad)) + w * Math.abs(Math.cos(toRad)));
        BufferedImage rotatedImage = new BufferedImage(wPrime, hPrime, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = rotatedImage.createGraphics();
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, wPrime, hPrime);
        g.setComposite(AlphaComposite.Src);
        g.translate(wPrime/2, hPrime/2);
        g.rotate(toRad);
        g.translate(-w/2, -h/2);
        g.drawImage(originalImage, 0, 0, null);
        g.dispose();
        return rotatedImage;
    }

}

