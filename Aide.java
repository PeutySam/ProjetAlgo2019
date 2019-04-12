import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.io.IOException;

public class Aide extends JFrame {

    private Image help;

    public Aide(){

        super("Help");
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setVisible(true);
        this.setResizable(false);
        this.setBounds(350, 20, (int) (0.8 * screensize.getWidth()), (int) (0.85 * screensize.getHeight()));

        try{
            help= ImageIO.read(new File("Sam/images/help.png"));
        }catch(IOException ex){
            System.out.println(ex);
        }

        monPanelDessin zoneD=new monPanelDessin(help);
        zoneD.setBounds(0,0,this.getWidth(),this.getHeight());
        this.add(zoneD);









    }




}