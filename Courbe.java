import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics;

public class Courbe {

    private LinkedList<Point> listePoint; //Liste de Point décrivant la courbe
    private Color maCouleur; //Couleur de la courbe


    public Courbe(LinkedList<Point> liste, Color c) {

        this.maCouleur = c;
        this.listePoint = liste;
    }
    public LinkedList<Point> getList (){
        return listePoint;
    }


    public void dessine(Graphics g) {
        g.setColor(maCouleur);
        for (int i = 1; i < listePoint.size(); i++) {
            g.drawLine((int) listePoint.get(i - 1).getX(), (int) listePoint.get(i - 1).getY(), (int) listePoint.get(i).getX(), (int) listePoint.get(i).getY());
        }
    }

    public boolean verifier() {
        Point lastCoeff = new Point(1, 0);
        Point currentCoeff = new Point(1, 0);

        int compteur = 0;
        for (int i = 2; i < listePoint.size(); i++) {
            lastCoeff.setY((listePoint.get(i - 1).getY() - listePoint.get(i - 2).getY()) / (listePoint.get(i - 1).getX() - listePoint.get(i - 2).getX()));
            currentCoeff.setY((listePoint.get(i).getY() - listePoint.get(i - 1).getY()) / (listePoint.get(i).getX() - listePoint.get(i - 1).getX()));
            if (Math.abs(Math.atan2(currentCoeff.getY(), currentCoeff.getX()) - Math.atan2(lastCoeff.getY(), lastCoeff.getX())) < Math.PI / 2) {
                compteur++;
            }
        }
        if (compteur == listePoint.size() - 2) {
            return true;
        } else {
            return false;
        }
    }

}

