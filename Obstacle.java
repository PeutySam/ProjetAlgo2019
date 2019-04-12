import java.awt.*;

public class Obstacle extends Rectangle {
    private Point vit;


    public Obstacle(int x,int y, int width, int height){
        super(x,y,width,height);
        vit=new Point(0,0);

    }
    public void setVit(int x, int y){
        vit.setX(x);
        vit.setY(y);
    }
    public void move(){
        this.setLocation(this.x+(int)vit.getX(),this.y+(int)vit.getY());
    }
    public Point getVit(){
        return this.vit;
    }

}
