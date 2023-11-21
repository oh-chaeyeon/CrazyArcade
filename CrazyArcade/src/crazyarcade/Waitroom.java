package crazyarcade;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Waitroom extends JFrame {
    JScrollPane scrollPane;
    ImageIcon icon;

    Waitroom() {
        super("대기실"); // Title
        JPanel jPanel = new JPanel();

        icon = new ImageIcon("./image/waitroom.png");

        JPanel background = new JPanel(new BorderLayout()) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (icon != null) {
                    int width = getWidth();
                    int height = (int) (((double) width / icon.getIconWidth()) * icon.getIconHeight());
                    g.drawImage(icon.getImage(), 0, 0, width, height, this);
                }
            }
        };

        scrollPane = new JScrollPane(jPanel);
        scrollPane.setBorder(new LineBorder(Color.YELLOW, 3));

        background.setBounds(0, 0, 900, 550);
        scrollPane.setBounds(28, 555, 840, 200);

        JTextField inputBox = new JTextField();
        inputBox.setBounds(28, 760, 700, 35);

        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setText("보내기");
        button.setBounds(735, 760, 131, 35);

        getContentPane().add(background);
        getContentPane().add(scrollPane);
        getContentPane().add(inputBox);
        getContentPane().add(button);

        setLayout(null);

        Color customColor = new Color(1, 49, 100);
        getContentPane().setBackground(customColor);
        setSize(900, 850);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Waitroom());
    }
}
