import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener, ChangeListener, KeyListener, MouseListener {


    private JSlider sliderVolume;
    private Float volume;
    public SoundEffect song;
    private Timer timer;
    private MenuNiveaux niveaux;


    public Menu() {
        super("Menu Principal");
        volume = -20f;
        song=new SoundEffect();
        song.setFile("Sam/musics/song2.wav");
        song.setVolume(volume);
        song.play();
        song.loop();


        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screensize.width, screensize.height-50);

        JPanel panelMain=new JPanel();
        panelMain.setBounds(0,0,this.getWidth(),this.getHeight());
        panelMain.setLayout(null);

        JPanel panelSon=new JPanel();
        panelSon.setBounds(0,0,30,this.getHeight());
        panelSon.setBackground(Color.LIGHT_GRAY);

        sliderVolume = new JSlider(0, 50, 25);
        sliderVolume.setOrientation(1);
        sliderVolume.setSize(30, 500);
        sliderVolume.setLocation(0, 150);
        sliderVolume.addChangeListener(this);

        JLabel etiquetteVolume = new JLabel("Son");
        etiquetteVolume.setSize(30, 20);
        etiquetteVolume.setLocation(0, 0);
        panelSon.add(sliderVolume);
        panelSon.add(etiquetteVolume);

        this.setVisible(true);
        ImageIcon fond = new ImageIcon("Sam/images/titlescreen.gif");
        monPanelDessin zoneD = new monPanelDessin(fond);
        zoneD.setBounds(30, 0, screensize.width-20, screensize.height);
        panelMain.add(zoneD);
        panelMain.add(panelSon);
        this.add(panelMain);


        zoneD.addMouseListener(this);
        this.addKeyListener(this);
        this.setResizable(false);



        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {



    }




    public void stateChanged(ChangeEvent e) {
        volume = (float) sliderVolume.getValue() - 45f;
        if (sliderVolume.getValue() == 0) {
            volume = -80f;
        }
        song.setVolume(volume);


    }


    public void keyTyped(KeyEvent e) {
        niveaux = new MenuNiveaux(1);
        this.setVisible(false);
    }


    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        niveaux = new MenuNiveaux(1);
        this.setVisible(false);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }


}
