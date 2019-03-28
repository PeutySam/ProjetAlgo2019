import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Victoire extends JFrame {

    private monPanelDessin zoneD;
    private Image gg;
    private Dimension screensize;

    public Victoire(){

        super("Victoire");
        screensize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setVisible(true);
        this.setResizable(false);
        this.setBounds(0,0,(int)screensize.getWidth(),(int)screensize.getHeight());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try{
            gg= ImageIO.read(new File("Sam/images/victoire1.jpg"));
        }catch(IOException ex){
            System.out.println(ex);
        }

        zoneD=new monPanelDessin(gg);
        zoneD.setBounds(0,0,this.getWidth(),this.getHeight());
        this.add(zoneD);

    }

}
