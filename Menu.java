import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {
    JButton start;
    JButton leave;
    JPanel panelMain;
    JLabel titre;


    public Menu (){
        super("");

        start=new JButton("Jouer");
        start.setBounds(50,50,200,100);
        start.setBackground(Color.green);
        start.addActionListener(this);

        leave=new JButton("Quitter");
        leave.setBounds(50,200,200,100);
        leave.setBackground(Color.red);
        leave.addActionListener(this);

        panelMain=new JPanel();
        panelMain.setBounds(0,0,400,400);
        panelMain.setLayout(null);
        panelMain.setBackground(Color.LIGHT_GRAY);
        panelMain.add(start);
        panelMain.add(leave);

        this.setSize(300, 460);
        this.add(panelMain);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==start){
            new MenuNiveaux();
            this.dispose();

        }
        if(e.getSource()==leave){
            this.dispose();

        }

    }

}
