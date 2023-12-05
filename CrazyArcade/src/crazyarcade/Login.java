package crazyarcade;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    public Login(String title, Runnable onStartChat) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/image/login.jpg"));
        Image image = backgroundIcon.getImage().getScaledInstance(750, 550, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        JButton startButton = new JButton("시작하기");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                onStartChat.run();
            }
        });
        
        startButton.setFont(new Font("굴림", Font.BOLD, 20));
        startButton.setFocusPainted(false);

        JLabel backgroundLabel = new JLabel(scaledIcon);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(startButton, BorderLayout.SOUTH);
        panel.add(backgroundLabel, BorderLayout.CENTER);

        setContentPane(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login frame = new Login("Login", () -> {
                System.out.println("게임에 접속합니다..");
                SwingUtilities.invokeLater(() -> new Waitroom("대기실"));
            });
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
