

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


public class monPanelDessin extends JPanel {

    private Courbe c;
    private Courbe cVrai;
    private Color maCouleur;
    private Vehicule veh;
    private Image b1=null;
    private Image o1=null;
    private LinkedList<Rectangle> obstacle;

    public monPanelDessin() {

        this.c = new Courbe(new LinkedList<Point>(),Color.red);
        this.cVrai = new Courbe(new LinkedList<Point>(),Color.blue);


    }
    public monPanelDessin(Image b){
        this.b1=b;
    }
    public monPanelDessin(Vehicule v,Image b,Image c,LinkedList<Rectangle> r){
        this.veh=v;
        this.c = new Courbe(new LinkedList<Point>(),Color.red);
        this.cVrai = new Courbe(new LinkedList<Point>(),Color.blue);
        this.obstacle=new LinkedList<>();
        for(Rectangle rec : r){
            this.obstacle.add(rec);
        }

        b1 = b;
        o1 = c;


    }

    public void update(Courbe c) {
        this.c = c;
        // Couleur initiale
        maCouleur = new Color(200, 200, 200);

    }
    public void updateV (Courbe c){
        this.cVrai=c;
    }

    public void paint(Graphics g) {
        // On dessine le fond
        g.setColor(maCouleur);


        // On dessine toutes les courbes du tableau si la liste n'est pas vide
        if(b1!=null){

            g.drawImage(b1,0,0,this.getWidth(),this.getHeight(),null);
        }
        g.drawRect(0,400,400,400);
        if((o1!=null)&&(this.obstacle.get(1)!=null)){
            g.drawImage(o1,(int)this.obstacle.get(1).getX(),(int)this.obstacle.get(1).getY(),this.obstacle.get(1).width,this.obstacle.get(1).height,null);
        }
        if (c!=null) {

                c.dessine(g);
        }
        if(this.veh!=null) {
            this.veh.dessine(g);
        }
        if(this.obstacle!=null){
            for(Rectangle rec : this.obstacle){
                g.setColor(Color.lightGray);
                g.fillRect(rec.x,rec.y,rec.width,rec.height);
            }
            g.setColor(Color.ORANGE);
            g.fillRect(this.obstacle.get(0).x,this.obstacle.get(0).y,this.obstacle.get(0).width,this.obstacle.get(0).height);
        }
        g.drawRect(0,400,400,400);

    }


}
