
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Map1.person1.moveToLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Map1.person1.moveToRight();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			Map1.person1.moveToUp();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Map1.person1.moveToDown();
		}
		
		
		else if (e.getKeyCode() == KeyEvent.VK_A) {
            Map1.person2.moveToLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            Map1.person2.moveToRight();
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            Map1.person2.moveToUp();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            Map1.person2.moveToDown();
        }
	}
	
}
