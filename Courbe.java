import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Courbe {

    private LinkedList<Point> listePoint; //Liste de Point décrivant la courbe

    private int limiteC;


    public Courbe(LinkedList<Point> liste) {


        this.listePoint = liste;
    }

    public LinkedList<Point> getList() {
        return listePoint;
    }


    public void dessine(Graphics gr) {
        Graphics2D g2 = (Graphics2D) gr;
        g2.setStroke(new BasicStroke(7));

        List<Color> colors = new ArrayList<Color>();
        for (int r = 0; r < 200; r++) colors.add(new Color(r * 255 / 200, 255, 0));
        for (int g = 200; g > 0; g--) colors.add(new Color(255, g * 255 / 200, 0));
        for (int b = 0; b < 200; b++) colors.add(new Color(255, 0, b * 255 / 200));
        for (int r = 200; r > 0; r--) colors.add(new Color(r * 255 / 200, 0, 255));
        for (int g = 0; g < 200; g++) colors.add(new Color(0, g * 255 / 200, 255));
        for (int b = 200; b > 0; b--) colors.add(new Color(0, 255, b * 255 / 200));

        Color[] c = colors.toArray(new Color[colors.size()]);

        for (int i = 1; i < listePoint.size(); i++) {
            if (limiteC < c.length) {
                g2.setColor(c[limiteC]);
                g2.draw(new Line2D.Float((int) listePoint.get(i - 1).getX(), (int) listePoint.get(i - 1).getY(), (int) listePoint.get(i).getX(), (int) listePoint.get(i).getY()));
                limiteC++;


            } else {
                limiteC = 0;
            }
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

