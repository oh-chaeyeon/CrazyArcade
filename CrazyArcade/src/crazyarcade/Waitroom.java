package crazyarcade;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Waitroom extends JFrame {
    private JTextArea textArea;
    private JTextField inputBox;
    private JButton sendButton;
    private JScrollPane scrollPane;
    private JButton map1Button;
    private JButton map2Button;
    private JButton startButton;

    public Waitroom(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        int frameWidth = 760;
        int frameHeight = 560;
        setSize(frameWidth, frameHeight);

        Color backgroundColor = new Color(1, 49, 100);
        getContentPane().setBackground(backgroundColor);

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/image/waitroom.png"));
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(frameWidth, 365, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, frameWidth, 365);
        add(imageLabel);

        ImageIcon map1Icon = new ImageIcon(getClass().getResource("/image/map1.png"));
        map1Button = new JButton("맵 1: Box", map1Icon);
        map1Button.setBounds(505, 165, 170, 40);
        map1Button.setBackground(Color.WHITE);
        map1Button.setFocusPainted(false);
        map1Button.setHorizontalAlignment(SwingConstants.LEFT);
        getLayeredPane().add(map1Button, Integer.valueOf(1));
        add(map1Button);

        ImageIcon map2Icon = new ImageIcon(getClass().getResource("/image/map2.png"));
        map2Button = new JButton("맵 2: Cookie", map2Icon);
        map2Button.setBounds(505, 220, 170, 40);
        map2Button.setBackground(Color.WHITE);
        map2Button.setFocusPainted(false);
        map2Button.setHorizontalAlignment(SwingConstants.LEFT);
        getLayeredPane().add(map2Button, Integer.valueOf(1));
        add(map2Button);
        
        startButton = new JButton("게임 시작");
        startButton.setBounds(265, 308, 225, 40);
        startButton.setBackground(Color.ORANGE);
        startButton.setFocusPainted(false);
        add(startButton);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(new Color(1, 49, 100));
        textArea.setForeground(Color.WHITE);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 370, 710, 100);
        scrollPane.setBorder(new LineBorder(Color.YELLOW, 3));
        add(scrollPane);

        inputBox = new JTextField();
        inputBox.setBackground(new Color(1, 49, 100));
        inputBox.setForeground(Color.WHITE);
        inputBox.setBorder(new LineBorder(Color.YELLOW, 3));
        inputBox.setBounds(20, 480, 580, 30);
        add(inputBox);

        sendButton = new JButton("보내기");
        sendButton.setBounds(620, 480, 110, 30);
        sendButton.setBackground(Color.YELLOW);
        sendButton.setFocusPainted(false);
        add(sendButton);

        setVisible(true);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JTextField getInputBox() {
        return inputBox;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public void appendText(String text) {
        textArea.append(text);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public JButton getMap1Button() {
        return map1Button;
    }

    public JButton getMap2Button() {
        return map2Button;
    }

    public JButton getStartButton() {
    	return startButton;
    }
    
    public void client1Image(String imagePath) {
        try {
            Image image = ImageIO.read(getClass().getResource(imagePath));
            ImageIcon imageIcon = new ImageIcon(image);

            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBounds(146, 170, imageIcon.getIconWidth(), imageIcon.getIconHeight());

            getLayeredPane().add(imageLabel, Integer.valueOf(1));

            revalidate();
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void client2Image(String imagePath) {
        try {
            Image image = ImageIO.read(getClass().getResource(imagePath));
            ImageIcon imageIcon = new ImageIcon(image);

            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBounds(355, 170, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            getLayeredPane().add(imageLabel, Integer.valueOf(1));

            revalidate();
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
