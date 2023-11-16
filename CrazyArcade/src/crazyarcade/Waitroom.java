package crazyarcade;

import javax.swing.*;
import java.awt.*;

public class Waitroom extends JFrame {
	JScrollPane scrollPane;
	ImageIcon icon;
    Waitroom(){
    	
        super("대기실"); //타이틀
        JPanel jPanel = new JPanel();

        icon = new ImageIcon("C:/Users/joie0/git/CrazyArcade/CrazyArcade/CrazyArcade/src/crazyarcade/image/waitroom.png");
        //icon = new ImageIcon("C:/Users/user/git/CrazyArcade/CrazyArcade/src/CrazyArcade/image/login.jpg");
        
        JPanel background = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (icon != null) {
                    g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };
        
        scrollPane = new JScrollPane(background);
        setContentPane(scrollPane);
        
        setSize(900, 650);
        add(jPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
