package crazyarcade;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
   JScrollPane scrollPane;
    ImageIcon icon;

    public Login() {
        icon = new ImageIcon("C:/Users/user/git/CrazyArcade/CrazyArcade/src/CrazyArcade/image/login.jpg");

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
    }

    public static void main(String[] args) {
       Login frame=new Login();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("CrazyArcade");
        frame.setSize(900, 650);
       frame.setVisible(true);
    }
}