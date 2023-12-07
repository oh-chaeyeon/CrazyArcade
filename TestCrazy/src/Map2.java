import javax.swing.*;
import java.awt.*;

public class Map2 extends JFrame {

    private Image background;
    private Image cookie2;
    

    public Map2() {
        setTitle("Map2");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel을 추가하여 이미지를 그릴 수 있도록 함
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                screenDraw(g);
            }
        };
        add(panel);

        background = new ImageIcon(Main.class.getResource("/image/mapbg1.png")).getImage();
        cookie2 = new ImageIcon(Main.class.getResource("/image/cookie2.png")).getImage();
        setVisible(true);
    }

    public void screenDraw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        
       
        //하트
        int[][] brickCoordinates = {
                {160, 80}, {200, 80}, {240, 80},
                {120, 110},
                {80, 140}, {80, 170}, {80, 200}, {80, 230},
                {120, 270}, {160, 310}, {200, 350}, {240, 390}, {280, 390}, {320, 390},
                {360, 350}, {400, 310}, {440, 270},
                {280, 110},
                {320, 80}, {360, 80}, {400, 80},
                {440, 110},
                {480, 140}, {480, 170}, {480, 200}, {480, 230}
        };
        for (int[] coordA : brickCoordinates) {
            int x = coordA[0];
            int y = coordA[1];
            g.drawImage(cookie2, x, y, null);
        }
        
        
        this.repaint();
    }

   
}
