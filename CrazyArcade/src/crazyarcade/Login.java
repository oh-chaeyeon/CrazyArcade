package crazyarcade;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
   JScrollPane scrollPane;
    ImageIcon icon;

    public Login() {
        icon = new ImageIcon("C:\\Users\\joie0\\git\\CrazyArcade\\CrazyArcade\\CrazyArcade\\src\\crazyarcade\\image\\login.jpg");
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
       
        background.setLayout(null);
        
        JButton loginButton = new JButton("시작하기");
        
        loginButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new Waitroom();
        		setVisible(false);
        	}
        });
        
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("굴림", Font.BOLD, 20));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setBackground(new Color(0, 0, 255));
        loginButton.setSize(320, 60);
        loginButton.setLocation(280, 510);
        background.add(loginButton);
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