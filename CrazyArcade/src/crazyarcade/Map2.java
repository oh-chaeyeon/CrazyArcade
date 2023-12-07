package crazyarcade;

import javax.swing.*;
import java.awt.*;

public class Map2 {

    public Map2(Point location) {
        // Swing UI를 실행하는 메서드 호출, 위치 정보 전달
        createAndShowGUI(location);
    }

    private void createAndShowGUI(Point location) {
        // JFrame 생성
        JFrame frame = new JFrame("Map2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 레이블 추가
        JLabel label = new JLabel("Map2", SwingConstants.CENTER);
        frame.getContentPane().add(label);

        // 창을 waitroom의 위치에 설정
        frame.setLocation(location);

        // 창 보이게 설정
        frame.setVisible(true);
    }
}
